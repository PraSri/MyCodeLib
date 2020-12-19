package multithreading;

public class MyThread {

	Thread t = new Thread(() -> {
		System.out.println("My thread is running by implementing Runnable interface with lambda...............");
	});

	Runnable runnable = new Runnable() {

		@Override
		public void run() {

			System.out.println("This is runnable old way of writitng runnable");

		}
	};

	Thread sleepJoinThread = new Thread(() -> {
		letDoSomeTask();
	});

	Thread oldWayThread = new Thread(runnable, "Old without lambda!!!!!!!!!!!");

	public static void main(String[] args) {

		MyThread myThread = new MyThread();
		
		Thread t1 = myThread.sleepJoinThread;
		
		Thread t2 = myThread.t;
		
		Thread t3 = myThread.oldWayThread;
		
		t1.start();
		
		System.out.println("t1 pe join method bulaya hai!!!!!!!!!!!");
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("t1 thread pe join method cross ho gya matlab ...t1 thread mar gya!!!!");
		
		System.out.println("t2 is starting..........");
		
		t2.start();
		
		System.out.println("t3 is starting..........");
		
		t3.start();


	}

	private void letDoSomeTask() {
		
		System.out.println("Inside letDoSomeTask method............");

		System.out.println("I m going to sleep for 5 seconds ...............");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("I m awake again!!!!!!!!!!");

	}
}
