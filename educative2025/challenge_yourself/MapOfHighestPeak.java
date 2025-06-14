package challenge_yourself;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MapOfHighestPeak {
    public int[][] highestPeak(int[][] isWater) {
        int rows = isWater.length;
        int cols = isWater[0].length;
        int[][] cellsHeight = new int[rows][cols];
        for(int[] row: cellsHeight) {
            Arrays.fill(row, -1);
        }
        Queue<Pair> queue = new LinkedList<>();
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols;j++) {
                if(isWater[i][j]==1) {
                    queue.add(new Pair(i, j));
                    cellsHeight[i][j] = 0;
                }
            }
        }

        int heightOfNextLayer = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0;i<size;i++) {
                Pair p = queue.poll();
                for(int[] dir : directions) {
                    int x = p.x + dir[0];
                    int y = p.y + dir[1];
                    if(checkBoundary(x, y, rows, cols) && cellsHeight[x][y] == -1) {
                        cellsHeight[x][y] = heightOfNextLayer;
                        queue.add(new Pair(x,y));
                    }
                }
            }
            heightOfNextLayer++;
        }
        return cellsHeight;
    }
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

    private boolean checkBoundary(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
