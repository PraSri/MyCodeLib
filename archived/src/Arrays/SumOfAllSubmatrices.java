package Arrays;

public class SumOfAllSubmatrices {

	/*
	 * Given a 2D Matrix A of dimensions N*N, we need to return sum of all possible
	 * submatrices.
	 */

	public int solve(int[][] A) {
		int sum = 0;
		int n = A.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += (i + 1) * (j + 1) * (n - i) * (n - j) * A[i][j];
			}
		}
		return sum;
	}

}
