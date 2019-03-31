import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteCounterImpl extends UnicastRemoteObject implements RemoteCounter{

	static int i = 0 ;
	
	protected RemoteCounterImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int inc() {
		// TODO Auto-generated method stub
		
		return i++;
	}

	
	
	
	
	public static void main(String args[]) {
		
		
		try {
			// TODO: create a new socket
			RemoteCounterImpl server = new RemoteCounterImpl();
		
		
		// Bind this object instance to the name "RMICounterObject"
		Naming.rebind("123", server);

		}catch(Exception e) {

			System.err.print(e);
		}
	}
}
