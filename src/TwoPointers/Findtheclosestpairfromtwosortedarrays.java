package TwoPointers;

import java.util.Arrays;

public class Findtheclosestpairfromtwosortedarrays {

	public static void main(String[] args) {
		int[] A = new int[] { 5, 10, 20 };
		int[] B = new int[] { 1, 2, 30 };
		int C = 13;
		int[] ans = solveEfficient(A, B, C);
		for (int x : ans) {
			System.out.print(x + " ");
		}
	}

	public static int[] solve(int[] A, int[] B, int C) {
		int[] ans = new int[2];
		int n = A.length;
		int m = B.length;
		int i = 0;
		int min = Integer.MAX_VALUE;
		while (i < n) {
			int j = i;
			while (j < m) {
				int sum = A[i] + B[j];
				if (sum == C) {
					ans[0] = A[i];
					ans[1] = B[j];
					i = n + 1;// to break the outer loop
					break;
				} else if (Math.abs(sum - C) < min) {
					min = Math.abs(sum - C);
					ans[0] = A[i];
					ans[1] = B[j];
				}
				j++;
			}
			i++;
		}
		return ans;
	}

	public static int[] solveEfficient(int[] A, int[] B, int C) {
		int[] ans = new int[2];
		int n = A.length;
		int m = B.length;
		int diff = Integer.MAX_VALUE;
		int l = 0;
		int r = m - 1;
		while (l < n && r >= 0) {
			if (Math.abs(A[l] + B[r] - C) < diff) {
				diff = Math.abs(A[l] + B[r] - C);
				ans[0] = A[l];
				ans[1] = B[r];
			}
			if (A[l] + B[r] > C) {
				r--;
			} else {
				l++;
			}
		}
		return ans;
	}

}
