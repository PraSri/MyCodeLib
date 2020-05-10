package Backtracking;

import java.util.ArrayList;

public class NQueens {

	public static void main(String[] args) {

		System.out.println(solveNQueens(2));

	}

	public static ArrayList<ArrayList<String>> solveNQueens(int a) {

		char[][] board = new char[a][a];

		int col = 0;

		ArrayList<ArrayList<String>> res = new ArrayList<>();

		// build the board

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				board[i][j] = '.';
			}
		}

//		for (int i = 0; i < a; i++) {
//			for (int j = 0; j < a; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}

		DFS(board, col, res);

		return res;

	}

	public static void DFS(char[][] board, int col, ArrayList<ArrayList<String>> res) {

		if (col == board.length) {
			res.add(construct(board));
			return;
		}

		for (int i = 0; i < board.length; i++) {

			if (canPlace(board, i, col)) {
				board[i][col] = 'Q';
				DFS(board, col + 1, res);
				board[i][col] = '.';
			}

		}

	}

	public static boolean canPlace(char[][] board, int row, int col) {

		// check in row for queen

		int i = 0;
		while (i < board.length) {
			if (board[row][i] == 'Q') {
				return false;
			}
			i++;
		}

		// check in col for queen

		int j = 0;
		while (j < board.length) {
			if (board[j][col] == 'Q') {
				return false;
			}
			j++;
		}

		// check top left diagonal

		int x = row, y = col;

		while (x >= 0 && y >= 0) {
			if (board[x][y] == 'Q') {
				return false;
			}
			x--;
			y--;

		}

		// check top right diagonal

		x = row;
		y = col;

		while (x >= 0 && y < board.length) {
			if (board[x][y] == 'Q') {
				return false;
			}
			x--;
			y++;
		}

		// check bottom left diagonal

		x = row;
		y = col;

		while (x < board.length && y >= 0) {
			if (board[x][y] == 'Q') {
				return false;
			}
			x++;
			y--;
		}

		// check bottom right diagonal

		x = row;
		y = col;

		while (x < board.length && y < board.length) {
			if (board[x][y] == 'Q') {
				return false;
			}
			x++;
			y++;
		}

		return true;

	}

	public static ArrayList<String> construct(char[][] board) {

		int n = board.length;

		ArrayList<String> res = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String s = new String(board[i]);
			res.add(s);
		}

		return res;

	}

}
