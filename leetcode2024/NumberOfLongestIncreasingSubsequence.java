package leetcode2024;

import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence {

  public static void main(String[] args) {

  }

  public int findNumberOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    // initial LIS len is 1
    Arrays.fill(dp, 1);
    int max = 1;
    int res = 0;
    int[] count = new int[n];
    Arrays.fill(count, 1);

    for (int curr = 0; curr < n; curr++) {
      for (int prev = 0; prev < curr; prev++) {
        if (nums[prev] < nums[curr] && dp[curr] < 1 + dp[prev]) {
          dp[curr] = 1 + dp[prev];
          count[curr] = count[prev];
        } else if (dp[curr] == 1 + dp[prev]) {
          count[curr] += count[prev];
        }
      }
      max = Math.max(max, dp[curr]);
    }

    for (int i = 0; i < n; i++) {
      if (dp[i] == max) {
        res += count[i];
      }
    }


    return res;


  }

}
