package greedy;

/**
 *
 * Best Time to Buy and Sell Stock (Easy)
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">...</a>
 * <p>
 * Maximum Product Subarray (Medium)
 * <a href="https://leetcode.com/problems/maximum-product-subarray/">...</a>
 * <p>
 * Degree of an Array (Easy)
 * <a href="https://leetcode.com/problems/degree-of-an-array/">...</a>
 * <p>
 * Longest Turbulent Subarray (Medium)
 * <a href="https://leetcode.com/problems/longest-turbulent-subarray/">...</a>
 * <p>
 * Maximum Score Of Spliced Array (Hard)
 * <a href="https://leetcode.com/problems/maximum-score-of-spliced-array/">...</a>
 * <p>
 * Maximum Absolute Sum of Any Subarray (Medium)
 * <a href="https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/">...</a>
 * <p>
 * Maximum Subarray Sum After One Operation (Medium)
 * <a href="https://leetcode.com/problems/maximum-subarray-sum-after-one-operation/">...</a>
 * <p>
 * Substring With Largest Variance (Hard)
 * <a href="https://leetcode.com/problems/substring-with-largest-variance/">...</a>
 * <p>
 * Count Subarrays With Score Less Than K (Hard)
 * <a href="https://leetcode.com/problems/count-subarrays-with-score-less-than-k/">...</a>
 * <p>
 * Maximum Value of a String in an Array (Easy)
 * <a href="https://leetcode.com/problems/maximum-value-of-a-string-in-an-array/">...</a>
 * <p>
 * Find the Substring With Maximum Cost (Medium)
 * <a href="https://leetcode.com/problems/find-the-substring-with-maximum-cost/">...</a>
 * <p>
 * K Items With the Maximum Sum (Easy)
 * <a href="https://leetcode.com/problems/k-items-with-the-maximum-sum/">...</a>
 * <p>
 * Maximum Good Subarray Sum (Medium)
 * <a href="https://leetcode.com/problems/maximum-good-subarray-sum/">...</a>
 * <p>
 * Maximize Subarray Sum After Removing All Occurrences of One Element (Hard)
 * <a href="https://leetcode.com/problems/maximize-subarray-sum-after-removing-all-occurrences-of-one-element/">...</a>
 *
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int currMax = nums[0];
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currMax = Math.max(nums[i], currMax + nums[i]);
            globalMax = Math.max(globalMax, currMax);
        }
        return globalMax;
    }

    /**
     * Best Time to Buy and Sell Stock
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">...</a>
     */
    public static class BestTimeToBuyAndSellStock {
    }

    /**
     * Maximum Product Subarray
     * https://leetcode.com/problems/maximum-product-subarray/">...</a>
     */
    public static class MaximumProductSubarray {
    }

    /**
     * Degree of an Array
     * https://leetcode.com/problems/degree-of-an-array/">...</a>
     */
    public static class DegreeOfAnArray {
    }

    /**
     * Longest Turbulent Subarray
     * https://leetcode.com/problems/longest-turbulent-subarray/">...</a>
     */
    public static class LongestTurbulentSubarray {
    }

    /**
     * Maximum Score Of Spliced Array
     * https://leetcode.com/problems/maximum-score-of-spliced-array/">...</a>
     */
    public static class MaximumScoreOfSplicedArray {
    }

    /**
     * Maximum Absolute Sum of Any Subarray
     * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/">...</a>
     */
    public static class MaximumAbsoluteSumOfAnySubarray {
    }

    /**
     * Maximum Subarray Sum After One Operation
     * https://leetcode.com/problems/maximum-subarray-sum-after-one-operation/">...</a>
     */
    public static class MaximumSubarraySumAfterOneOperation {
    }

    /**
     * Substring With Largest Variance
     * https://leetcode.com/problems/substring-with-largest-variance/">...</a>
     */
    public static class SubstringWithLargestVariance {
    }

    /**
     * Count Subarrays With Score Less Than K
     * https://leetcode.com/problems/count-subarrays-with-score-less-than-k/">...</a>
     */
    public static class CountSubarraysWithScoreLessThanK {
    }

    /**
     * Maximum Value of a String in an Array
     * https://leetcode.com/problems/maximum-value-of-a-string-in-an-array/">...</a>
     */
    public static class MaximumValueOfAStringInAnArray {
    }

    /**
     * Find the Substring With Maximum Cost
     * https://leetcode.com/problems/find-the-substring-with-maximum-cost/">...</a>
     */
    public static class FindTheSubstringWithMaximumCost {
    }

    /**
     * K Items With the Maximum Sum
     * https://leetcode.com/problems/k-items-with-the-maximum-sum/">...</a>
     */
    public static class KItemsWithTheMaximumSum {
    }

    /**
     * Maximum Good Subarray Sum
     * https://leetcode.com/problems/maximum-good-subarray-sum/">...</a>
     */
    public static class MaximumGoodSubarraySum {
    }

    /**
     * Maximize Subarray Sum After Removing All Occurrences of One Element
     * https://leetcode.com/problems/maximize-subarray-sum-after-removing-all-occurrences-of-one-element/">...</a>
     */
    public static class MaximizeSubarraySumAfterRemovingAllOccurrencesOfOneElement {
    }
}
