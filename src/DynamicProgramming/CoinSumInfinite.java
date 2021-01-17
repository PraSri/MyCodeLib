package DynamicProgramming;

public class CoinSumInfinite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(coinchange2(new int[] { 1, 2, 3 }, 5));

	}

	/******
	 * You are given a set of coins A. In how many ways can you make sum B assuming
	 * you have infinite amount of each coin in the set.
	 * 
	 * NOTE:
	 * 
	 * Coins in set A will be unique. Expected space complexity of this problem is
	 * O(B). The answer can overflow. So, return the answer % (106 + 7).
	 *********/

	public static int coinchange2(int[] A, int B) {

		int mod = 1000007;
		int[] dp = new int[B + 1];

		dp[0] = 1;

		for (int a : A) {

			for (int i = a; i <= B; i++) {

				dp[i] += dp[i - a] % mod;

			}

		}

		return dp[B];
	}

}
