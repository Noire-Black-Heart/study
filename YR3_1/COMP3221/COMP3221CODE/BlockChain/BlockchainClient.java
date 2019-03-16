import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BlockchainClient {
    public static void main(String[] args) {

        if (args.length != 2) {
            return;
        }
        String serverName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        BlockchainClient bcc = new BlockchainClient();

        // TODO: implement your code here.
        
        try {
			Socket client = new Socket(serverName, portNumber);
			//client.setSoTimeout(1000);
			InputStream serverInputStream = client.getInputStream();
			OutputStream serverOutputStream = client.getOutputStream();
			
			//use handler
			bcc.clientHandler(serverInputStream, serverOutputStream);
			
			
			//close after breaking from the loop
			client.close();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public void clientHandler(InputStream serverInputStream, OutputStream serverOutputStream) {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(serverInputStream));
        PrintWriter outWriter = new PrintWriter(serverOutputStream, true);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            // TODO: implement your code here
        	String command = sc.nextLine();
        	
        		//if command is cc
        	if(command.equals("cc")) {
        		outWriter.print(command + "\n");
        		outWriter.flush();
        		break;
        	}
        	//send command to server
        	outWriter.print(command + "\n");
        	outWriter.flush();
        	//print server response
        	
        	try {
        	String outputLine = null;
        	
        	while(((outputLine = inputReader.readLine()) != null)){
				System.out.print(inputReader.readLine() + "\n");
				//break if no input to read
				if(!inputReader.ready()) {
					break;
				}
        	}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        }
    }

    // implement helper functions here if you need any.
}