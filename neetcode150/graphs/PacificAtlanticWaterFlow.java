package graphs;

import java.util.*;

public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> res = new ArrayList<>();

        int n = heights.length;

        if (n < 1) {
            return res;
        }

        int m = heights[0].length;

        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];

        Queue<Pair> aq = new LinkedList<>();
        Queue<Pair> pq = new LinkedList<>();

        // vertical border
        for (int i = 0; i < n; i++) {
            // right side in atlantic
            aq.offer(new Pair(i, m - 1));
            atlantic[i][m - 1] = true;
            // left side in pacific
            pq.offer(new Pair(i, 0));
            pacific[i][0] = true;
        }
        // horizontal border
        for (int i = 0; i < m; i++) {
            // up side pacific
            pq.offer(new Pair(0, i));
            pacific[0][i] = true;
            // downside atlantic
            aq.offer(new Pair(n - 1, i));
            atlantic[n - 1][i] = true;
        }

        // call bfs on each ocean
        // the bfs will fill the visited arrays
        bfs(heights, atlantic, aq);
        bfs(heights, pacific, pq);

        // find if visited to both atlantic & pacific then put in ans
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (atlantic[i][j] && pacific[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void bfs(int[][] a, boolean[][] visited, Queue<Pair> q) {
        int n = a.length;
        int m = a[0].length;
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            for (int[] dir : directions) {
                int x = curr.x + dir[0];
                int y = curr.y + dir[1];
                if (x < 0 || y < 0 || x >= n || y >= m ||
                        visited[x][y] || a[x][y] < a[curr.x][curr.y]) {
                    continue;
                }
                visited[x][y] = true;
                q.offer(new Pair(x, y));
            }
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
