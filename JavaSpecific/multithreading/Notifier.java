package multithreading;

/****
 * A class that will process on Message object and then invoke notify method to
 * wake up threads waiting for Message object. Notice that synchronized block is
 * used to own the monitor of Message object.
 ****/
public class Notifier implements Runnable {
	
	private Message msg;
	
	

	public Notifier(Message msg) {
		this.msg = msg;
	}



	@Override
	public void run() {
		
		String name = Thread.currentThread().getName();
		
		System.out.println(name + " started!!");
		
		try {
			Thread.sleep(5000);
			
			synchronized(msg) {
				msg.setMsg(name + " Notifier works done!!!");
				msg.notify();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}

}
