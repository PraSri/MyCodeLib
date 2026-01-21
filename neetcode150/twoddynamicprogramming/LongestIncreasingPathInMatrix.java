package twoddynamicprogramming;

import java.util.Arrays;

//Number of Increasing Paths in a Grid - https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/

public class LongestIncreasingPathInMatrix {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] dp;
    int ROWS;
    int COLS;

    public int longestIncreasingPath(int[][] matrix) {

        ROWS = matrix.length;
        COLS = matrix[0].length;
        dp = new int[ROWS][COLS];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int ans = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, Integer.MIN_VALUE));
            }
        }

        return ans;
    }

    //Har cell se longest increasing path find karo,
    //aur same cell ko baar-baar calculate na karo (memoization).

    private int dfs(int[][] matrix, int i, int j, int prev) {
        if (i >= ROWS || j >= COLS || i < 0 || j < 0 || matrix[i][j] <= prev) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int res = 1;
        for (int[] dir : dirs) {
            res = Math.max(res, 1 + dfs(matrix, i + dir[0], j + dir[1], matrix[i][j]));
        }
        return dp[i][j] = res;
    }



    /**
     * Number of Increasing Paths in a Grid
     * https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/
     */
    public static class NumberOfIncreasingPathsInAGrid {
    }
}
