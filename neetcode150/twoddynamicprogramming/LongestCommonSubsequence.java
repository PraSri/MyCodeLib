package twoddynamicprogramming;

import java.util.Arrays;

/***Longest Palindromic Subsequence (Medium)
 https://leetcode.com/problems/longest-palindromic-subsequence/

 Delete Operation for Two Strings (Medium)
 https://leetcode.com/problems/delete-operation-for-two-strings/

 Shortest Common Supersequence (Hard)
 https://leetcode.com/problems/shortest-common-supersequence/

 Maximize Number of Subsequences in a String (Medium)
 https://leetcode.com/problems/maximize-number-of-subsequences-in-a-string/

 Subsequence With the Minimum Score (Hard)
 https://leetcode.com/problems/subsequence-with-the-minimum-score/*/

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();

        int m = text2.length();

        int[][] dp = new int[n][m];

        for (int[] it : dp) {
            Arrays.fill(it, -1);
        }

        return dfs(0, 0, text1, text2, dp);
    }

    private int dfs(int i, int j, String a, String b, int[][] dp) {

        if (i == a.length() || j == b.length()) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (a.charAt(i) == b.charAt(j)) {
            int includeBoth = dfs(i + 1, j + 1, a, b, dp);
            return dp[i][j] = 1 + includeBoth;
        } else {
            int includeAExcludeB = dfs(i + 1, j, a, b, dp);
            int includeBExcludeA = dfs(i, j + 1, a, b, dp);
            return dp[i][j] = Math.max(includeAExcludeB, includeBExcludeA);
        }

    }


    /**
     * Longest Palindromic Subsequence
     * https://leetcode.com/problems/longest-palindromic-subsequence
     */
    public static class LongestPalindromicSubsequence {
    }

    /**
     * Delete Operation for Two Strings
     * https://leetcode.com/problems/delete-operation-for-two-strings
     */
    public static class DeleteOperationForTwoStrings {
    }

    /**
     * Shortest Common Supersequence
     * https://leetcode.com/problems/shortest-common-supersequence
     */
    public static class ShortestCommonSupersequence {
    }

    /**
     * Maximize Number of Subsequences in a String
     * https://leetcode.com/problems/maximize-number-of-subsequences-in-a-string
     */
    public static class MaximizeNumberOfSubsequencesInAString {
    }

    /**
     * Subsequence With the Minimum Score
     * https://leetcode.com/problems/subsequence-with-the-minimum-score
     */
    public static class SubsequenceWithTheMinimumScore {
    }
}
