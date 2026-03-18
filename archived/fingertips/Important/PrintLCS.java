package Important;

public class PrintLCS {

	public static void main(String[] args) {
		printLCS("AGGTAB", "GXTXAYB");

	}
	
	public static int[][] getLCSMatrix(String x, String y){
		int n = x.length();
		int m = y.length();

		int[][] lcs = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 || j == 0) {
					lcs[i][j] = 0;
				} else if (x.charAt(i-1) == y.charAt(j-1)) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}
		
		return lcs;

	}

	public static void printLCS(String x, String y) {

		int n = x.length();
		int m = y.length();

		int[][] lcs = getLCSMatrix(x, y);

		int l = lcs[n][m];
		int k = l;
		char[] s = new char[k + 1];
		s[k] = '$';
		int i = n, j = m;
		while (i > 0 && j > 0) {
			if (x.charAt(i - 1) == y.charAt(j - 1)) {
				s[k - 1] = x.charAt(i - 1);
				i--;
				j--;
				k--;
			} else if (lcs[i - 1][j] > lcs[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}

		System.out.println(String.valueOf(s));

	}

}
