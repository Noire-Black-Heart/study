import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

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
        
        //if the remoteHost == localhost, then convert it into 127.0.0.1
        if(remoteHost.equals("localhost")){
            remoteHost = "127.0.0.1";
        }
        serverStatus.put(new ServerInfo(remoteHost, remotePort), new Date());

        PeriodicCommitRunnable pcr = new PeriodicCommitRunnable(blockchain);
        Thread pct = new Thread(pcr);
        pct.start();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(localPort);


            //create new server removal thread
            ////Thread serverRemoval = new Thread(new HeartBeatServerRemoval(serverStatus));
            //serverRemoval.start();

            //create new heart beat sender thread
            Thread heartBeatSender = new Thread(new PeriodicHeartBeatRunnable(serverStatus, localPort));
            heartBeatSender.start();

            //create new last block sender thread
            Thread lastBlockSender = new Thread(new LastBlockSender(serverStatus, blockchain, localPort));
            lastBlockSender.start();
            
            //initial catchup
            Thread initCatch = new Thread(new CatchUpThread(remoteHost, remotePort, blockchain));
            initCatch.start();
            try{ initCatch.join(); 
               } catch (Exception enf) {
                enf.printStackTrace();
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

