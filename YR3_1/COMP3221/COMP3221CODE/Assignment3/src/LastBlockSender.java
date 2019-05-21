import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;

public class LastBlockSender implements Runnable {
	
	private ConcurrentHashMap<ServerInfo, Date> serverStatus;
	private Blockchain blockchain;
	private int localPort;
	
	public LastBlockSender(ConcurrentHashMap<ServerInfo, Date> serverStatus, Blockchain blockchain, int localPort) {
		this.serverStatus = serverStatus;
		this.blockchain = blockchain;
		this.localPort = localPort;
	}
	
	@Override
	public void run() {
		
		while(true) {
			
				ArrayList<Thread> threadArrayList = new ArrayList<>();
				for (ServerInfo serverInfo : serverStatus.keySet()) {
				
				int chainSize = blockchain.getLength();
				
				String hash = null;
				//check if the head block is empty, if empty, assign empty hash
				if (blockchain.getHead() == null) {
					hash = Base64.getEncoder().encodeToString(new byte[32]);
				} else {
					//if not empty, use the latest block(head block)'s hash
					hash = Base64.getEncoder().encodeToString(blockchain.getHead().calculateHash());
				}
				//broadcast the message
				Thread thread = new Thread(new LastBlockPrinter(serverInfo, "lb|" + localPort + "|" + chainSize + "|" + hash));
				threadArrayList.add(thread);
				thread.start();
			}
			//join threads
			for (Thread thread : threadArrayList) {
				try {
					thread.join();
				} catch (InterruptedException e) {
				}
			}

			// sleep for two seconds
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
		
	}
}