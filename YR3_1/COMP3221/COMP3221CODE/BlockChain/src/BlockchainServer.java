import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockchainServer {
    private Blockchain blockchain;

    //new variable for monitoring close command
    private static boolean close = false;
    
    public BlockchainServer() { blockchain = new Blockchain(); }

    // getters and setters
    public void setBlockchain(Blockchain blockchain) { this.blockchain = blockchain; }
    public Blockchain getBlockchain() { return blockchain; }

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }
        int portNumber = Integer.parseInt(args[0]);
        BlockchainServer bcs = new BlockchainServer();

        // TODO: implement your code here.
        try {
			// create a new socket and client in&output streams
			ServerSocket server = new ServerSocket(portNumber);
			Socket client = null;
			InputStream clientInputStream = null;
			OutputStream clientOutputStream = null;
			
			// now listen for connections
			while (true) {
				
				// TODO: wait and accept a client socket connection
				client = server.accept();
				
				//get input&output streams from client socket
				clientInputStream = client.getInputStream();
				clientOutputStream = client.getOutputStream();
				
				//use handler on streams
				bcs.serverHandler(clientInputStream, clientOutputStream);

						//close the client connection if cc is typed
						if(close == true) {
						close = false;
						clientInputStream.close();
						clientOutputStream.close();
						client.close();
						}
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
        
        
    }

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
        					
        					int result = this.getBlockchain().addTransaction(inputLine);
        				
        					if(result == 0) {
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

    // implement helper functions here if you need any.
}