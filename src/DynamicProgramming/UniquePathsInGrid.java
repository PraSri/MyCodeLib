package DynamicProgramming;

public class UniquePathsInGrid {

	public static void main(String[] args) {

	}

	public int uniquePathsWithObstacles(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		if (n == 1 && m == 1 && A[0][0] == 1) {
			return 0;
		}
		if (n == 1 && m == 1 && A[0][0] == 0) {
			return 1;
		}
		int[][] dp = new int[n + 1][m + 1];
		if (A[0][0] == 0) {
			dp[0][0] = 1;
		}

		for (int i = 1; i < m; i++) {
			if (A[0][i] == 0) {
				dp[0][i] = dp[0][i - 1];
			}
		}

		for (int i = 1; i < n; i++) {
			if (A[i][0] == 0) {
				dp[i][0] = dp[i - 1][0];
			}
		}

		dp[0][0] = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (A[i][j] == 0)
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				else
					dp[i][j] = 0;
			}
		}

		return dp[n - 1][m - 1];
	}

}
