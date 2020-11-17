package CompanyQuestions;

import java.util.ArrayList;

public class MinimumOfEverySubmatrices {

	public static void main(String[] args) {

		int[][] a = { { -1, 5, 4, 1, -3 }, { 4, 3, 1, 1, 6 }, { 2, -2, 5, 3, 1 }, { 8, 5, 1, 9, -4 },
				{ 12, 3, 5, 8, 1 } };

		int b = 3;

		int[][] res = solve_EfficientApproach(a, b);
		int n = res.length;
		int m = res[0].length;
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {
				System.out.print(res[i][j] + " ");
			}

			System.out.println();
		}

	}

	/*******
	 * Given a 2D integer array A of size N * M and an integer B. You need to find
	 * and return the minimum values for all sub-matrices of size B
	 ***********/

	// giving wrong answer
	// time ccomplexity is O(n*m*k*k)
	public static int[][] solve_BruteForceApproach(int[][] A, int B) {

		int n = A.length;
		int m = A[0].length;
		int k = B;

		int min = Integer.MAX_VALUE;

		ArrayList<ArrayList<Integer>> a = new ArrayList<>();

		for (int i = 0; i < n - k + 1; i++) {
			ArrayList<Integer> b = new ArrayList<Integer>();
			for (int j = 0; j < m - k + 1; j++) {
				for (int p = i; p < k + i; p++) {
					for (int q = j; q < k + j; q++) {
						min = Math.min(min, A[p][q]);
					}
				}
				b.add(min);
			}
			a.add(b);
		}
		n = a.size();
		m = a.get(0).size();
		int[][] res = new int[n][m];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {
				res[i][j] = a.get(i).get(j);
			}
		}

		return res;

	}

	// Time complexity is O(max(n,m)^3)
	public static int[][] solve_EfficientApproach(int[][] A, int B) {

		int n = A.length;
		int m = A[0].length;
		int k = B;

		int[][] res = new int[n - k + 1][m - k + 1];

		// update the smallest elements row-wise

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m - k + 1; j++) {
				int min = Integer.MAX_VALUE;
				for (int l = j; l < j + k; l++) {
					min = Math.min(min, A[i][l]);
				}

				A[i][j] = min;
			}
		}

		// update the smalllest elemnts column-wise

		for (int j = 0; j < m; j++) {

			for (int i = 0; i < n - k + 1; i++) {
				int min = Integer.MAX_VALUE;
				for (int l = i; l < i + k; l++) {
					min = Math.min(min, A[l][j]);
				}

				A[i][j] = min;
			}
		}

		// store the final sub-matrix

		for (int i = 0; i < n - k + 1; i++) {

			for (int j = 0; j < m - k + 1; j++) {
				res[i][j] = A[i][j];
			}
		}

		return res;

	}

}
