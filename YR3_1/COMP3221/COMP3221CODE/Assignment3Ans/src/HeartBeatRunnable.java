import java.io.*;
import java.net.*;

public class HeartBeatRunnable implements Runnable {
	
	private ServerInfo serverInfo;
	private String message;
	
	public HeartBeatRunnable(ServerInfo serverInfo, String message) {
		this.serverInfo = serverInfo;
		this.message = message;
	}
	
	@Override
	public void run() {
		try {
			// create socket with a timeout of 2 seconds
			String host = serverInfo.getHost();
			int port = serverInfo.getPort();
			
			Socket socket = new Socket();
			SocketAddress address = new InetSocketAddress(host, port);
			socket.connect(address, 2000);

			// send the message forward
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			pw.print(message + "\n");
			pw.flush();
			
			// close printWriter and socket
			pw.close();
			socket.close();
			
		} catch (IOException e) {
			
		} 
	}
}