package leetcode2024;

import java.util.Arrays;

public class KeysKeyboard {

  int[][] dp;
  private int n;

  public int minSteps(int n) {

    if (n == 1) {
      return 0;
    }

    this.n = n;

    dp = new int[n + 1][n / 2 + 1];

    return 1 + solve(1, 1);

  }

  // here i -> currLen, j -> pasteLen
  private int solve(int i, int j) {
    // base cases
    if (i == n) {
      return 0;
    }
    if (i > n) {
      return 1000;
    }

    if (dp[i][j] != 0) {
      return dp[i][j];
    }

    // operations: 1. Copy ALL + paste, 2. Paste only
    // op1 - copy all + paste
    int op1 = 2 + solve(i * 2, i);
    // op2 - paste only
    int op2 = 1 + solve(i + j, j);

    dp[i][j] = Math.min(op1, op2);

    return dp[i][j];
  }


}
