package Arrays;

import java.util.Arrays;

public class MaxChunksToMakeSortedII {

	/*
	 * Given an array of integers (not necessarily distinct) A, if we split the
	 * array into some number of "chunks" (partitions), and individually sort each
	 * chunk. After concatenating them, the result equals the sorted array.
	 * 
	 * What is the most number of chunks we could have made?
	 * 
	 */

	public int solve(int[] A) {
		int[] a = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			a[i] = A[i];
		}
		Arrays.sort(a);
		for (int i = 0; i < A.length; i++) {
			a[i] = a[i] - A[i];
		}
		int c = 0, sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += a[i];
			if (sum == 0) {
				c++;
			}
		}
		return c;
	}
}
