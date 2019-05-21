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
                            
                          //put the new given serverinfo P into server status list
                            serverStatus.put(PserverInfo, new Date());
                            
                            //join thread
                            for (Thread beat : heartBeatList) {
                                try {
                                    beat.join();
                                } catch (InterruptedException e) {
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
                            
                          //put the new given serverinfo P into server status list
                            serverStatus.put(PserverInfo2, new Date());

                            for (Thread thread : threadArrayListSI) {
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
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
                    String remoteLastHash = tokens[3];
                    //set flag to indicate catchup needed
                    boolean catchupFlag = false; 
                    
                    //check if local server needs to catch up
                    
                    //1. check if the chain size is same
                    if (blockchain.getLength() < remoteChainSize) {
                        catchupFlag = true;
                    }
                    //2. if length is same, compare local last hash to remote last hash. catch up if local block hash is bigger
                    else {
                    	String localLastHash = Base64.getEncoder().encodeToString(blockchain.getHead().calculateHash());
                        //if they are not equal, compare byte by byte
                        for (int i = 0; i < localLastHash.length(); i++) {
                        	
                            byte a = (byte) localLastHash.charAt(i);
                            byte b = (byte) remoteLastHash.charAt(i);
                            
                            if (Byte.compare(a, b) < 0) {
                                catchupFlag = true;
                                break;
                            }
                        }
                    }
                    
                    //catch up action after this
                    if(catchupFlag == true) {
                    	//use the naive solution, put all remote blocks into a list, then restore them one by one
                    	ArrayList<Block> remoteBlockList = new ArrayList<Block>();
                    	
                        String currentHash = Base64.getEncoder().encodeToString(new byte[32]);
                        
                        // initiate everything for one block transmission
                        for (int i = 0; i < remoteChainSize; i++) {
                            try {
                            	//create new socket for transmitting one block object
                                Socket catchupSocket = new Socket();
                                catchupSocket.connect(new InetSocketAddress(remoteIP, remotePort), 2000);
                                
                                //sending cu command to remote to get block
                                String message = "cu|" + currentHash;
                                PrintWriter pw = new PrintWriter(catchupSocket.getOutputStream(), true);
                                pw.print(message + "\n");
                                pw.flush();
                                
                                Thread.sleep(100);
                                
                                //create input streams to get block
                                ObjectInputStream blockIn = new ObjectInputStream(catchupSocket.getInputStream());
                                
                                Block recieved = (Block) blockIn.readObject();
                                remoteBlockList.add(recieved);
                                
                                //renew current hash to progress
                                currentHash = Base64.getEncoder().encodeToString(recieved.calculateHash());
                                
                                //cancel current connection from remote client to avoid exception
                                pw.print("cc\n");
                                pw.flush();
                                
                                //close writer and socket for this block
                                pw.close();
                                catchupSocket.close();

                            } catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            
                        }
                        
                        //after receiving all blocks, assemble them into a new list, and replace current one
                        blockchain = new Blockchain();
                        
                        blockchain.setHead(remoteBlockList.get(0));
                        int i = 0;
                        Block current = blockchain.getHead();
                        while(remoteBlockList.get(i + 1) != null) {
                        	current.setPreviousBlock(remoteBlockList.get(i + 1));
                            //prevNode.next = nextNode;
                            //prevNode = nextNode;
                        	current = remoteBlockList.get(i);
                            i = i + 1;  // Move to next node.
                        }
                        blockchain.setLength(remoteBlockList.size());
                    	
                    	
                    }

                    break;
                    //catch up received handling
                    case "cu": 
                    	//if command is only "cu", send head block hash 
                    	if (tokens.length == 1) {
                    		
                            int localChainSize = blockchain.getLength();
                            String headHash = Base64.getEncoder().encodeToString(blockchain.getHead().calculateHash());
                            //send the lb message
                            PrintWriter cuOutWriter = new PrintWriter(clientOutputStream, true);
                                                        
                            cuOutWriter.print("lb|" + localPort + "|" + localChainSize + "|" + headHash + "\n");
                            cuOutWriter.flush();
                        } 
                    	
                    	//if command is with hash, find that block
                    	else {
                            
                            String recievedHash = tokens[1];

                            Block wantedBlock = new Block();
                            
                            Block currentBlock = blockchain.getHead();
                            
                            while (currentBlock != null) {
                               //check block hash values
                                if (Base64.getEncoder().encodeToString(currentBlock.getPreviousHash()).equals(recievedHash)) {
                                    wantedBlock = currentBlock;
                                }
                                currentBlock = currentBlock.getPreviousBlock();
                            }

                            //send the wanted block
                            ObjectOutputStream blockOut = new ObjectOutputStream(clientSocket.getOutputStream());
                            
                            blockOut.writeObject(wantedBlock);
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
