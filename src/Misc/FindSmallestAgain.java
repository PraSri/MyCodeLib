package Misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindSmallestAgain {

	public static void main(String[] args) {

		int[] A = new int[] { 7, 10, 15, 18, 22, 3, 6, 5, 3, 7 };
		int B = 22;
		System.out.println("SOLVED = " + solve(A, B));

	}

	public static int solve(int[] A, int B) {

		int minSum = 0, maxSum = 0, n;
		n = A.length;

		Arrays.sort(A);

		for (int i = 0; i < 3; i++) {
			minSum += A[i];
			maxSum += A[n - 1 - i];
		}

		if (B == 1) {
			return minSum;
		}
		if (B == (n * (n - 1) * (n - 2)) / 6) {
			return maxSum;
		}
		System.out.println("minSum = " + minSum + " maxSum = " + maxSum);
		int s = minSum;
		int e = maxSum;
		while (s <= e) {
			int m = (s + e) / 2;
			System.out.println(m + " _ > " + isPossibleSum(m, A));
			if (isPossibleSum(m, A)) {
				int d = m - minSum + 1;
				System.out.println("d= " + d);
				/*
				 * Here is the Problem : How i get to the Bth smallest sum here i use diff of
				 * mid & minSum but it may have duplicates so how i can get number of triplets
				 * bw minSum & mid and decide which side to go
				 * 
				 */
				if (d == B) {
					return m;
				} else if (d > B) {
					e = m - 1;
				} else {
					s = m + 1;
				}
			}
		}
		return -1;

	}

	private static boolean isPossibleSum(int m, int[] a) {
		int n = a.length;
		Map<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (!hm.containsKey(a[i])) {
				hm.put(a[i], 1);
			} else {
				hm.put(a[i], hm.get(a[i]) + 1);
			}
		}

		int i = 0, j = n - 1;
		while (i < j) {
			int s = a[i];
			int e = a[j];
			hm.put(s, hm.get(s) - 1);
			hm.put(e, hm.get(e) - 1);
			int d = m - (s + e);
			if (hm.containsKey(d)) {
				if (hm.get(d) > 0)
					return true;
			}
			hm.put(s, hm.get(s) + 1);
			hm.put(e, hm.get(e) + 1);
			if ((s + e) >= m) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}
	/*
	 * 
	 * I think you should sort the array then for i=0 and j=i+1 there exists n-2
	 * sums. Similarly for i=0 and j=i+2 there exists n-3 sums. So you must check
	 * where 'B' exists and then find the sum.
	 * 
	 * 
	 */
}
