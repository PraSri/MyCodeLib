package BacktrackingRecursion;

import java.util.ArrayList;

public class Soduko {

	public static void main(String[] args) {

		System.out.println((char) (1 + '0'));

	}

	public void solveSudoku(ArrayList<ArrayList<Character>> a) {
		int n = a.size();

		char[][] mat = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = a.get(i).get(j);
			}
		}
		solveSoduko(mat, 0, 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a.get(i).set(j, mat[i][j]);
			}
		}
	}

	private boolean solveSoduko(char[][] a, int i, int j) {

		if (i == a.length) {
			return true;
		}

		if (j == a.length) {
			return solveSoduko(a, i + 1, 0);
		}

		if (a[i][j] != '.') {
			return solveSoduko(a, i, j + 1);
		}

		for (char num = '1'; num <= '9'; num++) {

			if (canPlace(a, i, j, num)) {
				a[i][j] = num;
				boolean couldSolve = solveSoduko(a, i, j + 1);
				if (couldSolve == true) {
					return true;
				}
			}
		}
		a[i][j] = '.';
		return false;

	}

	private boolean canPlace(char[][] a, int i, int j, int num) {

		for (int x = 0; x < a.length; x++) {
			if (a[i][x] == num || a[x][j] == num) {
				return false;
			}
		}

		int rn = (int) Math.sqrt(a.length);
		int sx = (i / rn) * rn;
		int sy = (j / rn) * rn;
		for (int x = sx; x < sx + rn; x++) {
			for (int y = sy; y < sy + rn; y++) {
				if (a[x][y] == num) {
					return false;
				}
			}
		}

		return true;
	}

}
