import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class PeriodicHeartBeatRunnable implements Runnable {

    private ConcurrentHashMap<ServerInfo, Date> serverStatus;
    private int sequenceNumber;
    private int portNumber;

    public PeriodicHeartBeatRunnable(ConcurrentHashMap<ServerInfo, Date> serverStatus, int portNumber) {
        this.serverStatus = serverStatus;
        this.sequenceNumber = 0;
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
    	
        while(true) {
        	
            // broadcast HeartBeat message to all peers
            ArrayList<Thread> threadArrayList = new ArrayList<>();
            for (ServerInfo server : serverStatus.keySet()) {
                Thread thread = new Thread(new HeartBeatClientRunnable(server, "hb|" + portNumber + "|" + sequenceNumber));
                threadArrayList.add(thread);
                thread.start();
            }

            for (Thread thread : threadArrayList) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                }
            }
            
         // check to remove server info
        	for (ServerInfo serverInfo : serverStatus.keySet()) {
				
        		// if the peer is not heard for 4 sec(4 sec -2 sleep = 2 sec here)
				if (new Date().getTime() - serverStatus.get(serverInfo).getTime() >= 2000) {
					
					//remove the server info from list
					serverStatus.remove(serverInfo);
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
