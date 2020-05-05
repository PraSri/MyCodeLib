package DynamicProgramming;

public class LetsParty {

	public int solve(int A) {

		if (A == 1) {
			return 1;
		}

		if (A == 2) {
			return 2;
		}

		int[] dp = new int[A + 1];

		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		int mod = 10003;

		for (int i = 3; i <= A; i++) {

			dp[i] = (dp[i - 1] % mod + ((i - 1) % mod * dp[i - 2] % mod) % mod) % mod;
		}

		return dp[A];

	}

}
//ans[i] = (ans[i-1]%mod + ((i-1)%mod*ans[i-2]%mod)%mod)%mod;