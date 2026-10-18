package dp_grid.longest_increasing_path_in_matrix;

public class Solution {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int best = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                best = Math.max(best, dfs(matrix, r, c, memo));
            }
        }
        return best;
    }

    private int dfs(int[][] matrix, int r, int c, int[][] memo) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }
        int best = 1;
        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nc >= 0 && nr < matrix.length && nc < matrix[0].length && matrix[nr][nc] > matrix[r][c]) {
                best = Math.max(best, 1 + dfs(matrix, nr, nc, memo));
            }
        }
        memo[r][c] = best;
        return best;
    }
}
