import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;

public class HeartBeat implements Runnable {
	
	private ConcurrentHashMap<ServerInfo, Date> serverStatus;
	private int sequenceNumber;
	private int portNumber;
	
	public HeartBeat() {
		this.sequenceNumber = 0;
	}
	
	public void setNewServerStatus(ConcurrentHashMap<ServerInfo, Date> serverStatus) {
//		this.serverStatus = new ConcurrentHashMap<ServerInfo, Date>();
//		for (ServerInfo x : serverStatus.keySet()) {
//			Date y = serverStatus.get(x);
//			this.serverStatus.put(x, y);
//		}
		this.serverStatus = serverStatus;
	}
	
//	public void addServerStatus(ServerInfo serverInfo, Date date) {
//		serverStatus.put(serverInfo, date);
//	}
	
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	
	@Override
	public void run() {
		
		while(true) {
			// broadcast HeartBeat message to all peers
			ArrayList<Thread> threadArrayList = new ArrayList<>();
			for (ServerInfo serverInfo : serverStatus.keySet()) {
				Thread thread = new Thread(new HeartBeatRunnable(serverInfo, "hb|" + portNumber + "|" + sequenceNumber));
				//System.out.println("HB: " + serverInfo.getPort());
				threadArrayList.add(thread);
				thread.start();
			}

			for (Thread thread : threadArrayList) {
				try {
					thread.join();
				} catch (InterruptedException e) {
				}
			}

			// increment the sequenceNumber
			sequenceNumber += 1;

			// sleep for two seconds
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
		
	}
}