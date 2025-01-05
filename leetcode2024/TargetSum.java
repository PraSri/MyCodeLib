package leetcode2024;

import java.util.Arrays;

public class TargetSum {
  public static void main(String[] args) {

    TargetSum ts = new TargetSum();
    int ans = ts.findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3);
    System.out.println(ans);

  }

  public int findTargetSumWays(int[] nums, int target) {

    // nums[ ] -> sum of positive int set = s1 & negative = s2
    // s1+s2 = s (total)
    // s1-s2 = target
    // s2 = (s - target)/2
    // Therefore this question is modified to “Count Number of subsets with sum (totSum - target)/2

    int sum = Arrays.stream(nums).sum();

    if (sum - target < 0) {
      return 0;
    }

    // has to be even
    if ((sum - target) % 2 == 1) {
      return 0;
    }

    int n = nums.length;
    int currIndex = n - 1;
    int newTarget = (sum - target) / 2;

    int[][] dp = new int[n][newTarget + 1];

    Arrays.stream(dp).forEach(i -> Arrays.fill(i, -1));

    return ways(nums, newTarget, currIndex, dp);

  }

  private int ways(int[] nums, int target, int currIndex, int[][] dp) {

    // if we reach the first index
    if (currIndex == 0) {
      if (target == 0 && nums[0] == 0) {
        return 2;
      }
      if (target == 0 || target == nums[0]) {
        return 1;
      }
      return 0;
    }

    if (dp[currIndex][target] != -1) {
      return dp[currIndex][target];
    }

    int notTaken = ways(nums, target, currIndex - 1, dp);
    int taken = 0;
    if (nums[currIndex] <= target) {
      taken = ways(nums, target - nums[currIndex], currIndex - 1, dp);
    }

    return dp[currIndex][target] = notTaken + taken;

  }

}
