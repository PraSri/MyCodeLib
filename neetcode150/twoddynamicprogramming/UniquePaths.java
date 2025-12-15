package twoddynamicprogramming;

import java.util.Arrays;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[] it: dp) {
            Arrays.fill(it, -1);
        }
        return dfs(0,0,m,n,dp);
    }

    private int dfs(int i, int j, int m, int n, int[][] dp) {
        if(i == m-1 || j == n-1) {
            return 1;
        }
        if(i>=m || j>=n) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int right = dfs(i, j+1, m, n, dp);
        int down = dfs(i+1, j, m, n, dp);
        return dp[i][j] = right + down;
    }
}
