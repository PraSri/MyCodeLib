package graphs;

import java.util.LinkedList;
import java.util.Queue;

/***Walls and Gates
 https://leetcode.com/problems/walls-and-gates/

 Battleships in a Board
 https://leetcode.com/problems/battleships-in-a-board/

 Detonate the Maximum Bombs
 https://leetcode.com/problems/detonate-the-maximum-bombs/

 Escape the Spreading Fire
 https://leetcode.com/problems/escape-the-spreading-fire/*/

public class RottingFruit {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int fc = 0;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j));
                } else if (grid[i][j] == 1) {
                    fc++;
                }
            }
        }
        if (fc == 0) {
            return 0;
        }
        int min = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            min++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair curr = q.poll();
                int x = curr.x;
                int y = curr.y;
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] == 2 || grid[nx][ny] == 0) {
                        continue;
                    }
                    grid[nx][ny] = 2;
                    Pair nCurr = new Pair(nx, ny);
                    q.add(nCurr);
                    fc--;
                }
            }
        }
        return fc == 0 ? min - 1 : -1;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
