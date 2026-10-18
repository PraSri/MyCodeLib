package hashing.detect_squares;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static class DetectSquares {
        private static final int MAX = 1001;
        private final int[][] count = new int[MAX][MAX];
        private final List<int[]> distinct = new ArrayList<>();

        public DetectSquares() {
        }

        public void add(int[] point) {
            if (count[point[0]][point[1]]++ == 0) {
                distinct.add(point);
            }
        }

        public int count(int[] point) {
            int x = point[0];
            int y = point[1];
            int total = 0;
            for (int[] p : distinct) {
                int px = p[0];
                int py = p[1];
                if (Math.abs(px - x) != Math.abs(py - y) || px == x) {
                    continue;
                }
                total += count[px][py] * count[x][py] * count[px][y];
            }
            return total;
        }
    }
}
