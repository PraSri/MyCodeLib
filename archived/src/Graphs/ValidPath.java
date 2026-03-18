package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class ValidPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class Pair {
		public int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public String solve(int A, int B, int C, int D, int[] E, int[] F) {

		int[][] rect = new int[A + 1][B + 1];

		for (int i = 0; i <= A; i++) {
			for (int j = 0; j <= B; j++) {
				for (int k = 0; k < C; k++) {
					if (Math.sqrt(Math.pow(E[k] - i, 2) + Math.pow(F[k] - j, 2)) <= D) {
						rect[i][j] = 1;
						break;
					}
				}
			}
		}

		if (rect[0][0] == 1 || rect[A][B] == 1) {
			return "NO";
		}

		int X[] = { 0, 0, 1, 1, 1, -1, -1, -1 };
		int Y[] = { 1, -1, 0, 1, -1, 0, 1, -1 };

		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0));
		rect[0][0] = 1;
		while (!q.isEmpty()) {
			Pair front = q.poll();
			int x = front.x;
			int y = front.y;
			if (x == A && y == B) {
				return "YES";
			}
			for (int i = 0; i < 8; i++) {
				int nx = x + X[i];
				int ny = y + Y[i];
				if (nx >= 0 && nx <= A && ny >= 0 && ny <= B && rect[nx][ny] == 0) {
					rect[nx][ny] = 1;
					q.add(new Pair(nx, ny));
				}
			}
		}

		return "NO";
	}

}
