package graphs;

import java.util.*;

public class IslandsAndTreasure {
    static class Solution {

        private boolean[][] visited;
        private int INF = 2147483647;
        private int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        public void islandsAndTreasure(int[][] grid) {
            if(true) {
                multiSourceBfs(grid);
                return;
            }
            int n = grid.length;
            int m = grid[0].length;
            visited = new boolean[n][m];
            for(int i = 0;i<n;i++) {
                for(int j = 0;j<m;j++) {
                    if(grid[i][j] == INF) {
                        int x = dfs(i, j, grid);
                        grid[i][j] = x;
                    }
                }
            }
            return;
        }

        private int dfs(int i , int j, int[][] grid) {
            if(i<0 || j<0 || i>=grid.length ||
                    j>=grid[0].length || grid[i][j] ==-1 || visited[i][j]) {
                return INF;
            }
            if(grid[i][j] == 0) {
                return 0;
            }
            visited[i][j] = true;
            int res = INF;
            for(int[] dir : directions) {
                int curr = dfs(i+dir[0],j+dir[1], grid);
                if(curr != INF) {
                    res = Math.min(res, 1 + curr);
                }
            }
            visited[i][j] = false;
            return res;
        }

        // TC & SC - O(m * n)
        private void multiSourceBfs(int[][] grid) {

            Queue<int[]> q = new LinkedList<>();

            int n = grid.length;
            int m = grid[0].length;

            // Finds all 0 (src) at once and
            // puts them into the BFS queue (distance 0).

            for(int i = 0;i<n;i++) {
                for(int j = 0;j<m;j++) {
                    if(grid[i][j]==0) {
                        q.add(new int[]{i,j});
                    }
                }
            }
            // if no source then return
            if(q.isEmpty()) return;

            while(!q.isEmpty()) {
                int[] node = q.poll();
                int x = node[0];
                int y = node[1];
                for(int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] != INF) {
                        continue;
                    }
                    q.add(new int[]{nx, ny});
                    grid[nx][ny] = grid[x][y] + 1;
                }
            }
        }
    }

}
