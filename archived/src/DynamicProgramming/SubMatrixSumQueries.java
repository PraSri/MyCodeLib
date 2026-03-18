package DynamicProgramming;

public class SubMatrixSumQueries {
	public class Solution {
		public int[] solve(int[][] A, int[] B, int[] C, int[] D, int[] E) {
			int n = A.length;
			int m = A[0].length;
//				System.out.println(n + " " + m);// 1000000007
			long[][] ps = new long[n][m];
			ps[0][0] = A[0][0] % 1000000007;

			for (int j = 1; j < m; j++) {
				ps[0][j] = ps[0][j - 1] % 1000000007 + A[0][j] % 1000000007;
			}

			for (int i = 1; i < n; i++) {
				ps[i][0] = ps[i - 1][0] % 1000000007 + A[i][0] % 1000000007;
			}

			for (int i = 1; i < n; i++) {
				for (int j = 1; j < m; j++) {
					ps[i][j] = ps[i - 1][j] % 1000000007 + ps[i][j - 1] % 1000000007 - ps[i - 1][j - 1] % 1000000007
							+ A[i][j] % 1000000007;
				}
			}

//		 		for (int i = 0; i < n; i++) {
//		 			for (int j = 0; j < m; j++) {
//		 				System.out.print(ps[i][j] + " ");
//		 			}
//		 			System.out.println();
//		 		}

			int l = B.length;
			int[] ans = new int[l];
			for (int i = 0; i < l; i++) {
				long x = getSum(ps, B[i] - 1, C[i] - 1, D[i] - 1, E[i] - 1) % 1000000007;
				if (x < 0) {
					x = (x + 1000000007) % 1000000007;
				}
				ans[i] = (int) x;
			}
			return ans;

		}

		public long getSum(long[][] ps, int i, int j, int k, int l) {
			if (i != 0 && j != 0) {
				return ps[k][l] % 1000000007 - ps[i - 1][l] % 1000000007 - ps[k][j - 1] % 1000000007
						+ ps[i - 1][j - 1] % 1000000007;
			} else if (i == 0 && j == 0) {
				return ps[k][l] % 1000000007;
			} else if (i == 0) {
				return ps[k][l] % 1000000007 - ps[k][j - 1] % 1000000007;
			}
			return ps[k][l] % 1000000007 - ps[i - 1][l] % 1000000007;
		}
	}

}
