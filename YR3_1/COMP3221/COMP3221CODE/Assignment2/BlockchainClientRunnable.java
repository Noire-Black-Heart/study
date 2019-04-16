import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BlockchainClientRunnable implements Runnable {

    private String reply;
    private int serverNumber;
    private String serverName;
    private int portNumber;
    private String message;

    public BlockchainClientRunnable(int serverNumber, String serverName, int portNumber, String message) {
        this.reply = "Server" + serverNumber + ": " + serverName + " " + portNumber + "\n"; // header string
        this.serverNumber = serverNumber;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.message = message;
    }

    public void run() {
        // implement your code here
    	
    	
    	Socket client;
		try {
			client = new Socket(serverName, portNumber);
			
			InputStream serverInputStream = client.getInputStream();
			OutputStream serverOutputStream = client.getOutputStream();
			
			System.out.print(reply);
			
			this.clientHandler(serverInputStream, serverOutputStream);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String getReply() {
        return reply;
    }

    // implement any helper method here if you need any
    public void clientHandler(InputStream serverInputStream, OutputStream serverOutputStream) {
        BufferedReader inputReader = new BufferedReader( new InputStreamReader(serverInputStream));
        PrintWriter outWriter = new PrintWriter(serverOutputStream, true);
        
        //send message to server
        outWriter.println(message);
        outWriter.flush();
        
        // print server response
        try {
            String outputLine;
            
            while ((outputLine = inputReader.readLine()) != null) {
            	
            	//update server's reply
                reply += outputLine + "\n"; 
                
                System.out.print(outputLine + "\n");
                //break if no input to read
                if(!inputReader.ready()){
                    break;
                }
            }
        	} catch (IOException e) {
        	// TODO Auto-generated catch block
            e.printStackTrace();
        }
        	
        	
        }
    }