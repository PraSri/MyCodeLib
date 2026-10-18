package graph_dfs.pacific_atlantic_water_flow;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int r = 0; r < m; r++) {
            climb(heights, r, 0, pacific, Integer.MIN_VALUE);
            climb(heights, r, n - 1, atlantic, Integer.MIN_VALUE);
        }
        for (int c = 0; c < n; c++) {
            climb(heights, 0, c, pacific, Integer.MIN_VALUE);
            climb(heights, m - 1, c, atlantic, Integer.MIN_VALUE);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(List.of(r, c));
                }
            }
        }
        return result;
    }

    private void climb(int[][] h, int r, int c, boolean[][] seen, int prevHeight) {
        if (r < 0 || c < 0 || r == h.length || c == h[0].length || seen[r][c] || h[r][c] < prevHeight) {
            return;
        }
        seen[r][c] = true;
        climb(h, r + 1, c, seen, h[r][c]);
        climb(h, r - 1, c, seen, h[r][c]);
        climb(h, r, c + 1, seen, h[r][c]);
        climb(h, r, c - 1, seen, h[r][c]);
    }
}
