package multithreading;

public class PrintEvenOddWith2Threads {

	// https://www.baeldung.com/java-even-odd-numbers-with-2-threads
	
	public static void main(String[] args) {

		Printer printer = new Printer();

		Thread t1 = new Thread(new EvenOddTask(10, printer, false), "ODD NUMBER");
		Thread t2 = new Thread(new EvenOddTask(10, printer, true), "EVEN NUMBER");
		t1.start();
		t2.start();

	}

}

class EvenOddTask implements Runnable {

	private int max;
	private Printer print;
	private boolean isEven;

	public EvenOddTask(int max, Printer print, boolean isEven) {
		this.max = max;
		this.print = print;
		this.isEven = isEven;
	}

	@Override
	public void run() {

		int number = isEven ? 2 : 1;

		while (number <= max) {
			if (isEven) {
				print.printEvenNumber(number);
			} else {
				print.printOddNumber(number);
			}
			number += 2;
		}

	}

}

class Printer {

	private volatile boolean isOdd = false;

	public synchronized void printEvenNumber(int number) {

		while (!isOdd) {
			try {
				wait();// Wait() causes the current thread to wait indefinitely until some other thread
						// calls notify() or
				// notifyAll() on the same object.
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}
		}

		System.out.println(Thread.currentThread().getName() + ":" + number);
		isOdd = false;
		notify();// We can call notify() to waking up threads that are waiting for access to this
					// object’s monitor.

	}

	public synchronized void printOddNumber(int number) {

		// it will be in while loop till isOdd becomes false
		while (isOdd) {

			try {
				wait();
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}

		}

		System.out.println(Thread.currentThread().getName() + ":" + number);
		isOdd = true;
		notify();

	}

}