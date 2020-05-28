package Sorting;

import java.util.Arrays;

public class MAXMIN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Given an array of integers A and an integer B, find and return the difference
	 * of B'th max element and B'th min element of the array A.
	 * 
	 * 
	 */

	public int solve(int[] A, int B) {
		Arrays.sort(A);

		int n = A.length;

		int min = A[B - 1];

		int max = A[n - B];

		return max - min;
	}

}
