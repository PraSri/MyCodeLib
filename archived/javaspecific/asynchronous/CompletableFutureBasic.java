package asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureBasic {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFutureBasic completableFutureBasic = new CompletableFutureBasic();
		System.out.println(completableFutureBasic.getStringFuture().get());
	}

	Future<String> getStringFuture() {
		CompletableFuture<String> completableFuture = new CompletableFuture<String>();

		Executors.newCachedThreadPool().submit(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			completableFuture.complete("HELLO");
			return null;
		});

		return completableFuture;
	}
	
	

}
