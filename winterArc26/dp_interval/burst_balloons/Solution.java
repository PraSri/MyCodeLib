package dp_interval.burst_balloons;

public class Solution {

    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] vals = new int[n];
        vals[0] = vals[n - 1] = 1;
        System.arraycopy(nums, 0, vals, 1, nums.length);
        int[][] dp = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int l = 0; l + len < n; l++) {
                int r = l + len;
                for (int k = l + 1; k < r; k++) {
                    dp[l][r] = Math.max(dp[l][r], dp[l][k] + vals[l] * vals[k] * vals[r] + dp[k][r]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
