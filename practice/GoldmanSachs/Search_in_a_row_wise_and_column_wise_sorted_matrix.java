package GoldmanSachs;

public class Search_in_a_row_wise_and_column_wise_sorted_matrix {
	/*
	 * Given a matrix of integers A of size N x M and an integer B.
	 * 
	 * In the given matrix every row and column is sorted in increasing order. Find
	 * and return the position of B in the matrix in the given form: If A[i][j] = B
	 * then return (i * 1009 + j) If B is not present return -1.
	 * 
	 * Note 1: Rows are numbered from top to bottom and columns are numbered from
	 * left to right. Note 2: If there are multiple B in A then return the smallest
	 * value of i*1009 +j such that A[i][j]=B.
	 */

	public int solve(int[][] A, int B) {
		int n = A.length; // no of rows
		int m = A[0].length; // no of columns
		int i = 0;
		int j = m - 1;
		int x;
		while (i < n && j >= 0) {
			x = A[i][j];
			// System.out.println("x=" + x);
			if (x == B) {
				return ((i + 1) * 1009 + (j + 1));
			} else if (B < x) {
				j--;
			} else {
				i++;
			}

		}
		return -1;
	}
}
