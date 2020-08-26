package DynamicProgramming;

public class MaximumSum {

	public static void main(String[] args) {
	}

	public int solve(int[] A, int B, int C, int D) {

		int ans = Integer.MIN_VALUE;

		int n = A.length;

		int[] l = new int[n];
		int[] r = new int[n];

		l[0] = A[0] * B;

		r[n - 1] = A[n - 1] * D;

		for (int i = 1; i < n; i++) {
			l[i] = Math.max(l[i - 1], A[i] * B);
		}

		for (int i = n - 2; i >= 0; i--) {
			r[i] = Math.max(r[i + 1], A[i] * D);
		}

		for (int i = 0; i < n; i++) {
			ans = Math.max(ans, l[i] + (A[i] * C) + r[i]);
		}

		return ans;

	}

}
