package Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TheShipCompany {

	public static void main(String[] args) {

		TheShipCompany s = new TheShipCompany();
		int[] x = s.solve(4, 3, new int[] { 2, 1, 1 });
		System.out.println(x[0] + " " + x[1]);

	}

	public int[] solve(int A, int B, int[] C) {

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		};

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(comparator);

		for (int i : C) {
			minHeap.add(i);
			maxHeap.add(i);
		}

		int max = 0;
		int min = 0;

		while (A-- > 0) {

			int x = maxHeap.poll();
			max += x;
			if (x - 1 > 0) {
				maxHeap.add(x - 1);
			}
			int y = minHeap.poll();
			min += y;
			if (y - 1 > 0) {
				minHeap.add(y - 1);
			}

		}

		return new int[] { max, min };

	}

}
