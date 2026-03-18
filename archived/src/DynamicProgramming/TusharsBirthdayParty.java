package DynamicProgramming;

public class TusharsBirthdayParty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		solve(new int[] { 2, 3, 1, 5, 4 }, new int[] { 3, 2, 4, 1 }, new int[] { 1, 2, 5, 10 });

	}

	public static int solve(final int[] A, final int[] B, final int[] C) {

		int[] eatCap = A;
		int[] filCap = B;
		int[] cost = C;
		int maxCap = Integer.MIN_VALUE;
		for (int i = 0; i < eatCap.length; i++) {
			maxCap = Math.max(maxCap, eatCap[i]);
		}

		int[][] dp = new int[maxCap + 1][filCap.length + 1];

			for (int j = 0; j <= filCap.length; j++) {
				dp[0][j] = 0;
			}

		for (int i = 1; i <= maxCap; i++) {
			dp[i][0] = Integer.MAX_VALUE;
		}

		for (int i = 1; i <= maxCap; i++) {
			for (int j = 1; j <= filCap.length; j++) {
				int x = i - filCap[j - 1];
				if (x >= 0) {
					int curFilCap = filCap[j - 1];
					int curCost = cost[j - 1];
					dp[i][j] = Math.min(dp[i][j - 1], dp[i - curFilCap][j] + curCost);
				} else {
					dp[i][j] = dp[i][j - 1];
				}

			}
		}

		int ans = 0;
		for (int i = 0; i < maxCap + 1; i++) {
//			System.out.println(dp[i][filCap.length]);
//			ans += dp[i][filCap.length];
			for (int j = 0; j <= filCap.length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}

		return ans;
	}

}
