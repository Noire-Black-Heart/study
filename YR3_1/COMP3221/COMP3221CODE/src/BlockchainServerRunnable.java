import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class BlockchainServerRunnable implements Runnable{

    private Socket clientSocket;
    private Blockchain blockchain;
    // new variable for monitor close, not static for multiple instance
    private boolean close;

    public BlockchainServerRunnable(Socket clientSocket, Blockchain blockchain) {
        // implement your code here
    	this.clientSocket = clientSocket;
    	this.blockchain = blockchain;
    	this.close = false;
    }

    public void run() {
        // implement your code here
    	//create stream
    	InputStream clientInputStream = null;
		OutputStream clientOutputStream = null;
		
		try {
					while (true) {
						
						
						//get input&output streams from client socket
						clientInputStream = clientSocket.getInputStream();
						clientOutputStream = clientSocket.getOutputStream();
						
						//use handler on streams
						serverHandler(clientInputStream, clientOutputStream);

								//close the client connection if cc is typed
								if(close == true) {
								close = false;
								clientInputStream.close();
								clientOutputStream.close();
								clientSocket.close();
								}
					}
				} catch (IOException ioe) {
					System.err.println(ioe);
				}
		        
		
    }
    
    // implement any helper method here if you need any
    public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientInputStream));
        PrintWriter outWriter = new PrintWriter(clientOutputStream, true);

        // TODO: implement your code here.
        
        String inputLine = null;
        
        try {
        			//begin processing stuff
        			while(((inputLine = inputReader.readLine()) != null)){
        				
        				//tx command
        				if(inputLine.startsWith("tx")) {
        					
        					boolean result = this.blockchain.addTransaction(inputLine);
        				
        					if(result == false) {
        						outWriter.print("Rejected\n\n");
        					}else {
        						outWriter.print("Accepted\n\n");
        					}	
        				}
        				//pb command
        				else if(inputLine.equals("pb")){
        					outWriter.print(blockchain.toString() + "\n");
        					
        				}
        				//cc command
        				else if(inputLine.equals("cc")) {
        					close = true;
        					break;
        				}
        				//error
        				else {
        					outWriter.print("Error\n\n");
        				}
        				
        				//flush at last
        				outWriter.flush();
        			}
        	
        	
        }catch (IOException ioe) {
			System.err.println(ioe);
		}
        
     
    }
}
