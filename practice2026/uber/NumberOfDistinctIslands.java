package uber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfDistinctIslands {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };
        NumberOfDistinctIslands obj = new NumberOfDistinctIslands();
        System.out.println(obj.countDistinctIslands(grid));
    }

    // Class to find number of distinct islands
    // DFS function
    public void dfs(int row, int col, int baseRow, int baseCol,
                    int[][] grid, boolean[][] vis, List<String> shape) {
        // Mark visited
        vis[row][col] = true;
        // Add relative position
        shape.add((row - baseRow) + "_" + (col - baseCol));

        // 4 directions
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        // Traverse directions
        for (int i = 0; i < 4; i++) {
            int nrow = row + drow[i];
            int ncol = col + dcol[i];

            // Check bounds and if land and not visited
            if (nrow >= 0 && nrow < grid.length &&
                    ncol >= 0 && ncol < grid[0].length &&
                    grid[nrow][ncol] == 1 && !vis[nrow][ncol]) {
                dfs(nrow, ncol, baseRow, baseCol, grid, vis, shape);
            }
        }
    }

    // Main function to return number of distinct islands
    public int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Set<List<String>> set = new HashSet<>();

        // Traverse the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If land and not visited
                if (grid[i][j] == 1 && !vis[i][j]) {
                    List<String> shape = new ArrayList<>();
                    dfs(i, j, i, j, grid, vis, shape);
                    set.add(shape);
                }
            }
        }
        return set.size();
    }
}

