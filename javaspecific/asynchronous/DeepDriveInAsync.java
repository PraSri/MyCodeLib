package asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import multithreading.Waiter;

public class DeepDriveInAsync {

	// https://medium.com/xebia-engineering/asynchronous-programming-with-java-java-8-d71a5323070e

	public void executeMe(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		DeepDriveInAsync instance = new DeepDriveInAsync();
		long s = System.currentTimeMillis();
		instance.executeMe(2000);
		instance.executeMe(2000);
		long e = System.currentTimeMillis();
		System.out.println(e - s);

		Thread t1 = new Thread(() -> instance.executeMe(2000));
		Thread t2 = new Thread(() -> instance.executeMe(2000));
		s = System.currentTimeMillis();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		e = System.currentTimeMillis();
		System.out.println(e - s);

		System.out.println("IN JAVA 8");
		s = System.currentTimeMillis();
		Stream.of(1, 2).parallel().forEach(i -> instance.executeMe(2000));
		e = System.currentTimeMillis();
		System.out.println(e - s);

		System.out.println("In completeable future");
		s = System.currentTimeMillis();
		CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> instance.executeMe(2000));
		CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> instance.executeMe(2000));
		
		/****
		 * In this case, it will take time, which is the maximum time taken by
		 * individual computation. So if ‘cf1’ takes 1500 milliseconds and ‘cf2’ takes
		 * 1000, the total time would be 1500 instead of 2500 milliseconds.
		 ***/
		
		CompletableFuture.allOf(cf1, cf2).join();
		e = System.currentTimeMillis();
		System.out.println(e - s);
		
	}
}
