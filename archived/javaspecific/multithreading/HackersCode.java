package multithreading;

public class HackersCode {

	public static void main(String[] args) {

		ProcessingThread object = new ProcessingThread();
		
		//cause the system to go on deadlock and cause Denial of Service (DoS).
		
		synchronized (object) {

			while (true) {
				try {
					Thread.sleep(Integer.MAX_VALUE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
