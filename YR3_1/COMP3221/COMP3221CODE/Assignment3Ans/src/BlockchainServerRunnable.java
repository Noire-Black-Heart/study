import java.io.*;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.net.*;
import java.util.*;

 
 
public class BlockchainServerRunnable implements Runnable{
 
    private Socket clientSocket;
    private Blockchain blockchain;
    private ConcurrentHashMap<ServerInfo, Date> serverStatus;
    private static int x = 0;
    private int ownPort;
 
    public BlockchainServerRunnable(Socket clientSocket, Blockchain blockchain, ConcurrentHashMap<ServerInfo, Date> serverStatus, int ownPort) {
        this.clientSocket = clientSocket;
        this.blockchain = blockchain;
        this.serverStatus = serverStatus;
        this.ownPort = ownPort;
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
        
        // Create an instance of the heartbeat thread
        HeartBeat hb = new HeartBeat();
 
        try {
            while (true) {
                
                System.out.println("At read");
                String inputLine;
                while (true) {
                    try {
                        inputLine = inputReader.readLine();
                        break;
                    } catch (SocketException e) {
                        
                    }
                }
                
                if (inputLine == null) {
                    break;
                }
                System.out.println("Read: " + inputLine);
                
                String localIP = (((InetSocketAddress) clientSocket.getLocalSocketAddress()).getAddress()).toString().replace("/", "");
                String remoteIP = (((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress()).toString().replace("/", "");
 
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
                    case "hb":
                        Date time = new Date();
                        ServerInfo serverInfo = new ServerInfo(remoteIP, Integer.parseInt(tokens[1]));
                        
                        Boolean contains = serverStatus.containsKey(serverInfo);
                        
                        serverStatus.put(serverInfo, time);
                        if (!contains) {
                            ArrayList<Thread> threadArrayList = new ArrayList<>();
                            for (ServerInfo x : serverStatus.keySet()) {
                                if ((x.getHost().equals(remoteIP) && x.getPort() == Integer.parseInt(tokens[1]))) {
                                    continue;
                                }
                                Thread thread = new Thread(new HeartBeatRunnable(x, "si|" + ownPort + "|" + remoteIP + "|" + tokens[1]));
                                threadArrayList.add(thread);
                                thread.start();
                            }
                            for (Thread thread : threadArrayList) {
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                }
                                
                            }
                        }
                        break;
                    case "si":
                        ServerInfo serverInfoSI = new ServerInfo(tokens[2], Integer.parseInt(tokens[3]));
                        Boolean containsSI = serverStatus.containsKey(serverInfoSI);
                        
                        if (!containsSI) {
                            serverStatus.put(serverInfoSI, new Date());
                            
                            ArrayList<Thread> threadArrayListSI = new ArrayList<>();
                            for (ServerInfo x : serverStatus.keySet()) {
                                if ((x.getHost().equals(remoteIP) && x.getPort() == Integer.parseInt(tokens[1])) || (x.getHost().equals(tokens[2]) && x.getPort() == Integer.parseInt(tokens[3])) || (x.getHost().equals(localIP) && x.getPort() == ownPort)) {
                                   continue;
                                }
                                
                                Thread thread = new Thread(new HeartBeatRunnable(x, "si|" + ownPort + "|" + tokens[2] + "|" + tokens[3]));
                                threadArrayListSI.add(thread);
                                thread.start();
                            }
 
                            for (Thread thread : threadArrayListSI) {
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                        break;
                        
                    case "lb":
                        int remotePort = Integer.parseInt(tokens[1]);
                        int remoteBlockchainSize = Integer.parseInt(tokens[2]);
                        String remoteBlockHash = tokens[3];
                        
                        
                        Boolean catchup = false;
                        if (remoteBlockchainSize > blockchain.getLength()) {
                            catchup = true;
                        }
                        else {
                            if (blockchain.getHead() == null) {
                                break;
                            }
                            
                            String localBlockHash = Base64.getEncoder().encodeToString(blockchain.getHead().calculateHash());
                            
                            if (!localBlockHash.equals(remoteBlockHash)) {
                                
                                if (remoteBlockchainSize == 0) {
                                    break;
                                }
                                
                                for (int i = 0; i < localBlockHash.length(); i++) {
                                    byte x = (byte) localBlockHash.charAt(i);
                                    byte y = (byte) remoteBlockHash.charAt(i);
                                    if (Byte.compare(x, y) < 0) {
                                        catchup = true;
                                        break;
                                    }
                                }
                            }
                        }
                        
                        if (catchup == true) {
                            
                            System.out.println("Entered Catchup");
                                
                            ArrayList<Block> blocks = new ArrayList<Block>();
                            String currentHash = Base64.getEncoder().encodeToString(new byte[32]);
                            
                            String host = remoteIP;
                            int port = remotePort;
                            
                            for (int x = 0; x < remoteBlockchainSize; x++) {
                                try {
                                    System.out.println("Catchup " + x);
                                    
                                    Socket socket = new Socket();
                                    SocketAddress address = new InetSocketAddress(host, port);
                                    socket.connect(address, 2000);
                                    
                                    String message = "cu|" + currentHash;
                                    
                                    PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                                    pw.print(message + "\n");
                                    pw.flush();
                                    
                                    Thread.sleep(100);
                                    
                                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                                    
                                    Block recieved = (Block) in.readObject();
                                    blocks.add(recieved);
                                    
                                    currentHash = Base64.getEncoder().encodeToString(recieved.calculateHash());
                                    
                                    pw.print("cc\n");
                                    pw.flush();
                                    
                                    pw.close();
                                    out.close();
                                    socket.close();
                                    
                                    System.out.println("Catchup " + x + " finished");
                                    
                                    System.out.println("\t\t" + currentHash);
                                } catch (InterruptedException e) {
                                    
                                }
                                
                            }
                            
                            blockchain.clear();
                            
                            for (Block x : blocks) {
                                ArrayList<Transaction> transactions = x.getTransactions();
                                blockchain.setPool(transactions);
                                
                                blockchain.commit2();
                            }
                            
                        }
                        
                        break;
                    
                    case "cu":
                        if (tokens.length == 1) {
                            int size = blockchain.getLength();
                            String hash;
                            
                            if (blockchain.getHead() == null) {
                                hash = Base64.getEncoder().encodeToString(new byte[32]);
                            } else {
                                hash = Base64.getEncoder().encodeToString(blockchain.getHead().calculateHash());
                            }
                            
                            outWriter.print("lb|" + ownPort + "|" + size + "|" + hash + "\n");
                            outWriter.flush();
                        } else {
                            System.out.println("CU recieved");
                            
                            String recievedHash = tokens[1];
                            
                            Block want = new Block();
                            
                            Block bl = blockchain.getHead();
                            while (bl != null) {
                                byte[] blP = bl.getPreviousHash();
                                if (Base64.getEncoder().encodeToString(blP).equals(recievedHash)) {
                                    want = bl;
                                }
                                bl = bl.getPreviousBlock();
                            }

                            System.out.println(clientSocket.getPort());
                            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                            
                            out.writeObject(want);
                        }
                        
                        break;
                    default:
                        outWriter.print("Error\n\n");
                        outWriter.flush();
                    System.out.println(x);
                    x++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            
        }// catch (InterruptedException e) {
        // }
    }
}