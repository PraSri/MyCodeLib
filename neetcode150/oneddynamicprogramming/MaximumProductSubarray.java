package oneddynamicprogramming;

/**
 * Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 * <p>
 * House Robber
 * https://leetcode.com/problems/house-robber/
 * <p>
 * Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 * <p>
 * Maximum Product of Three Numbers
 * https://leetcode.com/problems/maximum-product-of-three-numbers/
 * <p>
 * Subarray Product Less Than K
 * https://leetcode.com/problems/subarray-product-less-than-k/
 */

public class MaximumProductSubarray {

    //Input: nums = [1,2,-3,4]
    //
    //Output: 4

    public int maxProduct(int[] nums) {

        int curMax = nums[0]; // 1

        int preMax = nums[0]; // 1

        int preMin = nums[0]; // 1

        int ans = nums[0]; // 1

        for (int i = 1; i < nums.length; i++) {

            // 3 things we are tracking
            // 1. ith * preMax
            // 2. ith * preMin
            // 3. ith
            // max(1,2,3)
            curMax = Math.max(nums[i] * preMax, nums[i] * preMin);

            curMax = Math.max(curMax, nums[i]);

            int curMin = Math.min(nums[i] * preMax, nums[i] * preMin);
            curMin = Math.min(curMin, nums[i]);

            ans = Math.max(ans, curMax);

            preMax = curMax;

            preMin = curMin;

        }
        return ans;
    }


    /**
     * Maximum Subarray
     * https://leetcode.com/problems/maximum-subarray
     */
    public static class MaximumSubarray {
    }

    /**
     * House Robber
     * https://leetcode.com/problems/house-robber
     */
    public static class HouseRobber {
    }

    /**
     * Product of Array Except Self
     * https://leetcode.com/problems/product-of-array-except-self
     */
    public static class ProductOfArrayExceptSelf {
    }

    /**
     * Maximum Product of Three Numbers
     * https://leetcode.com/problems/maximum-product-of-three-numbers
     */
    public static class MaximumProductOfThreeNumbers {
    }

    /**
     * Subarray Product Less Than K
     * https://leetcode.com/problems/subarray-product-less-than-k
     */
    public static class SubarrayProductLessThanK {
    }
}
