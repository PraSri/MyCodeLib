package leetcode2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnesAndZeroes {

  public static void main(String[] args) {

    OnesAndZeroes oz = new OnesAndZeroes();

    String[] s = new String[] {"10", "0001", "111001", "1", "0"};
    int ans = oz.findMaxForm(s, 5, 3);

    System.out.println(ans);

  }

  /**
   * You are given an array of binary strings strs and two integers m and n.
   * <p>
   * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
   * <p>
   * A set x is a subset of a set y if all elements of x are also elements of y.
   * <p>
   * <p>
   * <p>
   * Example 1:
   * <p>
   * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
   * Output: 4
   * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
   * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
   * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
   * Example 2:
   * <p>
   * Input: strs = ["10","0","1"], m = 1, n = 1
   * Output: 2
   * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
   **/

  public int findMaxForm(String[] strs, int m, int n) {

    List<Pair> count = new ArrayList<>();

    for (String s : strs) {
      int ones = 0;
      int zeros = 0;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '1') {
          ones++;
        } else {
          zeros++;
        }
      }
      count.add(new Pair(ones, zeros));
    }

    int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

    Arrays.stream(dp).flatMap(Arrays::stream).forEach(i -> Arrays.fill(i, -1));

    return solve(count, m, n, 0, dp);

  }

  private int solve(List<Pair> count, int m, int n, int i, int[][][] dp) {

    // base case
    if (i == count.size() || (m == 0 && n == 0)) {
      return 0;
    }

    if (count.get(i).nZeros > m || count.get(i).nOnes > n) {
      return solve(count, m, n, i + 1, dp);
    }

    if (dp[i][m][n] != -1) {
      return dp[i][m][n];
    }

    // pick or not pick

    int include = 1 + solve(count, m - count.get(i).nZeros, n - count.get(i).nOnes, i + 1, dp);
    int exclude = solve(count, m, n, i + 1, dp);

    int ans = Math.max(include, exclude);
    dp[i][m][n] = ans;
    return ans;
  }

  public static class Pair {
    public int nOnes;
    public int nZeros;

    Pair(int nOnes, int nZeros) {
      this.nOnes = nOnes;
      this.nZeros = nZeros;
    }
  }

}
