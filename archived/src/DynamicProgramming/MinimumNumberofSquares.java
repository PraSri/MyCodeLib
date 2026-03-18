package DynamicProgramming;

public class MinimumNumberofSquares {

	public static void main(String[] args) {
		System.out.println(countMinSquares(4));
	}

	/*
	 * 
	 * Given an integer A. Return minimum count of numbers, sum of whose squares is
	 * equal to A.
	 * 
	 */

	public static int countMinSquares(int A) {

		if (A <= 3)
			return A;

		int[] dp = new int[A + 1];

		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;

		/*
		 * A = 4
		 * 
		 * 
		 * 
		 */

		for (int i = 4; i <= A; i++) {

			dp[i] = i; // dp[4] = 4

			for (int x = 1; x <= Math.ceil(Math.sqrt(i)); x++) {

				int t = x * x; // x = 1 t = 1 i=4
				if (t > i) {// x = 2 t = 4 i = 4
					break;
				} else {
					dp[i] = Math.min(dp[i], 1 + dp[i - t]); // d[i] = min(4,1+dp[3]) = 3
					// d[4] = min(3,1+dp[4-4]) = 0
					System.out.println("i=" + i + " x=" + x + " dp[i]=" + dp[i] + " ");
				}

			}

		}

		return dp[A];
	}

}
