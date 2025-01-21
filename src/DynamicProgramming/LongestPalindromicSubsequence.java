package DynamicProgramming;

public class LongestPalindromicSubsequence {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  public static int lcs(String a, String b) {
    int m = a.length();
    int n = b.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        }
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[m][n];
  }

  public int longestPalindromeSubseq(String s) {
    String reversed = new StringBuilder(s).reverse().toString();
    return lcs(s, reversed);
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
