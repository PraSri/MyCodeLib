package multithreading;

public class ProcessingThread implements Runnable {

	private int count = 0;

	@Override
	public void run() {

		for (int i = 1; i < 5; i++) {
			processAnything(i);
			count++;
		}

	}

	public int getCount() {
		return this.count;
	}

	private void processAnything(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
