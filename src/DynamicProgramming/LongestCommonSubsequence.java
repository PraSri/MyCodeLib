package DynamicProgramming;

public class LongestCommonSubsequence {
	public int solve(String A, String B) {
		int n = A.length();
		int m = B.length();
		boolean dp = true;
		if (dp) {
			return lcsDp(A, B, n, m);
		}
		return lcsRecursion(A, B, n, m);

	}

	private int lcsDp(String a, String b, int n, int m) {
		int[][] lcs = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 || j == 0) {
					lcs[i][j] = 0;
				} else if (a.charAt(i - 1) == b.charAt(j - 1)) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}

		return lcs[n][m];
	}

	private int lcsRecursion(String a, String b, int n, int m) {
		if (m == 0 || n == 0)
			return 0;
		if (a.charAt(n - 1) == b.charAt(m - 1)) {
			return 1 + lcsRecursion(a, b, n - 1, m - 1);
		} else
			return Math.max(lcsRecursion(a, b, n - 1, m), lcsRecursion(a, b, n, m - 1));
	}
}
