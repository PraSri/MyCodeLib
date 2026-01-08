package graphs;

import java.util.*;

public class PacificAtlanticWaterFlow {

    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> res = new ArrayList<>();

        int n = heights.length;

        if (n < 1) {
            return res;
        }

        int m = heights[0].length;

        boolean[][] visitPacific = new boolean[n][m];
        boolean[][] visitAtlantic = new boolean[n][m];

        Queue<Pair> atlanticQueue = new LinkedList<>();
        Queue<Pair> pacificQueue = new LinkedList<>();

        // vertical border
        for (int i = 0; i < n; i++) {
            // right side in visitAtlantic
            atlanticQueue.offer(new Pair(i, m - 1));
            visitAtlantic[i][m - 1] = true;
            // left side in visitPacific
            pacificQueue.offer(new Pair(i, 0));
            visitPacific[i][0] = true;
        }
        // horizontal border
        for (int i = 0; i < m; i++) {
            // up side visitPacific
            pacificQueue.offer(new Pair(0, i));
            visitPacific[0][i] = true;
            // downside visitAtlantic
            atlanticQueue.offer(new Pair(n - 1, i));
            visitAtlantic[n - 1][i] = true;
        }

        // call bfs on each ocean
        // the bfs will fill the visited arrays
        bfs(heights, visitAtlantic, atlanticQueue);
        bfs(heights, visitPacific, pacificQueue);

        // find if visited to both visitAtlantic & visitPacific then put in ans
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visitAtlantic[i][j] && visitPacific[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

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
