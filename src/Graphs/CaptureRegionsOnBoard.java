package TestPackage;

import java.util.ArrayList;

public class CaptureRegionsOnBoard {

	public static void main(String[] args) {
	}

	public void solve(ArrayList<ArrayList<Character>> a) {

		int rows = a.size();
		int cols = a.get(0).size();

		// first column and last column

		for (int i = 0; i < rows; i++) {

			if (a.get(i).get(0) == 'O') {
				dfs(i, 1, a);
			}

			if (a.get(i).get(cols - 1) == 'O') {
				dfs(i, cols - 2, a);
			}

		}

		// first and last row

		for (int j = 0; j < cols; j++) {
			if (a.get(0).get(j) == 'O') {
				dfs(1, j, a);
			}

			if (a.get(rows - 1).get(j) == 'O') {
				dfs(rows - 2, j, a);
			}
		}

//		flip 'O' to 'X' & '8' to 'O'

		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++) {
				if (a.get(i).get(j) == 'O') {
					a.get(i).set(j, 'X');
				} else if (a.get(i).get(j) == '*') {
					a.get(i).set(j, 'O');
				}
			}
		}

	}

	private void dfs(int i, int j, ArrayList<ArrayList<Character>> a) {
		if (i <= 0 || j >= a.get(0).size() - 1 || i >= a.size() - 1 || j <= 0 || a.get(i).get(j) == 'X'
				|| a.get(i).get(j) == '*') {
			return;
		}

		a.get(i).set(j, '*');
		dfs(i + 1, j, a);
		dfs(i - 1, j, a);
		dfs(i, j - 1, a);
		dfs(i, j + 1, a);
	}
}
