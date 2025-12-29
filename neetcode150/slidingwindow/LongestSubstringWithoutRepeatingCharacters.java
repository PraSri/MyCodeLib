package slidingwindow;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Longest Substring with At Most Two Distinct Characters
     * <a href="https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/">...</a>
     * <p>
     * Longest Substring with At Most K Distinct Characters
     * <a href="https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/">...</a>
     * <p>
     * Subarrays with K Different Integers
     * <a href="https://leetcode.com/problems/subarrays-with-k-different-integers/">...</a>
     * <p>
     * Maximum Erasure Value
     * <a href="https://leetcode.com/problems/maximum-erasure-value/">...</a>
     * <p>
     * Number of Equal Count Substrings
     * <a href="https://leetcode.com/problems/number-of-equal-count-substrings/">...</a>
     * <p>
     * Minimum Consecutive Cards to Pick Up
     * <a href="https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/">...</a>
     * <p>
     * Longest Nice Subarray
     * <a href="https://leetcode.com/problems/longest-nice-subarray/">...</a>
     * <p>
     * Optimal Partition of String
     * <a href="https://leetcode.com/problems/optimal-partition-of-string/">...</a>
     * <p>
     * Count Complete Subarrays in an Array
     * <a href="https://leetcode.com/problems/count-complete-subarrays-in-an-array/">...</a>
     * <p>
     * Find Longest Special Substring That Occurs Thrice II
     * <a href="https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/">...</a>
     * <p>
     * Find Longest Special Substring That Occurs Thrice I
     * <a href="https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/">...</a>
     */

    //Input: s = "zxyzxyz"
    //
    //Output: 3
    public int lengthOfLongestSubstring(String s) {
        // last seen 
        HashMap<Character, Integer> mp = new HashMap<>();
        int l = 0, res = 0;

        for (int r = 0; r < s.length(); r++) {
            // agar current char phele aa chuka hai toh dekho
            if (mp.containsKey(s.charAt(r))) {
                // iski start kaha se hui thi
                // jo bhi max index hai usse l se update kro
                l = Math.max(mp.get(s.charAt(r)) + 1, l);
            }
            // har baar naya index update kr dete hai
            // taki last seen pata rhe
            mp.put(s.charAt(r), r);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }


    /**
     * <a href="https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/">LeetCode - Longest Substring with At Most Two Distinct Characters</a>
     */
    public static class LongestSubstringWithAtMostTwoDistinctCharacters {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/">LeetCode - Longest Substring with At Most K Distinct Characters</a>
     */
    public static class LongestSubstringWithAtMostKDistinctCharacters {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/subarrays-with-k-different-integers/">LeetCode - Subarrays with K Different Integers</a>
     */
    public static class SubarraysWithKDifferentIntegers {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-erasure-value/">LeetCode - Maximum Erasure Value</a>
     */
    public static class MaximumErasureValue {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/number-of-equal-count-substrings/">LeetCode - Number of Equal Count Substrings</a>
     */
    public static class NumberOfEqualCountSubstrings {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/">LeetCode - Minimum Consecutive Cards to Pick Up</a>
     */
    public static class MinimumConsecutiveCardsToPickUp {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/longest-nice-subarray/">LeetCode - Longest Nice Subarray</a>
     */
    public static class LongestNiceSubarray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/optimal-partition-of-string/">LeetCode - Optimal Partition of String</a>
     */
    public static class OptimalPartitionOfString {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/count-complete-subarrays-in-an-array/">LeetCode - Count Complete Subarrays in an Array</a>
     */
    public static class CountCompleteSubarraysInAnArray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/">LeetCode - Find Longest Special Substring That Occurs Thrice II</a>
     */
    public static class FindLongestSpecialSubstringThatOccursThriceII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/">LeetCode - Find Longest Special Substring That Occurs Thrice I</a>
     */
    public static class FindLongestSpecialSubstringThatOccursThriceI {
        // placeholder
    }
}
