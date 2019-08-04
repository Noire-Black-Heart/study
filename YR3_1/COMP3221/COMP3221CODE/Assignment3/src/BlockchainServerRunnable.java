import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class BlockchainServerRunnable implements Runnable{

    /*
        student Michael Matta(460157667) helped me with the idea of 
        changing localhost to 127.0.0.1 , and hash comparison & error catch&handling
        */

    private Socket clientSocket;
    private Blockchain blockchain;
    private ConcurrentHashMap<ServerInfo, Date> serverStatus;
    private int localPort;

    public BlockchainServerRunnable(Socket clientSocket, Blockchain blockchain, ConcurrentHashMap<ServerInfo, Date> serverStatus, int localPort) {
        this.clientSocket = clientSocket;
        this.blockchain = blockchain;
        this.serverStatus = serverStatus;
        this.localPort = localPort;
    }

    public void run() {
        try {
            serverHandler(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();  
        }
    }

    public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {

        BufferedReader inputReader = new BufferedReader(
            new InputStreamReader(clientInputStream));
        PrintWriter outWriter = new PrintWriter(clientOutputStream, true);

        try {
            while (true) {
                
                String inputLine = null;
                try{
                inputLine = inputReader.readLine();
                } catch (SocketException e){
                    //
                }
                    
                if (inputLine == null) {
                    break;
                }

                //get the remote IP from socket
                String remoteIP = (((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress()).toString().replace("/", "");
                //get the local IP from socket
                String localIP = (((InetSocketAddress) clientSocket.getLocalSocketAddress()).getAddress()).toString().replace("/", "");

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

                        ServerInfo PserverInfo = new ServerInfo(remoteIP, Integer.parseInt(Pport));


                        //if the Q thread first time hears P join in
                        if (serverStatus.containsKey(PserverInfo) == false) {
                            ArrayList<Thread> heartBeatList = new ArrayList<>();
                            for (ServerInfo QserverInfo : serverStatus.keySet()) {                           

                                //start new heart beat client thread to send message
                                Thread heartBeatClient = new Thread(new HeartBeatClientRunnable(QserverInfo, "si|" + localPort + "|" + remoteIP + "|" + Pport));
                                heartBeatList.add(heartBeatClient);
                                heartBeatClient.start();
                            }

                            //check if the server is local server
                            if(PserverInfo.getHost().equals("localhost")){
                                PserverInfo.setHost("127.0.0.1");
                            }
                            //put the new given serverinfo P into server status list
                            serverStatus.put(PserverInfo, new Date());

                            //join thread
                            for (Thread beat : heartBeatList) {
                                try {
                                    beat.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace(new java.io.PrintStream(System.out));                               
                                }

                            }
                            //put the new given serverinfo P into server status list
                            //serverStatus.put(PserverInfo, new Date());
                        }
                        break;
                        //serverinfo receiving and relaying
                    case "si":
                        //now token[1] means S port here
                        String Sport = tokens[1];

                        ServerInfo PserverInfo2 = new ServerInfo(tokens[2], Integer.parseInt(tokens[3]));

                        //if the server S doesnt know P, then relay
                        if (serverStatus.containsKey(PserverInfo2) == false) {

                            ArrayList<Thread> threadArrayListSI = new ArrayList<>();
                            for (ServerInfo serverNode : serverStatus.keySet()) {


                                //if originator or myself
                                if ((serverNode.getHost().equals(remoteIP) && serverNode.getPort() == Integer.parseInt(tokens[1])) || (serverNode.getHost().equals(localIP) && serverNode.getPort() == localPort)) {
                                    continue;
                                }
                                //send si message
                                Thread thread = new Thread(new HeartBeatClientRunnable(serverNode, "si|" + localPort + "|" + tokens[2] + "|" + tokens[3]));
                                threadArrayListSI.add(thread);
                                thread.start();
                            }

                            //check if the server is local server
                            if(PserverInfo2.getHost().equals("localhost")){
                                PserverInfo2.setHost("127.0.0.1");
                            }

                            //put the new given serverinfo P into server status list
                            serverStatus.put(PserverInfo2, new Date());

                            for (Thread thread : threadArrayListSI) {
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace(new java.io.PrintStream(System.out));  
                                }
                            }
                            //put the new given serverinfo P into server status list
                            //  serverStatus.put(PserverInfo2, new Date());
                        }
                        break;

                        //last block received handling
                    case "lb":

                        int remotePort = Integer.parseInt(tokens[1]);
                        int remoteChainSize = Integer.parseInt(tokens[2]);
                        byte[] remoteLastHash = Base64.getDecoder().decode(tokens[3]);

                        //set flag to indicate catchup needed
                        boolean catchupFlag = false; 

                        //check if local server needs to catch up

                        //1. check if the chain size is same
                        if (blockchain.getLength() < remoteChainSize) {
                            catchupFlag = true;
                        }
                        //2. if length is same, compare local last hash to remote last hash. catch up if local block hash is bigger
                        else {
                            if (blockchain.getHead() == null) {
                                break;
                            }


                            byte[] localLastHash = blockchain.getHead().calculateHash();
                            //if they are not equal, compare byte by byte
                            for(int i = 0; i < 32; i ++){
                                //if remote hash is smaller, then catch
                                if(localLastHash[i] > remoteLastHash[i]){
                                    catchupFlag = true;
                                    break;
                                }
                                else if(localLastHash[i] < remoteLastHash[i]){
                                    break;
                                }
                            }


                        }

                        //catch up action after this
                        if(catchupFlag == true) {

                            Thread catchUp = new Thread(new CatchUpThread(remoteIP, remotePort, blockchain));
                            catchUp.start();
                            try{ catchUp.join(); 
                               } catch (Exception enf) {}

                        }

                        break;
                        //catch up received handling
                    case "cu": 
                        //if command is only "cu", send head block hash 
                        if (tokens.length == 1) {                   		

                            //send the wanted block
                            ObjectOutputStream blockOut = new ObjectOutputStream(clientSocket.getOutputStream());
                            if(blockchain.getHead() != null) {
                                blockOut.flush();
                                blockOut.writeObject(blockchain.getHead());
                            }
                            else {
                                blockOut.flush();
                                blockOut.writeObject(null);
                            }
                            blockOut.close();
                        } 

                        //if command is with hash, find that block
                        else {

                            String recievedHash = tokens[1];

                            Block wantedBlock = new Block();

                            Block currentBlock = blockchain.getHead();

                            while (currentBlock != null) {
                                //check block hash values
                                if (Base64.getEncoder().encodeToString(currentBlock.calculateHash()).equals(recievedHash)) {
                                    wantedBlock = currentBlock;
                                    break;
                                }
                                currentBlock = currentBlock.getPreviousBlock();
                            }

                            //send the wanted block
                            ObjectOutputStream blockOut = new ObjectOutputStream(clientSocket.getOutputStream());

                            blockOut.flush();

                            blockOut.writeObject(wantedBlock);



                            blockOut.close();
                        }
                        return;

                    default:
                        outWriter.print("Error\n\n");
                        outWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }
        //catch (InterruptedException e) {
        //        }
    }
}
