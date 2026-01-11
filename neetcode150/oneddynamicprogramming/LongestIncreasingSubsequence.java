package oneddynamicprogramming;

import java.util.Arrays;

/**
 * Increasing Triplet Subsequence
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * <p>
 * <p>
 * Russian Doll Envelopes
 * https://leetcode.com/problems/russian-doll-envelopes/
 * <p>
 * <p>
 * Maximum Length of Pair Chain
 * https://leetcode.com/problems/maximum-length-of-pair-chain/
 * <p>
 * <p>
 * Number of Longest Increasing Subsequence
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 * <p>
 * <p>
 * Minimum ASCII Delete Sum for Two Strings
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 * <p>
 * <p>
 * Minimum Number of Removals to Make Mountain Array
 * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 * <p>
 * <p>
 * Find the Longest Valid Obstacle Course at Each Position
 * https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/
 * <p>
 * <p>
 * Minimum Operations to Make the Array K-Increasing
 * https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing/
 * <p>
 * <p>
 * Longest Ideal Subsequence
 * https://leetcode.com/problems/longest-ideal-subsequence/
 * <p>
 * <p>
 * Maximum Number of Books You Can Take
 * https://leetcode.com/problems/maximum-number-of-books-you-can-take/
 * <p>
 * <p>
 * Longest Increasing Subsequence II
 * https://leetcode.com/problems/longest-increasing-subsequence-ii/
 * <p>
 * <p>
 * Find the Maximum Length of a Good Subsequence II
 * https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-ii/
 * <p>
 * <p>
 * Find the Maximum Length of a Good Subsequence I
 * https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-i/
 * <p>
 * <p>
 * Find the Maximum Length of Valid Subsequence I
 * https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/
 * <p>
 * <p>
 * Find the Maximum Length of Valid Subsequence II
 * https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/
 * <p>
 * <p>
 * Longest Subsequence With Decreasing Adjacent Difference
 * https://leetcode.com/problems/longest-subsequence-with-decreasing-adjacent-difference/
 * <p>
 * <p>
 */

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return len(nums, 0, -1, dp);
    }

    private int len(int[] nums, int curr, int prev, int[][] dp) {
        if (curr == nums.length) {
            return 0;
        }
        if (dp[curr][prev + 1] != -1) {
            return dp[curr][prev + 1];
        }

        int exclude = len(nums, curr + 1, prev, dp);
        int include = Integer.MIN_VALUE;
        if (prev == -1 || nums[curr] > nums[prev]) {
            include = 1 + len(nums, curr + 1, curr, dp);
        }

        return dp[curr][prev + 1] = Math.max(exclude, include);

    }

    /**
     * Increasing Triplet Subsequence
     * https://leetcode.com/problems/increasing-triplet-subsequence
     */
    public static class IncreasingTripletSubsequence {
    }

    /**
     * Russian Doll Envelopes
     * https://leetcode.com/problems/russian-doll-envelopes
     */
    public static class RussianDollEnvelopes {
    }

    /**
     * Maximum Length of Pair Chain
     * https://leetcode.com/problems/maximum-length-of-pair-chain
     */
    public static class MaximumLengthOfPairChain {
    }

    /**
     * Number of Longest Increasing Subsequence
     * https://leetcode.com/problems/number-of-longest-increasing-subsequence
     */
    public static class NumberOfLongestIncreasingSubsequence {
    }

    /**
     * Minimum ASCII Delete Sum for Two Strings
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings
     */
    public static class MinimumAsciiDeleteSumForTwoStrings {
    }

    /**
     * Minimum Number of Removals to Make Mountain Array
     * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array
     */
    public static class MinimumNumberOfRemovalsToMakeMountainArray {
    }

    /**
     * Find the Longest Valid Obstacle Course at Each Position
     * https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position
     */
    public static class FindTheLongestValidObstacleCourseAtEachPosition {
    }

    /**
     * Minimum Operations to Make the Array K-Increasing
     * https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing
     */
    public static class MinimumOperationsToMakeTheArrayKIncreasing {
    }

    /**
     * Longest Ideal Subsequence
     * https://leetcode.com/problems/longest-ideal-subsequence
     */
    public static class LongestIdealSubsequence {
    }

    /**
     * Maximum Number of Books You Can Take
     * https://leetcode.com/problems/maximum-number-of-books-you-can-take
     */
    public static class MaximumNumberOfBooksYouCanTake {
    }

    /**
     * Longest Increasing Subsequence II
     * https://leetcode.com/problems/longest-increasing-subsequence-ii
     */
    public static class LongestIncreasingSubsequenceIi {
    }

    /**
     * Find the Maximum Length of a Good Subsequence II
     * https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-ii
     */
    public static class FindTheMaximumLengthOfAGoodSubsequenceIi {
    }

    /**
     * Find the Maximum Length of a Good Subsequence I
     * https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-i
     */
    public static class FindTheMaximumLengthOfAGoodSubsequenceI {
    }

    /**
     * Find the Maximum Length of Valid Subsequence I
     * https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i
     */
    public static class FindTheMaximumLengthOfValidSubsequenceI {
    }

    /**
     * Find the Maximum Length of Valid Subsequence II
     * https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii
     */
    public static class FindTheMaximumLengthOfValidSubsequenceIi {
    }

    /**
     * Longest Subsequence With Decreasing Adjacent Difference
     * https://leetcode.com/problems/longest-subsequence-with-decreasing-adjacent-difference
     */
    public static class LongestSubsequenceWithDecreasingAdjacentDifference {
    }
}
