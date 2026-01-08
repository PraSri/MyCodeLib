package graphs;

/***Number of Islands
 https://leetcode.com/problems/number-of-islands/

 Battleships in a Board
 https://leetcode.com/problems/battleships-in-a-board/

 Island Perimeter
 https://leetcode.com/problems/island-perimeter/

 Largest Submatrix With Rearrangements
 https://leetcode.com/problems/largest-submatrix-with-rearrangements/

 Detonate the Maximum Bombs
 https://leetcode.com/problems/detonate-the-maximum-bombs/

 Maximum Number of Fish in a Grid
 https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/*/

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(i, j, grid));
            }
        }
        return ans;
    }

    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int up = dfs(i - 1, j, grid);
        int down = dfs(i + 1, j, grid);
        int left = dfs(i, j - 1, grid);
        int right = dfs(i, j + 1, grid);
        return 1 + up + down + left + right;
    }
}
