package Greedy;

import java.util.Arrays;

public class DistributeCandy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int candy(int[] A) {
		int n = A.length;

		int[] a = new int[n];

		Arrays.fill(a, 1);

		for (int i = 1; i < n; i++) {
			if (A[i - 1] < A[i]) {
				a[i] = a[i - 1] + 1;
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			if (A[i - 1] > A[i]) {
				a[i - 1] = Math.max(a[i - 1], a[i] + 1);
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans += a[i];
		}

		return ans;

	}
}
