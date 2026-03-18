package DynamicProgramming;

public class MinSumPathInMatrix {
	static boolean dp = true;

	public int minPathSum(int[][] A) {
		if (dp) {
			return minPathDP(A);
		}
		return minSum(A, A.length - 1, A[0].length - 1);
	}

	// TLE for large number of inputs
	public static int minSum(int[][] a, int i, int j) {

		// this is the exit of the recursion
		if (i == 0 && j == 0) {
			return a[i][j];
		}

		/** when we reach the first row, we could only move horizontally. */
		if (i == 0) {
			return a[i][j] + minSum(a, i, j - 1);
		}

		/** when we reach the first column, we could only move vertically. */

		if (j == 0) {
			return a[i][j] + minSum(a, i - 1, j);
		}

		/** we want the min sum path so we pick the cell with the less value */
		return a[i][j] + Math.min(minSum(a, i, j - 1), minSum(a, i - 1, j));

	}

	public static int minPathDP(int[][] a) {

		int m = a.length;// no. of rows
		int n = a[0].length;// no. of columns

		int[][] sum = new int[m][n];

		sum[0][0] = a[0][0];

		for (int i = 1; i < m; i++) {
			sum[i][0] = sum[i - 1][0] + a[i][0];
		}

		for (int j = 1; j < n; j++)
			sum[0][j] = sum[0][j - 1] + a[0][j];

		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				sum[i][j] = Math.min(sum[i - 1][j] + a[i][j], sum[i][j - 1] + a[i][j]);

		return sum[m - 1][n - 1];

	}

}
