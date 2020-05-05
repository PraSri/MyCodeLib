package DynamicProgramming;

import java.util.ArrayList;

public class MinSumPathInTriangle {

	public static void main(String[] args) {
	}

	public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
		int n = a.size();
		int m = a.get(0).size();
		int[][] dp = new int[n][m];
		// Base case
		dp[n - 1][m - 1] = a.get(n - 1).get(m - 1);
		for (int i = n - 2; i <= 0; i--) {
			for (int j = m - 2; j <= 0; j--) {
				dp[i][j] = a.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
			}
		}
		return dp[0][0];
	}

}
