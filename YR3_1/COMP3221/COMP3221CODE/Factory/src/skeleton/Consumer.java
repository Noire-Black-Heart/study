package skeleton;

import java.util.Date;

public class Consumer implements Runnable {
	public Channel<Date> queue;
	
	public Consumer(Channel<Date> MQ){
		this.queue = MQ;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            while (true) {
                Thread.sleep(500);
                System.out.println("received " + queue.receive());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
