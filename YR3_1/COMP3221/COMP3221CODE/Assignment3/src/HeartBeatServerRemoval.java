import java.util.*;
import java.util.concurrent.*;

public class HeartBeatServerRemoval implements Runnable {
	
	private ConcurrentHashMap<ServerInfo, Date> serverStatus;
	
	public HeartBeatServerRemoval(ConcurrentHashMap<ServerInfo, Date> serverStatus) {
		this.serverStatus = serverStatus;
	}
	
	@Override
	public void run() {
		while(true) {
			for (ServerInfo serverInfo : serverStatus.keySet()) {
				// if the peer is not heard for 4 sec
				if (new Date().getTime() - serverStatus.get(serverInfo).getTime() >= 4000) {
					serverStatus.remove(serverInfo);
				}
			}try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
			
		}
	}
}