package DynamicProgramming;

import java.util.Arrays;

public class WaysToHaveFun {

	public static void main(String[] args) {
		long mod = 1000000007;
		long x = (-109817045 + mod) % mod;
		System.out.println(x);

	}

	/***
	 * https://leetcode.com/discuss/interview-question/726978/number-of-ways-to-have-breakfast-dp-microsoft
	 * 
	 * 
	 * Find the number of ways you can have fun in A days, given you can sleep every
	 * day, Pizza can be eaten every alternate day and you can watch Tv shows every
	 * two days.
	 * 
	 * Since the answer could be large, return answer % 109 + 7.
	 * 
	 */

	public int solve(int A) {

		long[][] dp = new long[A + 1][3];

		long mod = 1000000007;

		dp[1][0] = dp[1][1] = dp[1][2] = 1;

		for (int i = 2; i <= A; i++) {
			dp[i][0] = (dp[i - 1][0] % mod + dp[i - 1][1] % mod + dp[i - 1][2] % mod) % mod;
			dp[i][1] = (dp[i - 1][0] % mod + dp[i - 1][2] % mod) % mod;
			dp[i][2] = (dp[i - 1][0] % mod + dp[i - 1][1] % mod - 2 * dp[i - 2][2] % mod) % mod;
		}

		long ans = (dp[A][0] % mod + dp[A][1] % mod + dp[A][2] % mod) % mod;

		if (ans < 0) {
			ans = (ans + mod) % mod;
		}

		return (int) ans;

	}

}
