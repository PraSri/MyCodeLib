public class RottenOranges {
    public class Pair {
		public int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

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
