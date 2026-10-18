package shortest_path.swim_in_rising_water;

import java.util.PriorityQueue;

public class Solution {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] seen = new boolean[n][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        heap.offer(new int[]{grid[0][0], 0, 0});
        seen[0][0] = true;
        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            int time = top[0];
            if (top[1] == n - 1 && top[2] == n - 1) {
                return time;
            }
            for (int[] d : DIRS) {
                int r = top[1] + d[0];
                int c = top[2] + d[1];
                if (r >= 0 && c >= 0 && r < n && c < n && !seen[r][c]) {
                    seen[r][c] = true;
                    heap.offer(new int[]{Math.max(time, grid[r][c]), r, c});
                }
            }
        }
        return -1;
    }
}
