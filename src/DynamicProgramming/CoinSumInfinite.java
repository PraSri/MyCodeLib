package DynamicProgramming;

public class CoinSumInfinite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int coinchange2(int[] A, int B) {

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
