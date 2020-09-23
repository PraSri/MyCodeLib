package Graphs;

public class BlackShapes {

	public static void main(String[] args) {
	}

	public int black(String[] A) {

		char[][] c = new char[A.length][A[0].length()];
		int it = 0;
		for (String s : A) {
			c[it++] = s.toCharArray();
		}

		int count = 0;

		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				if (c[i][j] == 'X') {
					dfs(c, i, j);
					count++;
				}
			}
		}

		return count;

	}

	private void dfs(char[][] c, int i, int j) {
		if (i < 0 || j < 0 || i >= c.length || j >= c[0].length || c[i][j] != 'X') {
			return;
		}
		c[i][j] = 'O';

		dfs(c, i + 1, j);
		dfs(c, i, j + 1);
		dfs(c, i - 1, j);
		dfs(c, i, j - 1);
//		dfs(c, i + 1, j + 1);
//		dfs(c, i - 1, j - 1);
//		dfs(c, i - 1, j + 1);
//		dfs(c, i + 1, j - 1);
	}

}
