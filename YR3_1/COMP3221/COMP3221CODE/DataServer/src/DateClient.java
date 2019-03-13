import java.net.*;
import java.io.*;

public class DateClient {
	public static void main(String[] args) {
		try {
			
			
			
            // TODO
			Socket client = new Socket("127.0.0.1", 6013);
			
			// read the date from the socket
			String line;
			
			BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			while ((line = bin.readLine()) != null)
				System.out.println(line);

            // TODO
			client.close();
			
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}