package graph_bfs.islands_and_treasure;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    private static final int INF = Integer.MAX_VALUE;
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void islandsAndTreasure(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    queue.offer(new int[]{r, c});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRS) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == INF) {
                    grid[r][c] = grid[cell[0]][cell[1]] + 1;
                    queue.offer(new int[]{r, c});
                }
            }
        }
    }
}
