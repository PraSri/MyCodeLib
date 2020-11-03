package Amazon;

import java.util.PriorityQueue;

public class ConnectRopesInSingleRopeWithMinCost_Heaps {

	public static void main(String[] args) {

	}

	/*******************
	 * Given an array of integers A representing the length of ropes.
	 * 
	 * You need to connect these ropes into one rope. The cost of connecting two
	 * ropes is equal to the sum of their lengths.
	 * 
	 * Find and return the minimum cost to connect these ropes into one rope.
	 ***********************/

	/********
	 * This question can be asked like = merge two files and save the merged file
	 * ..do till single file is left ..and do it in minimum cost
	 * 
	 *******/

	/***************
	 * Approach of this question is very important ******** give it a try when you
	 * need minimum cost of integrating things to make it single entity
	 ******/

	public static int connectRopes(int[] a) {

		int n = a.length;

		/****** By default Priority queue is min heap in java ********/

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		for (int i : a) {
			minHeap.add(i);
		}
		int cost = 0;
		while (minHeap.size() != 1) {

			int newLength = minHeap.poll() + minHeap.poll();

			cost += newLength;

			minHeap.add(newLength);

		}

		return cost;

	}

}
