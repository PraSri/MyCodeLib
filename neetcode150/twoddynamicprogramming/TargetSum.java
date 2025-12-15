package twoddynamicprogramming;

import java.util.Arrays;

public class TargetSum {


    /**
     * Tumhe nums[] diya hai. Har number ke aage + ya - laga sakte ho.
     * Goal: kitne different ways me signs laga ke target bana sakte ho?
     *
     *
     * Ye problem directly +/- se solve karna costly hota hai.
     * Isliye hum isse Subset Sum problem me convert kar dete hain.
     * */

    public int findTargetSumWays(int[] nums, int target) {

        int total = Arrays.stream(nums).sum();

        int newTarget = (total - target)/2;
        if((total - target) < 0 || (total - target)%2==1) {
            return 0;
        }
        int[][] dp = new int[nums.length+1][newTarget + 1];

        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }

        return waysMemo(nums.length-1, nums, newTarget, dp);
    }

    private int ways(int i, int[] nums, int target) {
        if(i==0) {
            if(target == 0 && nums[0] == 0) {
                return 2;
            }
            if(target == 0 || target == nums[0]) {
                return 1;
            }
            return 0;
        }
        int notTaken = ways(i-1, nums, target);
        int taken = 0;
        if(nums[i]<=target) {
            taken = ways(i-1, nums, target - nums[i]);
        }
        return notTaken + taken;
    }

    private int waysMemo(int i, int[] nums, int target, int[][] dp) {
        if(i==0) {
            /**
             * Agar sirf ek element bacha hai (i == 0)
             *
             * Aur wo 0 hai & target bhi 0 ? 2 ways (+0, -0)
             * */
            if(target == 0 && nums[0] == 0) {
                return 2;
            }
            /***
             * target = 0 (element lo hi mat)
             *
             * target = nums[0] (element lo)
             * */
            if(target == 0 || target == nums[0]) {
                return 1;
            }
            return 0;
        }
        if(dp[i][target]!=-1) {
            return dp[i][target];
        }

        int notTaken = waysMemo(i-1, nums, target, dp);

        int taken = 0;

        if(nums[i]<=target) {
            taken = waysMemo(i-1, nums, target - nums[i], dp);
        }

        return dp[i][target] = notTaken + taken;
    }

}
