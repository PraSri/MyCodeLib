package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureBasicExampleSquareCalculator {

	private ExecutorService executorService = Executors.newSingleThreadExecutor();

	public Future<Integer> calculate(Integer input) {
		return executorService.submit(() -> {

			Thread.sleep(5000);

			return input * input;
		});
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureBasicExampleSquareCalculator ob = new FutureBasicExampleSquareCalculator();

		Future<Integer> future = ob.calculate(2);

		while (!future.isDone()) {
			System.out.println("Calculating.............!!!!");
			Thread.sleep(3000);
		}

		Integer output = future.get();

		System.out.println(output);
	}

}
