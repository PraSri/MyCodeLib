package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public class Pair {
		public int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
	
// 	Given a matrix of integers A of size N x M consisting of 0, 1 or 2.

// Each cell can have three values:

// The value 0 representing an empty cell.

// The value 1 representing a fresh orange.

// The value 2 representing a rotten orange.

// Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten orange becomes rotten. Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1 instead.

// Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them. 

	public int solve(int[][] A) {
		int rows = A.length;
		int cols = A[0].length;
		int fc = 0;
		Queue<Pair> q = new LinkedList<Pair>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (A[i][j] == 2) {
					q.add(new Pair(i, j));
				} else if (A[i][j] == 1) {
					fc++;
				}
			}
		}

		if (fc == 0)
			return 0;
		int time = 0;
		int[] x = { 0, 0, -1, 1 };
		int[] y = { 1, -1, 0, 0 };
		while (!q.isEmpty()) {
			time++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pair p = q.poll();
				for (int j = 0; j < 4; j++) {
					int nx = x[j] + p.x;
					int ny = y[j] + p.y;
					if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || A[nx][ny] == 2 || A[nx][ny]==0)
						continue;
					A[nx][ny] = 2;
					q.add(new Pair(nx, ny));
					fc--;
				}
			}
		}
		return fc == 0 ? time - 1 : -1;
	}
}
