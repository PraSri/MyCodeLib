package oneddynamicprogramming;

import java.util.*;

public class HouseRobber {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return solve(nums, 0, dp);
    }

    private int solve(int[] nums, int i, int[] dp) {
        // base case
        if (i >= nums.length) {
            return 0;
        }

        // already computed
        if (dp[i] != -1) {
            return dp[i];
        }

        // choices
        int rob = nums[i] + solve(nums, i + 2, dp);
        int skip = solve(nums, i + 1, dp);

        dp[i] = Math.max(rob, skip);
        return dp[i];
    }

    public static class HouseRobber2 {
        public int rob(int[] nums) {

            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            int[] dp1 = new int[n];
            int[] dp2 = new int[n];
            Arrays.fill(dp1, -1);
            Arrays.fill(dp2, -1);

            return Math.max(
                    solve2(nums, 0, n - 2, dp1),
                    solve2(nums, 1, n - 1, dp2)
            );

        }

        private int solve2(int[] nums, int s, int e, int[] dp) {
            // base case
            if (s > e) {
                return 0;
            }

            // already computed
            if (dp[s] != -1) {
                return dp[s];
            }

            // choices
            int rob = nums[s] + solve2(nums, s + 2, e, dp);
            int skip = solve2(nums, s + 1, e, dp);

            dp[s] = Math.max(rob, skip);
            return dp[s];
        }
    }

}
