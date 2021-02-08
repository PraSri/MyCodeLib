import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FiveStarsSellers {

	public static void main(String[] args) {
		List<List<Integer>> ratings = new ArrayList() {
			{
				add(Arrays.asList(4, 4));
				add(Arrays.asList(1, 2));
				add(Arrays.asList(3, 6));
			}
		};

//		System.out.println(ratings);

		System.out.println(fiveStarReviews(ratings, 77));
	}

	/******
	 * Heap sorted by : five start review# / total review# - (five start review# +
	 * 1) / (total review# + 1). Then take a new one from heap, add the number of
	 * five star reviews, recalculate the rating and put it back to heap. Repeat
	 * this operation until reach the threshold
	 **********/

	public static class Product {
		public int rating = 0;
		public int total = 0;
		public double rateChange = 0.0;

		public Product(int r, int t) {
			rating = r;
			total = t;
			double currRate = (double) rating / total;
			double newRate = (double) ((rating + 1) / (total + 1));
			rateChange = newRate - currRate;
		}

	}

	public static int fiveStarReviews(List<List<Integer>> products, int threshold) {

		if (products == null || products.size() == 0) {
			return 0;
		}

		Queue<Product> pq = new PriorityQueue<>(
				(product1, product2) -> Double.compare(product1.rateChange, product2.rateChange));

		int steps = 0;
		int currRate = 0;

		for (List<Integer> p : products) {
			pq.add(new Product(p.get(0), p.get(1)));
			currRate += p.get(0) / p.get(1);

		}

		int n = products.size();
		currRate /= n;
//		System.out.println("HELLO");
		while (currRate * 100 < threshold) {

			Product p = pq.poll();

			currRate += p.rateChange / n;

			steps++;

			pq.add(new Product(p.rating + 1, p.total + 1));

		}
		return steps;

	}

}
