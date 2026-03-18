package searchingAlgorithms;

import java.util.Arrays;

public class HireCandidates {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * A company wants to hire maximum number of students from a college. The
	 * company cannot spend more than A amount to hire students.
	 * 
	 * There are N students numbered 1 to N and the base cost to hire students is
	 * given by an integer array B of size N.
	 * 
	 * The final cost of students depends on the number of students the company
	 * hire. If a company hires X students then the final cost to hire the ith
	 * student will be B[i] + (i*X) (where i is the number of that student).
	 * 
	 * Find the maximum value of X and the minimum cost to hire X people.
	 * 
	 * NOTE: Cost to hire students X should be less than equal to A.
	 * 
	 */

	public int[] solve(int A, int[] B) {

		long n = B.length;

		long s = 0, e = n;

		long ans = -1, minCost = Long.MAX_VALUE;

		while (s <= e) {
			long mid = (s + e) / 2;
			long[] a = new long[(int) n];
			for (int i = 0; i < n; i++) {
				a[i] = B[i] + (i + 1) * mid;
			}
			Arrays.sort(a);
			long sum = 0;
			for (int i = 0; i < mid; i++) {
				sum = sum + a[i];
			}
			if (sum < A) {
				ans = mid;
				minCost = sum;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return new int[] { (int) ans, (int) minCost };

	}

}
