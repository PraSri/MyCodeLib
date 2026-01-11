package oneddynamicprogramming;

import java.util.Arrays;

/**
 * Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 * <p>
 * House Robber II
 * https://leetcode.com/problems/house-robber-ii/
 * <p>
 * Paint House
 * https://leetcode.com/problems/paint-house/
 * <p>
 * Paint Fence
 * https://leetcode.com/problems/paint-fence/
 * <p>
 * House Robber III
 * https://leetcode.com/problems/house-robber-iii/
 * <p>
 * Non-negative Integers without Consecutive Ones
 * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/
 * <p>
 * Coin Path
 * https://leetcode.com/problems/coin-path/
 * <p>
 * Delete and Earn
 * https://leetcode.com/problems/delete-and-earn/
 * <p>
 * Solving Questions With Brainpower
 * https://leetcode.com/problems/solving-questions-with-brainpower/
 * <p>
 * Count Number of Ways to Place Houses
 * https://leetcode.com/problems/count-number-of-ways-to-place-houses/
 * <p>
 * House Robber IV
 * https://leetcode.com/problems/house-robber-iv/
 * <p>
 * Mice and Cheese
 * https://leetcode.com/problems/mice-and-cheese/
 * <p>
 * Largest Element in an Array after Merge Operations
 * https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations/
 */

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


    /**
     * Maximum Product Subarray
     * https://leetcode.com/problems/maximum-product-subarray
     */
    public static class MaximumProductSubarray {
    }

    /**
     * House Robber II
     * https://leetcode.com/problems/house-robber-ii
     */
    public static class HouseRobberIi {
    }

    /**
     * Paint House
     * https://leetcode.com/problems/paint-house
     */
    public static class PaintHouse {
    }

    /**
     * Paint Fence
     * https://leetcode.com/problems/paint-fence
     */
    public static class PaintFence {
    }

    /**
     * House Robber III
     * https://leetcode.com/problems/house-robber-iii
     */
    public static class HouseRobberIii {
    }

    /**
     * Non-negative Integers without Consecutive Ones
     * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones
     */
    public static class NonNegativeIntegersWithoutConsecutiveOnes {
    }

    /**
     * Coin Path
     * https://leetcode.com/problems/coin-path
     */
    public static class CoinPath {
    }

    /**
     * Delete and Earn
     * https://leetcode.com/problems/delete-and-earn
     */
    public static class DeleteAndEarn {
    }

    /**
     * Solving Questions With Brainpower
     * https://leetcode.com/problems/solving-questions-with-brainpower
     */
    public static class SolvingQuestionsWithBrainpower {
    }

    /**
     * Count Number of Ways to Place Houses
     * https://leetcode.com/problems/count-number-of-ways-to-place-houses
     */
    public static class CountNumberOfWaysToPlaceHouses {
    }

    /**
     * House Robber IV
     * https://leetcode.com/problems/house-robber-iv
     */
    public static class HouseRobberIv {
    }

    /**
     * Mice and Cheese
     * https://leetcode.com/problems/mice-and-cheese
     */
    public static class MiceAndCheese {
    }

    /**
     * Largest Element in an Array after Merge Operations
     * https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations
     */
    public static class LargestElementInAnArrayAfterMergeOperations {
    }
}
