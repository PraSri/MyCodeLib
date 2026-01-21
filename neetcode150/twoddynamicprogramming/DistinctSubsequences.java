package twoddynamicprogramming;

import java.util.Arrays;


//Number of Unique Good Subsequences - https://leetcode.com/problems/number-of-unique-good-subsequences/

public class DistinctSubsequences {

    // Problem ka core idea: source string s se target t ko subsequence ke form me kitne ways me bana sakte ho.

    //Har index pe 2 choices hoti hain:
    //
    //Skip current char of s
    //
    //Use it (sirf tab jab s[i] == t[j])

    //DP state socho:
    //dp[i][j] = number of ways to form t[j:] using s[i:]

    //Base cases:
    //
    //Agar j == t.length() ? target ban chuka ? 1 valid way
    //
    //Agar i == s.length() but t baki hai ? 0 ways

    //Recurrence:
    //
    //Match hua ? dp[i+1][j] + dp[i+1][j+1]
    //
    //Match nahi hua ? dp[i+1][j]

    //Har char pe skip ya take ka decision, aur DP se count karo kitne tareeko se t ban sakta hai s se.

    int[][] dp;

    public int numDistinct(String s, String t) {
        if (t.length() > s.length()) {
            return 0;
        }
        dp = new int[s.length() + 1][t.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(s, t, 0, 0);
    }

    private int dfs(String s, String t, int i, int j) {

        // base case
        if (j >= t.length()) {
            return 1;
        }

        if (i >= s.length()) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int res = 0;

        if (s.charAt(i) == t.charAt(j)) {
            res = dfs(s, t, i + 1, j) + dfs(s, t, i + 1, j + 1); // take or leave
        } else {
            res = dfs(s, t, i + 1, j); // leave
        }

        return dp[i][j] = res;
    }

    /**
     * Number of Unique Good Subsequences
     * https://leetcode.com/problems/number-of-unique-good-subsequences/
     */
    public static class NumberOfUniqueGoodSubsequences {
    }
}
