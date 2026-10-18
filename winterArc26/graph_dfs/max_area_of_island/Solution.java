package graph_dfs.max_area_of_island;

public class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        int best = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    best = Math.max(best, area(grid, r, c));
                }
            }
        }
        return best;
    }

    private int area(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 0;
        return 1 + area(grid, r + 1, c) + area(grid, r - 1, c) + area(grid, r, c + 1) + area(grid, r, c - 1);
    }
}
