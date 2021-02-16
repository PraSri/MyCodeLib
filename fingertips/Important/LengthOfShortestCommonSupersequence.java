package Important;

public class LengthOfShortestCommonSupersequence {

	public static void main(String[] args) {

		System.out.println(lengthOfShortestCommonSupersequence("geek", "eke"));

		System.out.println(lengthOfShortestCommonSupersequence("AGGTAB", "GXTXAYB"));

		System.out.println(recursiveSCSLength("geek", "eke", 4, 3));

		System.out.println(recursiveSCSLength("AGGTAB", "GXTXAYB", 6, 7));
		
		System.out.println(SCSLength_DP("geek", "eke"));

		System.out.println(SCSLength_DP("AGGTAB", "GXTXAYB"));

	}

	public static int lengthOfShortestCommonSupersequence(String x, String y) {

		int ans = 0;

		int lcsLength = PrintLCS.getLCSMatrix(x, y)[x.length()][y.length()];

		int sumOfLengthOfBothStrings = x.length() + y.length();

		ans = sumOfLengthOfBothStrings - lcsLength;

		return ans;
	}

	public static int recursiveSCSLength(String x, String y, int n, int m) {
		if (n == 0)
			return m;
		if (m == 0)
			return n;
		if (x.charAt(n - 1) == y.charAt(m - 1)) {
			return 1 + recursiveSCSLength(x, y, n - 1, m - 1);
		}
		return 1 + Math.min(recursiveSCSLength(x, y, n - 1, m), recursiveSCSLength(x, y, n, m - 1));
	}
	
	public static int SCSLength_DP(String x , String y) {
		int n = x.length();
		int m = y.length();
		int[][] dp = getSCSMatrix(x, y, n, m);
		return dp[n][m];
	}

	public static int[][] getSCSMatrix(String x, String y, int n, int m) {
		int[][] dp = new int[n+1][m+1];
		for(int i = 0;i<=n;i++) {
			for(int j = 0;j<=m;j++) {
				if(i==0) {
					dp[i][j]=j;
				}else if(j==0) {
					dp[i][j] = i;
				}else if(x.charAt(i-1)==y.charAt(j-1)) {
					dp[i][j] = 1+dp[i-1][j-1];
				}else {
					dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp;
	}

}
