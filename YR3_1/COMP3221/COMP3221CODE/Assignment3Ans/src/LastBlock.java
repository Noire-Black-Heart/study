import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;

public class LastBlock implements Runnable {
	
	private ConcurrentHashMap<ServerInfo, Date> serverStatus;
	private Blockchain blockchain;
	private int ownPort;
	
	public LastBlock(ConcurrentHashMap<ServerInfo, Date> serverStatus, Blockchain blockchain, int ownPort) {
		this.serverStatus = serverStatus;
		this.blockchain = blockchain;
		this.ownPort = ownPort;
	}
	
	@Override
	public void run() {
		
		while(true) {
			// broadcast lb message to all peers
			ArrayList<Thread> threadArrayList = new ArrayList<>();
			for (ServerInfo serverInfo : serverStatus.keySet()) {
				
				int size = blockchain.getLength();
				String hash;
				if (blockchain.getHead() == null) {
					hash = Base64.getEncoder().encodeToString(new byte[32]);
				} else {
					hash = Base64.getEncoder().encodeToString(blockchain.getHead().calculateHash());
				}
				
				Thread thread = new Thread(new LastBlockRunnable(serverInfo, "lb|" + ownPort + "|" + size + "|" + hash));
				threadArrayList.add(thread);
				thread.start();
			}

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