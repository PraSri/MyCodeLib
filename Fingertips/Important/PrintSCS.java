package Important;

public class PrintSCS {

	public static void main(String[] args) {

		printSCS("AGGTAB", "GXTXAYB");
		printSCS("acbcf", "abcdaf");
		printSCS("abac", "cab");
	}

	public static void printSCS(String x, String y) {

		int n = x.length();
		int m = y.length();
		int[][] dp = LengthOfShortestCommonSupersequence.getSCSMatrix(x, y, n, m);
		StringBuilder sb = new StringBuilder();
		while (n > 0 && m > 0) {

			if (x.charAt(n - 1) == y.charAt(m - 1)) {
				sb.append(x.charAt(n - 1));
				n--;
				m--;
			} else if (dp[n - 1][m] > dp[n][m - 1]) {
				// pick looser side and move towards winner

				sb.append(y.charAt(m - 1));
				m--;

			} else {
				sb.append(x.charAt(n - 1));
				n--;
			}

		}

		while (n > 0) {
			sb.append(x.charAt(n - 1));
			n--;
		}

		while (m > 0) {
			sb.append(y.charAt(m - 1));
			m--;
		}

		String res = sb.reverse().toString();

		System.out.println(res);

	}

}
