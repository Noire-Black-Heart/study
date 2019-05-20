import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;import java.net.*;

public class BlockchainServer {

    public static void main(String[] args) {

        if (args.length != 3) {
            return;
        }

        int localPort = 0;
        int remotePort = 0;
        String remoteHost = null;

        try {
            localPort = Integer.parseInt(args[0]);
            remoteHost = args[1];
            remotePort = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            return;
        }

        Blockchain blockchain = new Blockchain();

        ConcurrentHashMap<ServerInfo, Date> serverStatus = new ConcurrentHashMap<ServerInfo, Date>();
        serverStatus.put(new ServerInfo(remoteHost, remotePort), new Date());

        PeriodicCommitRunnable pcr = new PeriodicCommitRunnable(blockchain);
        Thread pct = new Thread(pcr);
        pct.start();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(localPort);
            
            // Creating new hearbeatRemoval
            HeartBeatRemoval heartBeatRemoval = new HeartBeatRemoval(serverStatus);
            Thread heartBeatRemovalThread = new Thread(heartBeatRemoval);
            heartBeatRemovalThread.start();
            
            // Creating new heartbeat
            HeartBeat heartBeat = new HeartBeat();
            heartBeat.setNewServerStatus(serverStatus);
            heartBeat.setPortNumber(localPort);
            
            Thread heartBeatThread = new Thread(heartBeat);
            heartBeatThread.start();
            
            // Creating new lastBlock
            LastBlock lastBlock = new LastBlock(serverStatus, blockchain, localPort);
            Thread lastBlockThread = new Thread(lastBlock);
            lastBlockThread.start();
            
            // Initial catchup
            try {
                // create socket with a timeout of 2 seconds
                String host = remoteHost;
                int port = remotePort;
                
                Socket socket = new Socket();
                SocketAddress address = new InetSocketAddress(host, port);
                socket.connect(address, 2000);

                // send the message forward
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.print("cu\n");
                pw.flush();
                
                // close printWriter and socket
                pw.close();
                socket.close();
                
            } catch (IOException e) {
                
            }
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new BlockchainServerRunnable(clientSocket, blockchain, serverStatus, localPort)).start();
            }
        } catch (IllegalArgumentException e) {
        } catch (IOException e) {
        } finally {
            try {
                pcr.setRunning(false);
                pct.join();
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
        }
    }
}
