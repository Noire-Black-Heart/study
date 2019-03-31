import java.rmi.*;
public class RemoteCounterClient {
	
	
	public static void main(String[] args) {
			try {
				RemoteCounter server = (RemoteCounter)Naming.lookup("rmi://127.0.0.1/123");
				
			System.out.println(	server.inc());
				
				
				
				
			}catch (Exception e) {
				System.err.print(e);
			}
		
		
	}
}
