package DynamicProgramming;

public class IntersectingChordsInCircle {

	public static void main(String[] args) {

	}

	public int chordCnt(int A) {

		int n = 2 * A;

		long mod = 1000000007;

		long[] dp = new long[n + 1];

		dp[0] = 1;
		dp[2] = 1;

		for (int i = 4; i <= n; i += 2) {
			for (int j = 0; j <= i - 2; j += 2) {
				long l = dp[j] * dp[i - 2 - j];
				dp[i] = (dp[i] % mod + l % mod) % mod;
			}
		}

		return (int) (dp[n] % mod);

	}

}
