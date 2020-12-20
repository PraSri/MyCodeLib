package multithreading;

import java.util.Arrays;

public class SyncronizedMethod {

	public static void main(String[] args) throws InterruptedException {
		String[] strArr = { "1", "2", "3" };
		HashmapProcessor hmp = new HashmapProcessor(strArr);

		Thread t1 = new Thread(hmp, "t1");
		Thread t2 = new Thread(hmp, "t2");
		Thread t3 = new Thread(hmp, "t3");

		long start = System.currentTimeMillis();

		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();

		System.out.println(" Time Taken  :" + (System.currentTimeMillis() - start));

		System.out.println("Get the shared variable values  : ");
		Arrays.stream(hmp.getMap()).forEach((i) -> System.out.println(i));

	}

}

class HashmapProcessor implements Runnable {

	private String[] strArr = null;

	public HashmapProcessor(String[] strArr) {

		this.strArr = strArr;
	}

	public String[] getMap() {
		return this.strArr;
	}

	@Override
	public void run() {

		processArray(Thread.currentThread().getName());

	}

	private void processArray(String name) {

		for (int i = 0; i < this.strArr.length; i++) {
			processSomething(i);
			addThreadName(i, name);

		}

	}

	private void addThreadName(int i, String name) {
		// without using synchronized block on Object object lock this will give
		// inconsistent output
		synchronized (this) {
			this.strArr[i] = this.strArr[i] + ":" + name;
		}

	}

	private void processSomething(int i) {

		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}