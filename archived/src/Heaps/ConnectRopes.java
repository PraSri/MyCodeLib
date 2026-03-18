package Heaps;

import java.util.PriorityQueue;

public class ConnectRopes {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 1, 2, 3, 4, 5 }));
	}

	/*
	 * Given an array of integers A representing the length of ropes.
	 * 
	 * You need to connect these ropes into one rope. The cost of connecting two
	 * ropes is equal to the sum of their lengths.
	 * 
	 * Find and return the minimum cost to connect these ropes into one rope.
	 * 
	 */

	public static int solve(int[] A) {

		int n = A.length;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			minHeap.add(A[i]);
		}
		int cost = 0;
		int x, y;
		while (n - 1 > 0) {
			x = minHeap.poll();
			y = minHeap.poll();
			int z = x + y;
			cost += z;
			minHeap.add(z);
			n--;
		}

		return cost;

	}

}
