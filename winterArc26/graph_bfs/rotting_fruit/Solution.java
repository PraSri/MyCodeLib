package graph_bfs.rotting_fruit;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        int fresh = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }
        int minutes = 0;
        while (fresh > 0 && !queue.isEmpty()) {
            minutes++;
            for (int size = queue.size(); size > 0; size--) {
                int[] cell = queue.poll();
                for (int[] d : DIRS) {
                    int r = cell[0] + d[0];
                    int c = cell[1] + d[1];
                    if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        fresh--;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }
}
