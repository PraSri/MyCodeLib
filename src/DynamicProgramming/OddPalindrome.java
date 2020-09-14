package DynamicProgramming;

public class OddPalindrome {

	public static void main(String[] args) {
		int[] res = solve("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		for (long i : res) {
			System.out.print(i + ", ");
		}
	}

	public static int[] solve(String A) {

		int n = A.length();

		String s = A;
		String r = new StringBuilder(s).reverse().toString();

		long mod = 1000000007;

		long[][] dp = new long[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {

				dp[i][j] += dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];

				if (s.charAt(i - 1) == r.charAt(j - 1)) {
					dp[i][j] += 1 + dp[i - 1][j - 1];
				}

				dp[i][j] = (dp[i][j] + mod) % mod;

			}
		}

		long[] res = new long[n];

		int j = n;
		for (int i = 1; i <= n; i++) {
			res[i - 1] = 1 + dp[i - 1][j - 1];
			j--;
		}

		int[] ans = new int[n];

		for (int i = 0; i < n; i++) {
			ans[i] = (int) res[i];
		}

		return ans;

	}

}
