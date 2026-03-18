package DynamicProgramming;

public class MatrixChainMultiplication {

	public static void main(String[] args) {
		System.out.println(solve_v2(new int[] { 45, 17, 34, 27, 12, 22 }));
	}

	// Giving Partial correct may be due to TLE or overflow issues
	public static int solve(int[] A) {
		int n = A.length;
		int[][] cost = new int[n][n];
		for (int l = 2; l < n; l++) {
			for (int i = 0; i < n - l; i++) {
				int j = i + l;
				cost[i][j] = 1000000;
				for (int k = i + 1; k < j; k++) {
					int x = cost[i][k] + cost[k][j] + (A[i] * A[k] * A[j]);
					if (x < cost[i][j])
						cost[i][j] = x;
				}
			}
		}

		return cost[0][n - 1];
	}

	public static int solve_v2(int[] A) {
		int n = A.length;
		int[][] cost = new int[n][n];
		for (int l = 2; l < n; l++) {
			for (int i = 0; i < n - l; i++) {
				int j = i + l;
				cost[i][j] = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; k++) {
					long lg = A[i] * A[k] * A[j];
					int x = cost[i][k] + cost[k][j] + (int) lg;
					if (x < cost[i][j])
						cost[i][j] = x;
				}
			}
		}

		return cost[0][n - 1];
	}

}
