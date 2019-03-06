package skeleton;

import java.util.Date;

public class Producer implements Runnable {
	public Channel<Date> queue;
	
	public Producer(Channel<Date> MQ){
		this.queue = MQ;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		  try {
	            while (true) {
	                Thread.sleep(500);
	                Date date = new Date();
	                queue.send(date);
	        		System.out.println("added " + date);
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	}

}
