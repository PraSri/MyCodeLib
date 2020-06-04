package Sorting;

import java.util.Arrays;
import java.util.Collections;

public class MAXIMUM_AND_MINIMUM_MAGIC {

	public static void main(String[] args) {

		for (int i : solve(new int[] { 3, 11, -1, 5 })) {
			System.out.print(": " + i + " ");
		}

	}

	public static int mod = 1000000007;

	public static int[] solve(int[] A) {

		int n = A.length;

		Arrays.sort(A);// 1 2 3 4 5 6

		Integer[] a = new Integer[n / 2];
		Integer[] b = new Integer[n / 2];

		/*
		 * 3,11,-1,5 -1,3,5,11
		 * 
		 * a = -1 3 b = 5 11 b = 11 5
		 * 
		 * 11+1 + 3-5=10 11-3 + 5+1=14 a = 11 , 5 b = 3 , -1
		 * 
		 * 
		 */

		int i,j=0,k=0;
		for (i = 0; i < n; i++) {
			if(i<n/2)
			a[j++] = A[i];
			else
				b[k++]=A[i];
		}


		Arrays.sort(b, Collections.reverseOrder());// 6 5 4

		int MaxMagicNumber = getMagicNumber(a, b);

		// 1 3 5
		// 2 4 6
		extracted(A, n, a, b);

		int MinMagicNumber = getMagicNumber(a, b);

		return new int[] { MaxMagicNumber, MinMagicNumber };

	}

	/**
	 * @param A
	 * @param n
	 * @param a
	 * @param b
	 */
	private static void extracted(int[] A, int n, Integer[] a, Integer[] b) {
		int j = 0, k = 0;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				a[j++] = A[i];
			} else {
				b[k++] = A[i];
			}
		}
	}

	private static int getMagicNumber(Integer[] a, Integer[] b) {
		int m = 0;
		for (int i = 0; i < a.length; i++) {
			m = (m % mod + (Math.abs(a[i] - b[i])) % mod) % mod;
		}

		return m % mod;
	}

}
