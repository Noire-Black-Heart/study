import java.net.*;
import java.io.*;

public class DateServer {
	public static void main(String[] args) {
		
		try {
			// TODO: create a new socket
			ServerSocket server = new ServerSocket(6013);
			Socket client = null;
			// now listen for connections
			while (true) {
				// TODO: wait and accept a client connection
				client = server.accept();
				
				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

				// write the Date to the socket
				pout.println(new java.util.Date().toString());
				// TODO: close the client connection
				client.close();
				server.close();
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
    }
}