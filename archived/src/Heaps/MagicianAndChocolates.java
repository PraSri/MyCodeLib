package Heaps;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MagicianAndChocolates {

	public static void main(String[] args) {
		System.out.println(nchoc(3, new int[] { 6, 5 }));
	}

	/*
	 * Given N bags, each bag contains Bi chocolates. There is a kid and a magician.
	 * In one unit of time, kid chooses a random bag i, eats Bi chocolates, then the
	 * magician fills the ith bag with floor(Bi/2) chocolates.
	 * 
	 * Find the maximum number of chocolates that kid can eat in A units of time.
	 */

	public static int nchoc(int A, int[] B) {

		int n = B.length;

		long mod = 1000000007;

		Comparator<Integer> c = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}

		};

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < n; i++) {
			maxHeap.add(B[i]);
		}
		long ct = 0;
		while (A > 0) {
			int x = maxHeap.poll();
			ct = (ct % mod + x % mod) % mod;
			maxHeap.add((int) Math.floor(x / 2));
			A--;
		}

		return (int) ct;

	}

}
