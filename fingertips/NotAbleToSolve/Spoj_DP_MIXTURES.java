package NotAbleToSolve;

//<https://www.spoj.com/problems/MIXTURES/>
public class Spoj_DP_MIXTURES {

	public static void main(String[] args) {

	}

	static int[][] dp;

	public static int minSmoke(int[] a) {

		int n = a.length;

		dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}

		return helper(a, 0, n - 1);

	}

	private static int helper(int[] a, int i, int j) {
		if (i >= j)
			return 0;

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		dp[i][j] = Integer.MAX_VALUE;

		for (int k = i; k <= j; k++) {
			dp[i][j] = Math.min(dp[i][j], helper(a, i, k) + helper(a, k + 1, j) + sum(a, i, k) * sum(a, k + 1, j));
		}

		return dp[i][j];
	}

	private static int sum(int[] a, int i, int j) {

		int s = 0;
		for (int k = i; k <= j; k++) {
			s += a[k];
		}

		return s % 100;

	}

}
