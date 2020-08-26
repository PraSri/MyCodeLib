package DynamicProgramming;

public class LongestPalindromicSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solve(String A) {
		int n = A.length();
		boolean dp = true;
		if (dp) {
			int[][] memo = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					memo[i][j] = -1;
				}
			}
			return lpsDp(A, memo, 0, n - 1);
		}
		// TODO : bottom up approach
		return lpsRecursion(A, 0, n - 1);

	}

	private int lpsDp(String a, int[][] memo, int i, int j) {
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		if (i > j) {
			return 0;
		}
		if (i == j) {
			return 1;
		}
		if (j == i + 1 && a.charAt(i) == a.charAt(j)) {
			return 2;
		}
		if (a.charAt(i) == a.charAt(j)) {
			memo[i][j] = lpsDp(a, memo, i + 1, j - 1) + 2;
		} else {
			memo[i][j] = Math.max(lpsDp(a, memo, i, j - 1), lpsDp(a, memo, i + 1, j));
		}

		return memo[i][j];

	}

	private int lpsRecursion(String a, int i, int j) {
		if (i == j) {
			return 1;
		}
		if (j == i + 1 && a.charAt(i) == a.charAt(j)) {
			return 2;
		}
		if (a.charAt(i) == a.charAt(j)) {
			return lpsRecursion(a, i + 1, j - 1) + 2;
		}
		return Math.max(lpsRecursion(a, i, j - 1), lpsRecursion(a, i + 1, j));
	}

}
