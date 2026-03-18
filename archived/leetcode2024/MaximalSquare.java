package leetcode2024;

import java.util.Arrays;

public class MaximalSquare {

  /**
   * *Given an m x n binary matrix filled with 0's and 1's,
   * find the largest square containing only 1's and return its area.
   * Input: matrix = [
   * ["1","0","1","0","0"],
   * ["1","0","1","1","1"],
   * ["1","1","1","1","1"],
   * ["1","0","0","1","0"]
   * ]
   * Output: 4
   */

  public static void main(String[] args) {

  }

  public int maximalSquare(char[][] matrix) {

    int n = matrix.length;
    int m = matrix[0].length;

    int[][] dp = new int[n + 1][m + 1];


    int max = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (matrix[i - 1][j - 1] == '1') {
          dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
          max = Math.max(max, dp[i][j]);
        }
      }
    }

    return max * max;

  }


}
