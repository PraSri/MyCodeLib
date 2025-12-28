package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Two Sum
 * <a href="https://leetcode.com/problems/two-sum/">...</a>
 * <p>
 * 3Sum Closest
 * <a href="https://leetcode.com/problems/3sum-closest/">...</a>
 * <p>
 * 4Sum
 * <a href="https://leetcode.com/problems/4sum/">...</a>
 * <p>
 * 3Sum Smaller
 * <a href="https://leetcode.com/problems/3sum-smaller/">...</a>
 * <p>
 * Number of Arithmetic Triplets
 * <a href="https://leetcode.com/problems/number-of-arithmetic-triplets/">...</a>
 * <p>
 * Minimum Sum of Mountain Triplets I
 * <a href="https://leetcode.com/problems/minimum-sum-of-mountain-triplets-i/">...</a>
 * <p>
 * Minimum Sum of Mountain Triplets II
 * <a href="https://leetcode.com/problems/minimum-sum-of-mountain-triplets-ii/">...</a>
 *
 *
 */

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            // guaranteed that sum will not equal to zero
            if (nums[i] > 0) {
                break;
            }

            // avoid duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1;

            int r = nums.length - 1;

            while (l < r) {

                int sum = nums[i] + nums[l] + nums[r];

                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                }
            }
        }

        return res;
    }

    /**
     * <a href="https://leetcode.com/problems/two-sum/">LeetCode - Two Sum</a>
     */
    public static class TwoSum {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/3sum-closest/">LeetCode - 3Sum Closest</a>
     */
    public static class ThreeSumClosest {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/4sum/">LeetCode - 4Sum</a>
     */
    public static class FourSum {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/3sum-smaller/">LeetCode - 3Sum Smaller</a>
     */
    public static class ThreeSumSmaller {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/number-of-arithmetic-triplets/">LeetCode - Number of Arithmetic Triplets</a>
     */
    public static class NumberOfArithmeticTriplets {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-sum-of-mountain-triplets-i/">LeetCode - Minimum Sum of Mountain Triplets I</a>
     */
    public static class MinimumSumOfMountainTripletsI {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-sum-of-mountain-triplets-ii/">LeetCode - Minimum Sum of Mountain Triplets II</a>
     */
    public static class MinimumSumOfMountainTripletsII {
        // placeholder
    }

}
