package leetcode2024;

import java.util.Arrays;

public class DungeonGame {

  public static void main(String[] args) {
    DungeonGame dg = new DungeonGame();
    int[][] d = new int[][] {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
    int ans = dg.calculateMinimumHP(d);
    System.out.println(ans);
    System.out.println("Bottom up : " + dg.solve2(d));
    System.out.println("Bottom up O(m) space : " + dg.solve3(d));
  }

  /***
   * given matrix m*n
   * source = 0,0
   * dest = m,n
   * 2 ways -> right or down
   * min health required
   * each cell can be -ve, +ve or 0
   * recursion with boundary cases
   * return min of solving right or down ways
   * if health goes below 0, it dies
   * so health has to be 1 + (-mat[i][j])
   *
   * [[-10]]                     : ans  = 1 + (-(-10)) = 11
   * [[10]]                      : ans  = 1 as we still need 1 health at first place to get there
   * [[-2,-3,3,-5,-10]]          : ans = 1 + (-(-17)) = 18 same as 1st case
   *
   * base cases :
   * out of bound
   * if i == n || j == m return MAX
   *
   * reach destination
   * if i == n-1 && j == m-1 return Math.max(-mat[i][j] + 1, 1)
   *
   *possible paths:
   * 1. rightward -> r = recur(i, j+1)
   * 2. downward -> d = recur(i+1, j)
   *
   * take min of both & add current health required
   * ans = min(r,d) + (-mat[i][j])
   *
   * final ans need to be positive
   * ans = Math.max(ans, 1)
   *
   * */

  public int calculateMinimumHP(int[][] dungeon) {
    int n = dungeon.length;
    int m = dungeon[0].length;
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], -1);
    }
    return solve(0, 0, dungeon, n, m, dp);

  }

  private int solve(int i, int j, int[][] mat, int n, int m, int[][] dp) {

    // base case

    if (i == n || j == m) {
      return Integer.MAX_VALUE;
    }

    if (i == n - 1 && j == m - 1) {
      return Math.max(1, 1 - mat[i][j]);
    }

    if (dp[i][j] != -1) {
      return dp[i][j];
    }

    // possible picks
    int goRight = solve(i, j + 1, mat, n, m, dp);
    int goDown = solve(i + 1, j, mat, n, m, dp);

    int ans = Math.min(goRight, goDown) - mat[i][j];

    ans = Math.max(ans, 1);

    dp[i][j] = ans;

    return ans;

  }

  public int solve2(int[][] dungeon) {
    int n = dungeon.length;
    int m = dungeon[0].length;
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[n][m - 1] = 1;
    dp[n - 1][m] = 1;

    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        int need = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
        dp[i][j] = Math.max(1, need);
      }
    }

    return dp[0][0];

  }

  public int solve3(int[][] dungeon) {
    int n = dungeon.length;
    int m = dungeon[0].length;
    int[] dp = new int[m + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[m - 1] = 1;
    dp[m] = 1;

    for (int i = n - 1; i >= 0; i--) {
      if (i == n - 2) {
        dp[m] = Integer.MAX_VALUE;
      }
      for (int j = m - 1; j >= 0; j--) {
        int need = Math.min(dp[j + 1], dp[j]) - dungeon[i][j];
        dp[j] = Math.max(1, need);
      }
    }

    return dp[0];

  }

}
