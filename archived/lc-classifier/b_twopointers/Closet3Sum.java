package b_twopointers;

import java.util.Arrays;

public class Closet3Sum {

	// https://leetcode.com/problems/3sum-closest/

	public static void main(String[] args) {

	}

	public static int threeSumClosest(int[] A, int B) {

		int n = A.length;
		int sum = 0, close = A[0] + A[1] + A[n - 1];

		Arrays.sort(A);

		for (int i = 0; i < n - 2; i++) {
			int p1 = i + 1;
			int p2 = n - 1;
			while (p1 < p2) {
				sum = A[i] + A[p1] + A[p2];
				if (Math.abs(B - sum) < Math.abs(B - close)) {
					close = sum;
				}
				if (sum > B) {
					p2--;
				} else {
					p1++;
				}
			}
		}

		return close;

	}

}
