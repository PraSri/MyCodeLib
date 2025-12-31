package linkedlist;


/**
 * First Missing Positive (Hard)
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * Single Number (Easy)
 * https://leetcode.com/problems/single-number/
 * <p>
 * Linked List Cycle II (Medium)
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * <p>
 * Missing Number (Easy)
 * https://leetcode.com/problems/missing-number/
 * <p>
 * Set Mismatch (Easy)
 * https://leetcode.com/problems/set-mismatch/
 */

public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        int slow2 = 0;
        while (true) {
            slow2 = nums[slow2];
            slow = nums[slow];
            if (slow2 == slow) {
                return slow;
            }
        }
    }
    /**
     * <a href="https://leetcode.com/problems/first-missing-positive/">LeetCode - First Missing Positive</a>
     */
    public static class FirstMissingPositive {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/single-number/">LeetCode - Single Number</a>
     */
    public static class SingleNumber {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/linked-list-cycle-ii/">LeetCode - Linked List Cycle II</a>
     */
    public static class LinkedListCycleIi {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/missing-number/">LeetCode - Missing Number</a>
     */
    public static class MissingNumber {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/set-mismatch/">LeetCode - Set Mismatch</a>
     */
    public static class SetMismatch {
        // placeholder
    }

}
