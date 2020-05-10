package DynamicProgramming;

public class KnapSack0_1 {

	public static void main(String[] args) {

	}

	/*
	 * Given two integer arrays A and B of size N each which represent values and
	 * weights associated with N items respectively.
	 * 
	 * Also given an integer C which represents knapsack capacity.
	 * 
	 * Find out the maximum value subset of A such that sum of the weights of this
	 * subset is smaller than or equal to C.
	 * 
	 * NOTE:
	 * 
	 * You cannot break an item, either pick the complete item, or don’t pick it
	 * (0-1 property).
	 */

	public static int solve(int[] A, int[] B, int C) {

		int n = B.length;

		// dp[i] stores the max profit till capacity i
		int[] dp = new int[C + 1];
		// iterate to all the items
		for (int i = 0; i < n; i++) {

			// iterate reverse order from capacity value C to current item weigth B[i]
			for (int j = C; j >= B[i]; j--) {
				dp[j] = Math.max(dp[j], A[i] + dp[j - B[i]]);
			}

		}

		return dp[C];

	}

}
