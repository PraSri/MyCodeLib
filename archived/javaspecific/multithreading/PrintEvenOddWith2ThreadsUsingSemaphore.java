package multithreading;

import java.util.concurrent.Semaphore;

public class PrintEvenOddWith2ThreadsUsingSemaphore {

	// https://www.baeldung.com/java-even-odd-numbers-with-2-threads

	public static void main(String[] args) {

		SharedPrinter printer = new SharedPrinter();

		Thread t1 = new Thread(new Odd(printer, 10), "ODD NUMBER");
		Thread t2 = new Thread(new Even(printer, 10), "EVEN NUMBER");
		t1.start();
		t2.start();

	}

}

class SharedPrinter {

	// A semaphore controls access to a shared resource through the use of a
	// counter.
//	If the counter is greater than zero, then access is allowed. If it is zero, then access is denied.

//	The SharedPrinter class will have two semaphores, 
//	semOdd and semEven which will have 1 and 0 permits to start with.
//	This will ensure that odd number gets printed first.

	private Semaphore semOdd = new Semaphore(1);
	private Semaphore semEven = new Semaphore(0);

	void printEvenNum(int num) {

		try {
			semEven.acquire();
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}

		System.out.println(Thread.currentThread().getName() + ":" + num);
		semOdd.release();

	}

	void printOddNum(int num) {

//		To print an odd number, the acquire() method is called on semOdd, 
//		and since the initial permit is 1, it acquires the access successfully, 
//		prints the odd number and calls release() on semEven.
		try {
			semOdd.acquire();
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}

		System.out.println(Thread.currentThread().getName() + ":" + num);

//		Calling release() will increment the permit by 1 for semEven, 
//		and the even thread can then successfully acquire the access and print the even number.
		semEven.release();

	}

}

class Even implements Runnable {

	private SharedPrinter printer;
	private int max;

	public Even(SharedPrinter printer, int max) {
		this.printer = printer;
		this.max = max;
	}

	@Override
	public void run() {

		for (int i = 2; i <= max; i += 2) {

			printer.printEvenNum(i);

		}

	}

}

class Odd implements Runnable {

	private SharedPrinter printer;
	private int max;

	public Odd(SharedPrinter printer, int max) {
		this.printer = printer;
		this.max = max;
	}

	@Override
	public void run() {
		for (int i = 1; i <= max; i += 2) {
			printer.printOddNum(i);
		}
	}

}
