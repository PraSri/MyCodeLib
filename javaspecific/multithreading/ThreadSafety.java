package multithreading;

public class ThreadSafety {

	/***
	 * count is incremented by 1 four times and since we have two threads, its
	 * value should be 8 after both the threads finished executing. But when you
	 * will run the above program multiple times, you will notice that count value
	 * is varying between 6,7,8. This is happening because even if count++ seems to
	 * be an atomic operation, its NOT and causing data corruption.
	 */

	public static void main(String[] args) throws InterruptedException {
		ProcessingThread pt = new ProcessingThread();
		Thread t1 = new Thread(pt, "t1");
		t1.start();
		Thread t2 = new Thread(pt, "t2");
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Count : " + pt.getCount());
	}

}
