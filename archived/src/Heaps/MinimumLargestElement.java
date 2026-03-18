package Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumLargestElement {

	public static void main(String[] args) {
		MinimumLargestElement h = new MinimumLargestElement();

		System.out.println(h.solve(new int[] {5, 7, 8}, 9));

	}

	public class Pair {

		public int initialValue, currentValue, sum;

		public Pair(int initialValue, int currentValue) {
			this.initialValue = initialValue;
			this.currentValue = currentValue;
			this.sum = this.currentValue;//this.initialValue + this.currentValue;
		}

	}

	public int solve(int[] A, int B) {

		Comparator<Pair> comparator = new Comparator<Pair>() {

			@Override
			public int compare(Pair a, Pair b) {
				return a.sum - b.sum;
			}
		};
		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(comparator);

		int n = A.length;

		for (int i = 0; i < n; i++) {
			minHeap.add(new Pair(A[i], A[i]));
		}

		for (int i = 0; i < B; i++) {
			Pair p = minHeap.poll();
			int iv = p.initialValue;
			int cv = p.currentValue + iv;
			minHeap.add(new Pair(iv, cv));
		}
		int max = Integer.MIN_VALUE;
		while(!minHeap.isEmpty()) {
			Pair p = minHeap.poll();
			if(p.currentValue>max) {
//				System.out.println(max);
				max = p.currentValue;
			}
		}

		return max;

	}
}
