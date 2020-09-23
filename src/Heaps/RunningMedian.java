package Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RunningMedian {

	public static void main(String[] args) {
		for (int i : solve(new int[] { 5, 17, 100, 11 })) {
			System.out.print(i + " ");
		}

	}

	public static int[] solve(int[] A) {
		int n = A.length;
		int[] res = new int[n];
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		};
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(comparator);
		maxHeap.add(A[0]);
		res[0] = A[0];
		int median = A[0];
		for (int i = 1; i < n; i++) {
			int newEntry = A[i];
			if (maxHeap.size() > minHeap.size()) {
				if (newEntry > median) {

					minHeap.add(newEntry);

				} else {

					minHeap.add(maxHeap.poll());
					maxHeap.add(newEntry);

				}
				median = maxHeap.size() > minHeap.size() ? minHeap.peek() : maxHeap.peek();
			} else if (maxHeap.size() == minHeap.size()) {
				if (newEntry > median) {
					minHeap.add(newEntry);
					median = minHeap.peek();
				} else {
					maxHeap.add(newEntry);
					median = maxHeap.peek();
				}
			} else {
				if (newEntry > median) {

					maxHeap.add(minHeap.poll());
					minHeap.add(newEntry);

				} else {

					maxHeap.add(newEntry);
				}

				median = maxHeap.size() > minHeap.size() ? minHeap.peek() : maxHeap.peek();

			}
			res[i] = median;
		}

		return res;

	}

}
