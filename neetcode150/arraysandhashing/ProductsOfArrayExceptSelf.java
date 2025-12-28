package arraysandhashing;

class ProductsOfArrayExceptSelf {

    /**
     * Trapping Rain Water (Hard)
     * <a href="https://leetcode.com/problems/trapping-rain-water/">...</a>
     * <p>
     * Maximum Product Subarray (Medium)
     * <a href="https://leetcode.com/problems/maximum-product-subarray/">...</a>
     * <p>
     * Paint House II (Hard)
     * <a href="https://leetcode.com/problems/paint-house-ii/">...</a>
     * <p>
     * Minimum Difference in Sums After Removal of Elements (Hard)
     * <a href="https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/">...</a>
     * <p>
     * Construct Product Matrix (Medium)
     * <a href="https://leetcode.com/problems/construct-product-matrix/">...</a>
     * <p>
     * Find Sum of Array Product of Magical Sequences (Hard)
     * <a href="https://leetcode.com/problems/find-sum-of-array-product-of-magical-sequences/">...</a>
     */

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] pref = new int[n];
        int[] suff = new int[n];

        pref[0] = 1;
        suff[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            pref[i] = nums[i - 1] * pref[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = nums[i + 1] * suff[i + 1];
        }
        for (int i = 0; i < n; i++) {
            res[i] = pref[i] * suff[i];
        }
        return res;
    }

    /**
     * <a href="https://leetcode.com/problems/trapping-rain-water/">LeetCode - Trapping Rain Water</a>
     */
    public static class TrappingRainWater {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-product-subarray/">LeetCode - Maximum Product Subarray</a>
     */
    public static class MaximumProductSubarray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/paint-house-ii/">LeetCode - Paint House II</a>
     */
    public static class PaintHouseII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/">LeetCode - Minimum Difference in Sums After Removal of Elements</a>
     */
    public static class MinimumDifferenceInSumsAfterRemovalOfElements {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/construct-product-matrix/">LeetCode - Construct Product Matrix</a>
     */
    public static class ConstructProductMatrix {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-sum-of-array-product-of-magical-sequences/">LeetCode - Find Sum of Array Product of Magical Sequences</a>
     */
    public static class FindSumOfArrayProductOfMagicalSequences {
        // placeholder
    }

}
