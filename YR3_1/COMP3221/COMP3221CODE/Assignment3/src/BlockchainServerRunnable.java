import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class BlockchainServerRunnable implements Runnable{

    private Socket clientSocket;
    private Blockchain blockchain;
    private ConcurrentHashMap<ServerInfo, Date> serverStatus;
    private int currentServerPort;

    public BlockchainServerRunnable(Socket clientSocket, Blockchain blockchain, ConcurrentHashMap<ServerInfo, Date> serverStatus, int currentServerPort) {
        this.clientSocket = clientSocket;
        this.blockchain = blockchain;
        this.serverStatus = serverStatus;
        this.currentServerPort = currentServerPort;
    }
    
    public static String getClientIP(Socket clientSocket) {
        if (null == clientSocket) {
            return "";
        }
        SocketAddress remote = clientSocket.getRemoteSocketAddress();
        String addr = remote.toString();

        if (addr.length() > 0) {
            int index = addr.lastIndexOf("/");
            if (index >= 0) {
                return addr.substring(index + 1);
            }

            return addr;
        }

        return "";
    }
    

    public void run() {
        try {
            serverHandler(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
        }
    }

    public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {

        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(clientInputStream));
        PrintWriter outWriter = new PrintWriter(clientOutputStream, true);

        try {
            while (true) {
                String inputLine = inputReader.readLine();
                if (inputLine == null) {
                    break;
                }
                
                //get the IP from current client socket
                //String PclientIP = getClientIP(clientSocket); 
                String PclientIP = (((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress()).toString().replace("/", "");
                //get the IP of current server socket
                String currentServerIP = (((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress()).toString().replace("/", "");
                
                //handle command
                String[] tokens = inputLine.split("\\|");
                switch (tokens[0]) {
                    case "tx":
                        if (blockchain.addTransaction(inputLine))
                            outWriter.print("Accepted\n\n");
                        else
                            outWriter.print("Rejected\n\n");
                        outWriter.flush();
                        break;
                    case "pb":
                        outWriter.print(blockchain.toString() + "\n");
                        outWriter.flush();
                        break;
                    case "cc":
                        return;
                    //heart beat receiving and server info sending
                    case "hb": 
                    	//now token[1] means P port here
                    	String Pport = tokens[1];
                    	
                        ServerInfo PserverInfo = new ServerInfo(PclientIP, Integer.parseInt(Pport));
                        //put the new given serverinfo P into server status list
                        //serverStatus.put(PserverInfo, new Date());
                        
                        //if the Q thread first time hears P join in
                        if (serverStatus.containsKey(PserverInfo) == false) {
                            ArrayList<Thread> heartBeatList = new ArrayList<>();
                            for (ServerInfo QserverInfo : serverStatus.keySet()) {
                                //if ((QserverInfo.getHost().equals(PclientIP) && QserverInfo.getPort() == Integer.parseInt(tokens[1]))) {
                                //    continue;
                                //}
                            	//start new heart beat client thread to send message
                                Thread heartBeatClient = new Thread(new HeartBeatClientRunnable(QserverInfo, "si|" + currentServerPort + "|" + PclientIP + "|" + Pport));
                                heartBeatList.add(heartBeatClient);
                                heartBeatClient.start();
                            }
                            //join thread
                            for (Thread beat : heartBeatList) {
                                try {
                                    beat.join();
                                } catch (InterruptedException e) {
                                }
                                
                            }
                          //put the new given serverinfo P into server status list
                          serverStatus.put(PserverInfo, new Date());
                        }
                        break;
                    //serverinfo receiving and relaying
                    case "si":
                    	//now token[1] means S port here
                    	String Sport = tokens[1];
                    	
                    	ServerInfo PserverInfo2 = new ServerInfo(tokens[2], Integer.parseInt(tokens[3]));
                        //Boolean containsSI = serverStatus.containsKey(PserverInfo);
                        
                    	//if the server S doesnt know P, then relay
                        if (serverStatus.containsKey(PserverInfo2) == false) {
                        	//put the new given serverinfo P into server status list
                            //serverStatus.put(PserverInfo, new Date());
                            
                            ArrayList<Thread> threadArrayListSI = new ArrayList<>();
                            for (ServerInfo serverNode : serverStatus.keySet()) {
                                //if ((x.getHost().equals(remoteIP) && x.getPort() == Integer.parseInt(tokens[1])) || (x.getHost().equals(tokens[2]) && x.getPort() == Integer.parseInt(tokens[3])) || (x.getHost().equals(localIP) && x.getPort() == ownPort)) {
                            	
                            	//if originator or myself
                            	if ((serverNode.getHost().equals(PclientIP) && serverNode.getPort() == Integer.parseInt(tokens[1])) || (serverNode.getHost().equals(currentServerIP) && serverNode.getPort() == currentServerPort)) {
                            		continue;
                                }
                                //send si message
                                Thread thread = new Thread(new HeartBeatClientRunnable(serverNode, "si|" + currentServerPort + "|" + tokens[2] + "|" + tokens[3]));
                                threadArrayListSI.add(thread);
                                thread.start();
                            }
 
                            for (Thread thread : threadArrayListSI) {
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                }
                            }
                          //put the new given serverinfo P into server status list
                            serverStatus.put(PserverInfo2, new Date());
                        }
                        break;
                    	
                    	
                    	
                    	
                    default:
                        outWriter.print("Error\n\n");
                        outWriter.flush();
                }
            }
        } catch (IOException e) {
        }
        //catch (InterruptedException e) {
//        }
    }
}
