package Misc;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreading {

	public static void main(String args[]) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
			try {
				return foo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}, executorService);
		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
			try {
				return loo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}, executorService);

		Runnable runnableTask = () -> {
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Callable<String> callableTask = () -> {
			TimeUnit.MILLISECONDS.sleep(300);
			System.out.println("HELLO BHAI IN CALLABLE TASK : " + Thread.currentThread().getName() + " : "
					+ Thread.currentThread().getId());
			return "Task's execution";
		};
		executorService.submit(callableTask);
		executorService.submit(runnableTask);

		System.out.println(executorService.isTerminated());
		System.out.println(cf1.isDone());
		String s = cf1.join();
		String s2 = cf2.join();
		System.out.println(s + " - " + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
		executorService.submit(callableTask);
		executorService.submit(runnableTask);
		System.out.println(s2 + " - " + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
		executorService.submit(callableTask);
		executorService.submit(runnableTask);
		System.out.println(s + " - " + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
		System.out.println(s2 + " - " + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
		System.out.println(executorService.isTerminated());
		System.out.println(cf1.isDone());

		executorService.submit(callableTask);
		executorService.submit(runnableTask);

	}

	public static String foo() throws InterruptedException {
		Thread.sleep(4000);
		return "Running completable future in foo";
	}

	public static String loo() throws InterruptedException {
		Thread.sleep(3000);
		return "Running completable future in  loo";
	}

}
