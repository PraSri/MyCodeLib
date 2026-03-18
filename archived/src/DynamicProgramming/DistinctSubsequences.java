package DynamicProgramming;

public class DistinctSubsequences {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int numDistinct(String A, String B) {
		int n = A.length();
		int m = B.length();

		int[][] dp = new int[m + 1][n + 1];

		dp[0][0] = 1;

		for (int i = 1; i <= n; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (B.charAt(i - 1) == A.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}

		return dp[m][n];
	}

}
