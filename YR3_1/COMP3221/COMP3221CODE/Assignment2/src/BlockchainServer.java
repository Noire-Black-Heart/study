import java.io.*;
import java.net.*;

public class BlockchainServer {

    public static void main(String[] args) {

        if (args.length != 1) {
            return;
        }

        int portNumber;
        try {
        portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
        	//not a number
        	return;
        }
        Blockchain blockchain = new Blockchain();


        PeriodicCommitRunnable pcr = new PeriodicCommitRunnable(blockchain);
        Thread pct = new Thread(pcr);
        pct.start();

        // implement your code here
        
        //initiate server socket
        ServerSocket server;
        Socket client;
        try {
			server = new ServerSocket(portNumber);
			
			 //listen to connections
			while(true) {
	        	client = server.accept();
	        	
	        	//open new server runnable thread in connection
	        	new Thread(new BlockchainServerRunnable(client, blockchain)).start();
	        }
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(IllegalArgumentException e) {
			//port value out of range;
			return;
		}
       
        
        
        
        
        
        pcr.setRunning(false);
        try {
			pct.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

    // implement any helper method here if you need any
}
