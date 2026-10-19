import java.util.*;

public class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Set<String> uniqueShapes = new HashSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    StringBuilder shape = new StringBuilder();
                    dfs(grid, r, c, shape, 'O'); // 'O' for origin
                    uniqueShapes.add(shape.toString());
                }
            }
        }

        return uniqueShapes.size();
    }

    private void dfs(int[][] grid, int r, int c, StringBuilder shape, char dir) {
        grid[r][c] = 0; // Mark visited
        shape.append(dir);

        int[][] dirs = {{-1, 0, 'U'}, {1, 0, 'D'}, {0, -1, 'L'}, {0, 1, 'R'}};
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            char step = (char) d[2];
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == 1) {
                dfs(grid, nr, nc, shape, step);
            }
        }

        shape.append('B'); // Invariant: Backtrack step preserves tree structure
    }
}
