package oneddynamicprogramming;

import java.util.Arrays;

/**
 * Partition to K Equal Sum Subsets
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * <p>
 * Minimize the Difference Between Target and Chosen Elements
 * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/
 * <p>
 * Maximum Number of Ways to Partition an Array
 * https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array/
 * <p>
 * Partition Array Into Two Arrays to Minimize Sum Difference
 * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
 * <p>
 * Find Subarrays With Equal Sum
 * https://leetcode.com/problems/find-subarrays-with-equal-sum/
 * <p>
 * Number of Great Partitions
 * https://leetcode.com/problems/number-of-great-partitions/
 * <p>
 * Split With Minimum Sum
 * https://leetcode.com/problems/split-with-minimum-sum/
 **/

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        Boolean[][] dp = new Boolean[n + 1][sum / 2 + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], null);
        }
        return dfs(0, nums, dp, sum / 2);
    }

    private boolean dfs(int i, int[] nums, Boolean[][] dp, int target) {
        if (i == nums.length) {
            return target == 0;
        }
        if (target < 0) {
            return false;
        }
        if (dp[i][target] != null) {
            return dp[i][target];
        }
        return dp[i][target] = dfs(i + 1, nums, dp, target - nums[i]) || dfs(i + 1, nums, dp, target);
    }


    /**
     * Partition to K Equal Sum Subsets
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets
     */
    public static class PartitionToKEqualSumSubsets {
    }

    /**
     * Minimize the Difference Between Target and Chosen Elements
     * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements
     */
    public static class MinimizeTheDifferenceBetweenTargetAndChosenElements {
    }

    /**
     * Maximum Number of Ways to Partition an Array
     * https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array
     */
    public static class MaximumNumberOfWaysToPartitionAnArray {
    }

    /**
     * Partition Array Into Two Arrays to Minimize Sum Difference
     * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference
     */
    public static class PartitionArrayIntoTwoArraysToMinimizeSumDifference {
    }

    /**
     * Find Subarrays With Equal Sum
     * https://leetcode.com/problems/find-subarrays-with-equal-sum
     */
    public static class FindSubarraysWithEqualSum {
    }

    /**
     * Number of Great Partitions
     * https://leetcode.com/problems/number-of-great-partitions
     */
    public static class NumberOfGreatPartitions {
    }

    /**
     * Split With Minimum Sum
     * https://leetcode.com/problems/split-with-minimum-sum
     */
    public static class SplitWithMinimumSum {
    }
}
