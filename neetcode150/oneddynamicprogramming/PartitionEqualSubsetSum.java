package oneddynamicprogramming;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i<n; i++) {
            sum += nums[i];
        }
        if(sum%2!=0) {
            return false;
        }
        Boolean[][] dp = new Boolean[n+1][sum/2 + 1];
        for(int i = 0; i<n; i++) {
            Arrays.fill(dp[i], null);
        }
        return dfs(0, nums, dp, sum/2);
    }

    private boolean dfs(int i, int[] nums, Boolean[][] dp, int target) {
        if(i == nums.length) {
            return target == 0;
        }
        if(target<0) {
            return false;
        }
        if(dp[i][target]!=null) {
            return dp[i][target];
        }
        return dp[i][target] = dfs(i+1, nums, dp, target - nums[i]) || dfs(i+1, nums, dp, target);
    }

}
