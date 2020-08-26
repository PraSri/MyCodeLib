package Heaps;

import java.util.PriorityQueue;

public class MaximumArraySumAfterBNegations {

	public static void main(String[] args) {
	}

	public int solve(int[] A, int B) {

		int sum = 0;

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		for (int i = 0; i < A.length; i++) {
			minHeap.add(A[i]);
		}

		while (B > 0) {

			int x = minHeap.poll();

			minHeap.add(x * -1);

			B--;
		}

		while (!minHeap.isEmpty()) {
			sum += minHeap.poll();
		}

		return sum;

	}

}
