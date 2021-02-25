/**
 * 
 */
package Graphs;

/**
 * @author PrakharGuest
 *
 */
public class NumberOfIslands {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] A = new int[][] { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 } };

//		int[][] A = new int[][] { { 1, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 0, 0, 1 } };

		System.out.println(solve(A));
	}

	/*
	 * Given a matrix of integers A of size N x M consisting of 0 and 1. A group of
	 * connected 1's forms an island. From a cell (i, j) such that A[i][j] = 1 you
	 * can visit any cell that shares a corner with (i, j) and value in that cell is
	 * 1.
	 * 
	 * More formally, from any cell (i, j) if A[i][j] = 1 you can visit:
	 * 
	 * (i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1. (i, j-1) if (i,
	 * j-1) is inside the matrix and A[i][j-1] = 1. (i+1, j) if (i+1, j) is inside
	 * the matrix and A[i+1][j] = 1. (i, j+1) if (i, j+1) is inside the matrix and
	 * A[i][j+1] = 1. (i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1]
	 * = 1. (i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1. (i-1,
	 * j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1. (i+1, j-1) if
	 * (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
	 * 
	 * Return the number of islands.
	 * 
	 * NOTE: Rows are numbered from top to bottom and columns are numbered from left
	 * to right.
	 * 
	 */

	public static int solve(int[][] A) {

		int count = 0;
		int n = A.length;
		int m = A[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (A[i][j] == 1) {
					dfs(A, i, j);
					count++;
				}
			}
		}

		return count;

	}

	private static void dfs(int[][] a, int i, int j) {

		if (i < 0 || j < 0 || i >= a.length || j >= a[0].length || a[i][j] != 1) {
			return;
		}

		a[i][j] = 0;

		dfs(a, i - 1, j);// top
		dfs(a, i - 1, j - 1);// top left
		dfs(a, i - 1, j + 1);// top right
		dfs(a, i + 1, j);// below
		dfs(a, i + 1, j - 1);// below left
		dfs(a, i + 1, j + 1);// below right
		dfs(a, i, j + 1);// right
		dfs(a, i, j - 1);// left

	}

}
