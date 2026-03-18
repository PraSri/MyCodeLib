package challenge_yourself;

import java.util.*;

public class ShortestBridge {

    // represents 4 directions
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // to represent a cell
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean checkBoundary(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }


    List<Pair> bfsQueue;

    // recursively check neighbours & insert in queue for 1st island, say A
    private void dfs(int[][] grid, int x, int y, int n) {
        grid[x][y] = 2; // visited
        bfsQueue.add(new Pair(x, y));
        for (int[] dir : directions) {
            int curX = x + dir[0], curY = y + dir[1];
            // check boundary conditions & visited or not
            if (checkBoundary(curX, curY, n) && grid[curX][curY] == 1) {
                dfs(grid, curX, curY, n);
            }
        }
    }

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int firstX = -1, firstY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    firstX = i;
                    firstY = j;
                    break;
                }
            }
        }

        bfsQueue = new ArrayList<>();
        dfs(grid, firstX, firstY, n);

        // do bfs from source island A to find shortest distance to destination island B
        int distance = 0;
        while (!bfsQueue.isEmpty()) {
            List<Pair> newbfs = new ArrayList<>(); // to capture the intermediate cells to reach B in each iteration
            for (Pair pair : bfsQueue) {
                for (int[] dir : directions) {
                    int curX = pair.x + dir[0];
                    int curY = pair.y + dir[1];
                    // check boundary conditions
                    if (checkBoundary(curX, curY, n)) {
                        if (grid[curX][curY] == 1) {
                            return distance;
                        } else if (grid[curX][curY] == 0) {
                            newbfs.add(new Pair(curX, curY));
                            grid[curX][curY] = -1;
                        }
                    }
                }
            }
            bfsQueue = newbfs;
            distance++;
        }
        return distance;
    }

}
