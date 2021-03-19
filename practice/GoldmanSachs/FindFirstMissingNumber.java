package GoldmanSachs;

import java.util.Arrays;

public class FindFirstMissingNumber {

	public static void main(String[] args) {

		System.out.println(getFirstMissUsingSort(new int[] { 7, 8, 9, 11, 12 }));

	}

	public static int getFirstMissUsingSort(int[] A) {
		if (A.length == 0)
			return 1;
		Arrays.sort(A);
		int i;
		for (i = 0; i < A.length; i++) {
			if (A[i] > 0)
				break;
		}
		if (i == A.length || A[i] > 1)
			return 1;
		for (; i < A.length - 1; i++) {
			if (A[i + 1] - A[i] > 1)
				return A[i] + 1;
		}
		return A[i] + 1;
	}

	// https://leetcode.com/problems/first-missing-positive/discuss/17214/Java-simple-solution-with-documentation

	/*****
	 * Most crucial observation is If an integer is missing it must be in the range
	 * [1..n] if an integer is not missing then the answer is n+1.
	 *******/
	public static int getFirstMiss(int[] a) {

		int n = a.length;

		for (int i = 0; i < n; i++) {
			if (a[i] < 0 || a[i] > n) {
				a[i] = n + 1;
			}
		}

		for (int i = 0; i < n; i++) {
			int index = Math.abs(a[i]);

			if (index > n) {
				continue;
			}

			index--;

			if (a[index] > 0) {
				a[index] = a[index] * (-1);
			}

		}

		for (int i = 0; i < n; i++) {
			if (a[i] >= 0) {
				return i + 1;
			}
		}

		return n + 1;
	}

}
