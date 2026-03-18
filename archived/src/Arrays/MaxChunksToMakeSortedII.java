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

	/********* SIMILAR QUESTION *************/

	/****
	 * Little Ponny and Partition Sort
	 * 
	 * You are given an array of integers A.
	 * 
	 * 
	 * You need to divide it in the maximum number of partitions such that after
	 * sorting each partition individually, The array becomes sorted.
	 * 
	 * 
	 * Return the maximum number of partitions in which you can divide the array.
	 * 
	 * 
	 *****************/

	/*******
	 * A = [2, 0, 1, 2] Given array : 2, 0, 1, 2 Final sorted array : 0, 1, 2, 2
	 * Difference array : -2,1,1,0 sum : -2,-1,0,0
	 * 
	 ****/

	public int solve(int[] A) {
		int[] a = new int[A.length];

		// Make new array and sort it
		for (int i = 0; i < A.length; i++) {
			a[i] = A[i];
		}

		// new sorted array
		Arrays.sort(a);

		// make this sorted array as diff array with original array
		// its kind of giving displacemnt of elemnts valus from original array
		for (int i = 0; i < A.length; i++) {
			a[i] = a[i] - A[i];
		}

		// initialize counter and sum
		int c = 0, sum = 0;

		// calculate sum of displacement array
		// if at any point sum is zero that is the partition point and increase the
		// counter
		for (int i = 0; i < A.length; i++) {
			sum += a[i];
			if (sum == 0) {
				c++;
			}
		}
		return c;
	}
}
