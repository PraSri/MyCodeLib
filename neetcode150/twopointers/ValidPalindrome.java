package twopointers;

public class ValidPalindrome {

    /**
     *
     * Palindrome Linked List
     * <a href="https://leetcode.com/problems/palindrome-linked-list/">...</a>
     * <p>
     * Valid Palindrome II
     * <a href="https://leetcode.com/problems/valid-palindrome-ii/">...</a>
     * <p>
     * Maximum Product of the Length of Two Palindromic Subsequences
     * <a href="https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/">...</a>
     * <p>
     * Find First Palindromic String in the Array
     * <a href="https://leetcode.com/problems/find-first-palindromic-string-in-the-array/">...</a>
     * <p>
     * Valid Palindrome IV
     * <a href="https://leetcode.com/problems/valid-palindrome-iv/">...</a>
     * <p>
     * Maximum Palindromes After Operations
     * <a href="https://leetcode.com/problems/maximum-palindromes-after-operations/">...</a>
     *
     */

    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            while (l < r && !alphaNum(s.charAt(l))) {
                l++;
            }
            while (r > l && !alphaNum(s.charAt(r))) {
                r--;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean alphaNum(char c) {
        return (c >= 'A' && c <= 'Z' ||
                c >= 'a' && c <= 'z' ||
                c >= '0' && c <= '9');
    }

    /**
     * <a href="https://leetcode.com/problems/palindrome-linked-list/">LeetCode - Palindrome Linked List</a>
     */
    public static class PalindromeLinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/valid-palindrome-ii/">LeetCode - Valid Palindrome II</a>
     */
    public static class ValidPalindromeII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/">LeetCode - Maximum Product of the Length of Two Palindromic Subsequences</a>
     */
    public static class MaximumProductOfTheLengthOfTwoPalindromicSubsequences {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/find-first-palindromic-string-in-the-array/">LeetCode - Find First Palindromic String in the Array</a>
     */
    public static class FindFirstPalindromicStringInTheArray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/valid-palindrome-iv/">LeetCode - Valid Palindrome IV</a>
     */
    public static class ValidPalindromeIV {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-palindromes-after-operations/">LeetCode - Maximum Palindromes After Operations</a>
     */
    public static class MaximumPalindromesAfterOperations {
        // placeholder
    }

}
