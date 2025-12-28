package twopointers;

public class TwoIntegerSumII {

    /**
     * Two Sum
     * <a href="https://leetcode.com/problems/two-sum/">...</a>
     * <p>
     * Two Sum IV – Input is a BST
     * <a href="https://leetcode.com/problems/two-sum-iv-input-is-a-bst/">...</a>
     * <p>
     * Two Sum Less Than K
     * <a href="https://leetcode.com/problems/two-sum-less-than-k/">...</a>
     */

    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            if (target - numbers[l] == numbers[r]) {
                return new int[]{l + 1, r + 1};
            } else if (target - numbers[l] > numbers[r]) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * <a href="https://leetcode.com/problems/two-sum/">LeetCode - Two Sum</a>
     */
    public static class TwoSum {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/two-sum-iv-input-is-a-bst/">LeetCode - Two Sum IV – Input is a BST</a>
     */
    public static class TwoSumIVInputIsABST {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/two-sum-less-than-k/">LeetCode - Two Sum Less Than K</a>
     */
    public static class TwoSumLessThanK {
        // placeholder
    }
}
