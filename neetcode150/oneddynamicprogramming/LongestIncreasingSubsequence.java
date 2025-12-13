package oneddynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int[][] dp = new int[n+1][n+1];

        for(int i = 0;i <n;i++) {
            Arrays.fill(dp[i], -1);
        }

        return len(nums, 0, -1, dp);
    }

    private int len(int[] nums, int curr, int prev, int[][] dp) {
        if(curr == nums.length) {
            return 0;
        }
        if(dp[curr][prev+1] != -1) {
            return dp[curr][prev+1];
        }

        int exclude = len(nums, curr+1, prev, dp);
        int include = Integer.MIN_VALUE;
        if(prev == -1 || nums[curr]>nums[prev]) {
            include = 1 + len(nums, curr+1, curr, dp);
        }

        return dp[curr][prev+1] = Math.max(exclude, include);

    }
}
