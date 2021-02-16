package multithreading;

public class DemoWaitNotify {

	public static void main(String[] args) {
		
		
		Message msg = new Message("process it!!!!!!!!!!");
		
		Waiter w1 = new Waiter(msg);
		
		Waiter w2 = new Waiter(msg);
		
		Notifier n = new Notifier(msg);
		
		
		Thread wt1 = new Thread(w1,"waiter1");
		Thread wt2 = new Thread(w2,"waiter2");
		
		Thread nThread = new Thread(n,"notifier");
		
		wt1.start();
		wt2.start();
		nThread.start();
		
		System.out.println("All threads are started!!!!!!!!!!!!!!!!!!!");
		

	}

}
;