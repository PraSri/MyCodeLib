package Heaps;

import java.util.PriorityQueue;

public class MishaAndCandies {

	public static void main(String[] args) {
	}

	public int solve(int[] A, int B) {
		int n = A.length;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			minHeap.add(A[i]);
		}
		int consumed = 0;
		while (!minHeap.isEmpty()) {
			int choose = minHeap.poll();
			if (choose > B) {
				break;
			}
			int eat = choose / 2;
			consumed += eat;
			if (minHeap.isEmpty())
				break;
			int otherBox = minHeap.poll() + (eat + (choose % 2));
			if (otherBox <= B)
				minHeap.add(otherBox);
		}
		return consumed;
	}

}
