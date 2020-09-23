package TestPackage;

import java.util.LinkedList;
import java.util.Queue;

public class KnightOnChessBoard {

	public static void main(String[] args) {

		KnightOnChessBoard k = new KnightOnChessBoard();
		int x = k.knight(2, 20, 1, 18, 1, 5);
		System.out.println(x);

	}

	public class Cell {
		public int x, y, dist;

		public Cell(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

	}

	public int knight(int A, int B, int C, int D, int E, int F) {

		return minSteps(A, B, C, D, E, F);

	}

	private int minSteps(int rows, int cols, int sx, int sy, int dx, int dy) {

		int[] px = { -2, -1, 1, 2, -2, -1, 1, 2 };
		int[] py = { -1, -2, -2, -1, 1, 2, 2, 1 };

		Queue<Cell> q = new LinkedList<Cell>();

		Cell source = new Cell(sx, sy, 0);

		q.add(source);

		boolean[][] visited = new boolean[rows + 1][cols + 1];

		visited[source.x][source.y] = true;

		while (!q.isEmpty()) {
			Cell c = q.poll();

			if (c.x == dx && c.y == dy) {
				return c.dist;
			}

			// loop for all reachable states

			for (int i = 0; i < 8; i++) {
				int nx = px[i] + c.x;
				int ny = py[i] + c.y;
				// if reachable state is not yet visited and inside board ...push it in queue
				if (insideBoard(rows, cols, nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					Cell reachableCell = new Cell(nx, ny, c.dist + 1);
					q.add(reachableCell);
				}
			}

		}

		return -1;
	}

	private boolean insideBoard(int rows, int cols, int x, int y) {

		if (x >= 1 && x <= rows && y >= 1 && y <= cols)
			return true;
		return false;

	}

}
