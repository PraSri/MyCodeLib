package DynamicProgramming;

public class EditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int minDistance(String A, String B) {

		int n = A.length();
		int m = B.length();

		boolean dp = true;

		if (dp) {
			return minEditsDP(A, B, n, m);
		}

		return getMinEditsRecursive(A, B, n, m);

	}

	public int minEditsDP(String a, String b, int n, int m) {

		int[][] dp = new int[n + 1][m + 1];

		dp[0][0] = 0;

		/*
		 * row for A column for B
		 * 
		 */

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + getMinOfThree(

							dp[i][j - 1], // insert
							dp[i - 1][j], // remove
							dp[i - 1][j - 1] // replace

					);

				}
			}
		}

		return dp[n][m];

	}

	public int getMinEditsRecursive(String a, String b, int n, int m) {

		if (n == 0) {
			return m;
		}

		if (m == 0) {
			return n;
		}

		if (a.charAt(n - 1) == b.charAt(m - 1)) {
			return getMinEditsRecursive(a, b, n - 1, m - 1);
		}

		return 1 + getMinOfThree(getMinEditsRecursive(a, b, n - 1, m) // remove
				, getMinEditsRecursive(a, b, n, m - 1) // insert
				, getMinEditsRecursive(a, b, n - 1, m - 1)// repalce
		);

	}

	public int getMinOfThree(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));

	}

}
