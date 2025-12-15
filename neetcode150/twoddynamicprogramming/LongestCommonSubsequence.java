package twoddynamicprogramming;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();

        int m = text2.length();

        int[][] dp = new int[n][m];

        for(int[] it: dp) {
            Arrays.fill(it, -1);
        }

        return dfs(0, 0, text1, text2, dp);
    }

    private int dfs(int i, int j, String a, String b, int[][] dp) {

        if(i == a.length() || j == b.length()) {
            return 0;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        if(a.charAt(i) == b.charAt(j)) {
            int includeBoth =  dfs(i+1, j+1, a, b, dp);
            return dp[i][j] = 1 + includeBoth;
        } else {
            int includeAExcludeB = dfs(i+1, j, a, b, dp);
            int includeBExcludeA = dfs(i, j+1, a, b, dp);
            return dp[i][j] = Math.max(includeAExcludeB, includeBExcludeA);
        }

    }

}
