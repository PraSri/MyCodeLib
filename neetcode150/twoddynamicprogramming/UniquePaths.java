package twoddynamicprogramming;

import java.util.Arrays;

/***Unique Paths II (Medium)
 https://leetcode.com/problems/unique-paths-ii/

 Minimum Path Sum (Medium)
 https://leetcode.com/problems/minimum-path-sum/

 Dungeon Game (Hard)
 https://leetcode.com/problems/dungeon-game/

 Minimum Path Cost in a Grid (Medium)
 https://leetcode.com/problems/minimum-path-cost-in-a-grid/

 Minimum Cost Homecoming of a Robot in a Grid (Medium)
 https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/

 Number of Ways to Reach a Position After Exactly k Steps (Medium)
 https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/

 Paths in Matrix Whose Sum Is Divisible by K (Hard)
 https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/*/

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] it : dp) {
            Arrays.fill(it, -1);
        }
        // starting point is 0,0
        // destination is m-1,n-1
        // keep intermediate states in dp
        // dp[i][j] represents the steps required to reach i,j
        return dfs(0, 0, m, n, dp);
    }

    private int dfs(int i, int j, int m, int n, int[][] dp) {
        if (i == m - 1 || j == n - 1) {
            return 1;
        }
        if (i >= m || j >= n) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int right = dfs(i, j + 1, m, n, dp);
        int down = dfs(i + 1, j, m, n, dp);
        return dp[i][j] = right + down;
    }

    /**
     * Unique Paths II
     * https://leetcode.com/problems/unique-paths-ii
     */
    public static class UniquePathsIi {
    }

    /**
     * Minimum Path Sum
     * https://leetcode.com/problems/minimum-path-sum
     */
    public static class MinimumPathSum {
    }

    /**
     * Dungeon Game
     * https://leetcode.com/problems/dungeon-game
     */
    public static class DungeonGame {
    }

    /**
     * Minimum Path Cost in a Grid
     * https://leetcode.com/problems/minimum-path-cost-in-a-grid
     */
    public static class MinimumPathCostInAGrid {
    }

    /**
     * Minimum Cost Homecoming of a Robot in a Grid
     * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid
     */
    public static class MinimumCostHomecomingOfARobotInAGrid {
    }

    /**
     * Number of Ways to Reach a Position After Exactly k Steps
     * https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps
     */
    public static class NumberOfWaysToReachAPositionAfterExactlyKSteps {
    }

    /**
     * Paths in Matrix Whose Sum Is Divisible by K
     * https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k
     */
    public static class PathsInMatrixWhoseSumIsDivisibleByK {
    }
}
