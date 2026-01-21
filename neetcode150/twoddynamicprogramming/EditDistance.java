package twoddynamicprogramming;

import java.util.Arrays;

/***##

 1. **One Edit Distance** (Medium)
 [<a href="https://leetcode.com/problems/one-edit-distance/">...</a>](https://leetcode.com/problems/one-edit-distance/)

 2. **Delete Operation for Two Strings** (Medium)
 [https://leetcode.com/problems/delete-operation-for-two-strings/](https://leetcode.com/problems/delete-operation-for-two-strings/)

 3. **Minimum ASCII Delete Sum for Two Strings** (Medium)
 [https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)

 4. **Uncrossed Lines** (Medium)
 [https://leetcode.com/problems/uncrossed-lines/](https://leetcode.com/problems/uncrossed-lines/)

 5. **Minimum White Tiles After Covering With Carpets** (Hard)
 [https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/](https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/)

 6. **Longest Palindrome After Substring Concatenation II** (Hard)
 [https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-ii/](https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-ii/)

 7. **Minimum Steps to Convert String with Operations** (Hard)
 [https://leetcode.com/problems/minimum-steps-to-convert-string-with-operations/](https://leetcode.com/problems/minimum-steps-to-convert-string-with-operations/)
 */

public class EditDistance {

    int n, m;
    int[][] dp;

    public int minDistance(String word1, String word2) {
        n = word1.length();
        m = word2.length();
        dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(word1, word2, 0, 0);
    }

    private int dfs(String s1, String s2, int i, int j) {
        if (i == n) {
            return m - j; // word1 khatam, word2 ke bache chars insert karo
        }
        if (j == m) {
            return n - i; // word2 khatam, word1 ke bache chars delete karo
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = dfs(s1, s2, i + 1, j + 1);
        } else {
            int insert = dfs(s1, s2, i + 1, j);
            int delete = dfs(s1, s2, i, j + 1);
            int replace = dfs(s1, s2, i + 1, j + 1);
            dp[i][j] = 1 + Math.min(Math.min(insert, delete), replace);
        }
        return dp[i][j];
    }


    /**
     * 1. **One Edit Distance**
     * <a href="https://leetcode.com/problems/one-edit-distance/">...</a>">...</a>](https://leetcode.com/problems/one-edit-distance/)
     */
    public static class OneEditDistance {
    }

    /**
     * 2. **Delete Operation for Two Strings**
     * <a href="https://leetcode.com/problems/delete-operation-for-two-strings/">...</a>](https://leetcode.com/problems/delete-operation-for-two-strings/)
     */
    public static class DeleteOperationForTwoStrings {
    }

    /**
     * 3. **Minimum ASCII Delete Sum for Two Strings**
     * <a href="https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/">...</a>](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)
     */
    public static class MinimumAsciiDeleteSumForTwoStrings {
    }

    /**
     * 4. **Uncrossed Lines**
     * <a href="https://leetcode.com/problems/uncrossed-lines/">...</a>](https://leetcode.com/problems/uncrossed-lines/)
     */
    public static class UncrossedLines {
    }

    /**
     * 5. **Minimum White Tiles After Covering With Carpets**
     * <a href="https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/">...</a>](https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/)
     */
    public static class MinimumWhiteTilesAfterCoveringWithCarpets {
    }

    /**
     * 6. **Longest Palindrome After Substring Concatenation II**
     * <a href="https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-ii/">...</a>](https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-ii/)
     */
    public static class LongestPalindromeAfterSubstringConcatenationIi {
    }

    /**
     * 7. **Minimum Steps to Convert String with Operations**
     * <a href="https://leetcode.com/problems/minimum-steps-to-convert-string-with-operations/">...</a>](https://leetcode.com/problems/minimum-steps-to-convert-string-with-operations/)
     */
    public static class MinimumStepsToConvertStringWithOperations {
    }

    /**
     * leetcode.com/problems/one-edit-distance/">...</a>](
     * https://leetcode.com/problems/one-edit-distance/)
     */
    public static class LeetcodeComProblemsOneEditDistanceA {
    }

    /**
     * leetcode.com/problems/delete-operation-for-two-strings/](
     * https://leetcode.com/problems/delete-operation-for-two-strings/)
     */
    public static class LeetcodeComProblemsDeleteOperationForTwoStrings {
    }

    /**
     * leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/](
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)
     */
    public static class LeetcodeComProblemsMinimumAsciiDeleteSumForTwoStrings {
    }

    /**
     * leetcode.com/problems/uncrossed-lines/](
     * https://leetcode.com/problems/uncrossed-lines/)
     */
    public static class LeetcodeComProblemsUncrossedLines {
    }

    /**
     * leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/](
     * https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/)
     */
    public static class LeetcodeComProblemsMinimumWhiteTilesAfterCoveringWithCarpets {
    }

    /**
     * leetcode.com/problems/longest-palindrome-after-substring-concatenation-ii/](
     * https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-ii/)
     */
    public static class LeetcodeComProblemsLongestPalindromeAfterSubstringConcatenationIi {
    }

    /**
     * leetcode.com/problems/minimum-steps-to-convert-string-with-operations/](
     * https://leetcode.com/problems/minimum-steps-to-convert-string-with-operations/)
     */
    public static class LeetcodeComProblemsMinimumStepsToConvertStringWithOperations {
    }
}
