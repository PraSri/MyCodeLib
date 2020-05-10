package DynamicProgramming;

public class MaxSumWithoutAdjacentElements {

	public static void main(String[] args) {

//		System.out.println(adjacent(new int[][] { { 1, 2, 3, 4 }, { 2, 3, 4, 5 } }));
		System.out.println(adjacent(new int[][] { { 1 }, { 2 } }));

	}

	public static int adjacent(int[][] A) {
		int n = A[0].length;
//		A[0][0] = 1;
//		A[1][0] = 2;
		if (n == 1) {
			return Math.max(A[0][0], A[1][0]);
		}
		int[] dp = new int[n];

		dp[0] = Math.max(A[0][0], A[1][0]);

		dp[1] = Math.max(dp[0], Math.max(A[0][1], A[1][1]));

		for (int i = 2; i < n; i++) {

			dp[i] = Math.max(dp[i - 1], dp[i - 2] + Math.max(A[0][i], A[1][i]));
			System.out.println(dp[i]);

		}

		return dp[n - 1];

	}

}
