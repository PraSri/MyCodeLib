package challenge_yourself;

import java.util.LinkedList;
import java.util.Queue;

public class AsFarFromLandAsPossible {
    public static class Pair {
        public int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public int solve(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (A[i][j] == 1) {
                    q.add(new Pair(i, j));
                }
            }
        }

        int time = 0;
        int[] x = {0, 0, -1, 1};
        int[] y = {1, -1, 0, 0};
        while (!q.isEmpty()) {
            time++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = x[j] + p.x;
                    int ny = y[j] + p.y;
                    if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || A[nx][ny] == 1)
                        continue;
                    A[nx][ny] = 1;
                    q.add(new Pair(nx, ny));
                }
            }
        }
        return time > 0 ? time - 1 : -1;
    }
}
