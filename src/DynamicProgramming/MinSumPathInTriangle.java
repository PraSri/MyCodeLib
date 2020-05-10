package DynamicProgramming;

import java.util.ArrayList;

public class MinSumPathInTriangle {

	public static void main(String[] args) {
	}

	public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
		int n = a.size();
		int m = a.get(0).size();
		int[] dp = new int[n];
		// Base case
		// for the last row the values themseleves are the min path
		for (int i = 0; i < a.get(n - 1).size(); i++) {
			dp[i] = a.get(n - 1).get(i);
		}
		// Bottom up manner
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j < a.get(i).size(); j++) {
				dp[j] = Math.min(dp[j], dp[j + 1]) + a.get(i).get(j);
			}
		}

		return dp[0];
	}

}
