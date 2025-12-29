package slidingwindow;

import java.util.*;

//Input: nums = [1,2,1,0,4,2,6], k = 3
//
//Output: [2,2,4,4,6]

public class SlidingWindowMaximum {

    /**
     * 
     * Minimum Window Substring
https://leetcode.com/problems/minimum-window-substring/

Min Stack
https://leetcode.com/problems/min-stack/

Longest Substring with At Most Two Distinct Characters
https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

Paint House II
https://leetcode.com/problems/paint-house-ii/

Jump Game VI
https://leetcode.com/problems/jump-game-vi/

Maximum Number of Robots Within Budget
https://leetcode.com/problems/maximum-number-of-robots-within-budget/

Maximum Tastiness of Candy Basket
https://leetcode.com/problems/maximum-tastiness-of-candy-basket/

Maximal Score After Applying K Operations
https://leetcode.com/problems/maximal-score-after-applying-k-operations/
     * 
     * 
    */

    // Humein har sliding window ka maximum chahiye.
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
//        Hum ek deque (double-ended queue) use karte hain jisme sirf potential maximum candidates rakhte hain.
//        Key idea:
//
//Deque mein sirf indices rakhe jaate hain, values nahi.
//
//Deque hamesha values ke decreasing order mein hota hai.
//
//Deque ka front current window ka maximum hota hai.

        //Agar new element aaya aur woh kisi purane element se bada hai,
        //toh woh purana element kabhi window ka maximum nahi ban sakta future mein.
        // toh usko deque se nikaal do.
        Deque<Integer> q = new LinkedList<>();
        int l = 0, r = 0;

        while (r < n) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            q.addLast(r);

            if (l > q.getFirst()) {
                q.removeFirst();
            }

            if ((r + 1) >= k) {
                output[l] = nums[q.getFirst()];
                l++;
            }
            r++;
        }

        return output;
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-window-substring/">LeetCode - Minimum Window Substring</a>
     */
    public static class MinimumWindowSubstring {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/min-stack/">LeetCode - Min Stack</a>
     */
    public static class MinStack {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/">LeetCode - Longest Substring with At Most Two Distinct Characters</a>
     */
    public static class LongestSubstringWithAtMostTwoDistinctCharacters {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/paint-house-ii/">LeetCode - Paint House II</a>
     */
    public static class PaintHouseII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/jump-game-vi/">LeetCode - Jump Game VI</a>
     */
    public static class JumpGameVI {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-number-of-robots-within-budget/">LeetCode - Maximum Number of Robots Within Budget</a>
     */
    public static class MaximumNumberOfRobotsWithinBudget {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-tastiness-of-candy-basket/">LeetCode - Maximum Tastiness of Candy Basket</a>
     */
    public static class MaximumTastinessOfCandyBasket {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximal-score-after-applying-k-operations/">LeetCode - Maximal Score After Applying K Operations</a>
     */
    public static class MaximalScoreAfterApplyingKOperations {
        // placeholder
    }
}
