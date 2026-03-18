package Arrays;

import java.util.Arrays;

public class Minimum_operations_of_given_type_to_make_all_elements_of_a_matrix_equal {
	/*
	 * Given a matrix of integers A of size N x M and an integer B.
	 * 
	 * In a single operation, B can be added to or subtracted from any element of
	 * the matrix.
	 * 
	 * Find and return the minimum number of operations required to make all the
	 * elements of the matrix equal and if it impossible return -1 instead.
	 * 
	 * NOTE: Rows are numbered from top to bottom and columns are numbered from left
	 * to right.
	 * 
	 */
	public int solve(int[][] A, int B) {
		int n = A.length;
		int m = A[0].length;
		int[] a = new int[n * m];
		int mod = ((A[0][0] % B) + B) % B;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i * m + j] = A[i][j];
				int x = A[i][j];
				int checkMod = ((x % B) + B) % B;
				// System.out.println(mod);
				if (checkMod != mod) {
					return -1;
				}
			}
		}
		Arrays.sort(a);
		int minOp = 0;
		if ((n * m) % 2 == 0) {
			int med1 = a[(n * m) / 2];
			int med2 = a[(n * m) / 2 + 1];
			int minOp1 = 0;
			int minOp2 = 0;
			for (int i = 0; i < n * m; i++) {
				minOp1 += Math.abs(a[i] - med1) / B;
			}
			for (int i = 0; i < n * m; i++) {
				minOp2 += Math.abs(a[i] - med2) / B;
			}
			minOp = Math.min(minOp1, minOp2);
		} else {
			int med = a[(n * m) / 2];
			for (int i = 0; i < n * m; i++) {
				minOp += Math.abs(a[i] - med) / B;
			}
		}
		return minOp;
	}
}
