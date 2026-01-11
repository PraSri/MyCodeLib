package oneddynamicprogramming;

/**
 * Shortest Palindrome (Hard)
 * https://leetcode.com/problems/shortest-palindrome/
 * <p>
 * Palindrome Permutation (Easy)
 * https://leetcode.com/problems/palindrome-permutation/
 * <p>
 * Palindrome Pairs (Hard)
 * https://leetcode.com/problems/palindrome-pairs/
 * <p>
 * Longest Palindromic Subsequence (Medium)
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * <p>
 * Palindromic Substrings (Medium)
 * https://leetcode.com/problems/palindromic-substrings/
 * <p>
 * Maximum Number of Non-overlapping Palindrome Substrings (Hard)
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/
 */

public class LongestPalindromicSubstring {

    int start = 0, maxLen = 0;

    public String longestPalindrome(String s) {
        // "ababd"
        // "dbaba"
        int n = s.length();
        for (int i = 0; i < n; i++) {
            extend(s, i, i);
            extend(s, i, i + 1);
        }
        return s.substring(start, start + maxLen);
    }

    private void extend(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {
            maxLen = right - left - 1;
            start = left + 1;
        }
    }

    /**
     * Shortest Palindrome
     * https://leetcode.com/problems/shortest-palindrome
     */
    public static class ShortestPalindrome {
    }

    /**
     * Palindrome Permutation
     * https://leetcode.com/problems/palindrome-permutation
     */
    public static class PalindromePermutation {
    }

    /**
     * Palindrome Pairs
     * https://leetcode.com/problems/palindrome-pairs
     */
    public static class PalindromePairs {
    }

    /**
     * Longest Palindromic Subsequence
     * https://leetcode.com/problems/longest-palindromic-subsequence
     */
    public static class LongestPalindromicSubsequence {
    }

    /**
     * Palindromic Substrings
     * https://leetcode.com/problems/palindromic-substrings
     */
    public static class PalindromicSubstrings {
    }

    /**
     * Maximum Number of Non-overlapping Palindrome Substrings
     * https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings
     */
    public static class MaximumNumberOfNonOverlappingPalindromeSubstrings {
    }
}
