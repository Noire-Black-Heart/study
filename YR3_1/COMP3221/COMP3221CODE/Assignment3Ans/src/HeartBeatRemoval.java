import java.util.*;
import java.util.concurrent.*;

public class HeartBeatRemoval implements Runnable {
	
	private ConcurrentHashMap<ServerInfo, Date> serverStatus;
	
	public HeartBeatRemoval(ConcurrentHashMap<ServerInfo, Date> serverStatus) {
		this.serverStatus = serverStatus;
	}
	
	@Override
	public void run() {
		while(true) {
			for (ServerInfo serverInfo : serverStatus.keySet()) {
				// if greater than 2T, remove
				if (new Date().getTime() - serverStatus.get(serverInfo).getTime() >= 4000) {
					serverStatus.remove(serverInfo);
					// System.out.println("Removed: " + serverInfo.getPort());
				}
			}try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
			
		}
	}
}