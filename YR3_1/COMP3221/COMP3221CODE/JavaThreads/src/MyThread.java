
public class MyThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hello, World");
	}
	
	public static void main(String args[]) {
		Thread mt = new Thread(new MyThread());// (2) allocate a new thread
		mt.start(); // (3) start it
		
		}
	
	


}
