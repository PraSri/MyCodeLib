import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class FiveStarsSellers {

	public static void main(String[] args) {
//		List<List<Integer>> ratings = new ArrayList() {
//			{
//				add(Arrays.asList(4, 4));
//				add(Arrays.asList(1, 2));
//				add(Arrays.asList(3, 6));
//			}
//		};
//
////		System.out.println(ratings);
//
//		System.out.println(fiveStarReviews(ratings, 77));

		System.out.println(routePairs(20, Arrays.asList(Arrays.asList(1, 8), Arrays.asList(2, 7), Arrays.asList(3, 14)),
				Arrays.asList(Arrays.asList(1, 5), Arrays.asList(2, 10), Arrays.asList(3, 14))));
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

	public static class MyPair {
		public int idf, idr;

		public MyPair(int idf, int idr) {
			this.idf = idf;
			this.idr = idr;
		}

		@Override
		public String toString() {
			return "MyPair [idf=" + idf + ", idr=" + idr + "]";
		}
		
	}

	public static List<List<Integer>> routePairs(int maxTravelDist, List<List<Integer>> forwardRouteList,
			List<List<Integer>> returnRouteList) {
		// Write your code here

		HashMap<MyPair, Integer> hm = new HashMap<>();
		int n = forwardRouteList.size();
		int m = returnRouteList.size();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (forwardRouteList.get(i).get(1) + returnRouteList.get(j).get(1) <= maxTravelDist) {
					MyPair p = new MyPair(forwardRouteList.get(i).get(0), returnRouteList.get(j).get(0));
					hm.put(p, forwardRouteList.get(i).get(1) + returnRouteList.get(j).get(1));
				}
			}
		}

		System.out.println(hm);
		List<Map.Entry<MyPair, Integer>> list = new LinkedList<>(hm.entrySet());
		System.out.println(list);
		Collections.sort(list, new Comparator<Map.Entry<MyPair, Integer>>() {
			public int compare(Map.Entry<MyPair, Integer> m1, Map.Entry<MyPair, Integer> m2) {
				return (m1.getValue()).compareTo(m2.getValue());
			}
		});
		System.out.println(list);

		HashMap<MyPair, Integer> temp = new LinkedHashMap<>();
		for (Map.Entry<MyPair, Integer> me : list) {
			temp.put(me.getKey(), me.getValue());
		}
		Set<MyPair> l = new HashSet<>();
		int max = 0;
		for (Map.Entry<MyPair, Integer> me : temp.entrySet()) {
			MyPair p  =me.getKey();
			int d = me.getValue();
			if(d>max) {
				max = d;
				if(!l.isEmpty()){
					l.clear();
				}
				l.add(p);
			}else if(max==d) {
				l.add(p);
			}
		}
//		System.out.println(temp);
//
//		ArrayList<MyPair> pairList = new ArrayList<>();
//		
//		for (Map.Entry<MyPair, Integer> me : temp.entrySet()) {
//			if (me.getValue() > max) {
//				max = me.getValue();
//				pairList.add(me.getKey());
//			}
//		}
//		
//		System.out.println(pairList);

		List<List<Integer>> res = new ArrayList<>();
		for (MyPair p : l) {
			List<Integer> l2 = new ArrayList<>();
			l2.add(p.idf);
			l2.add(p.idr);
			res.add(l2);
		}

		return res;

	}

}
