package DynamicProgramming;

public class RegularExpressionMatch {
	
	// similar to : https://leetcode.com/problems/wildcard-matching/

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static boolean v2Flag = true;

	public int isMatch(final String A, final String B) {

		int n = A.length();
		int m = B.length();

		if (v2Flag) {

			return strmatch(A.toCharArray(), B.toCharArray(), n, m) ? 1 : 0;

		}

		int[][] dp = new int[n + 1][m + 1];

		/*
		 * dp[0][0] = 1, since empty string matches empty pattern
		 * 
		 * A = "" B = ""
		 * 
		 */

		dp[0][0] = 1;

		/*
		 * A = "abc" B = ""
		 * 
		 * dp[i][0] = 0
		 * 
		 * 
		 */

		for (int i = 1; i <= n; i++) {
			dp[i][0] = 0;
		}

		/*
		 * A = "" B = a*a dp[0][j] = 0
		 * 
		 * A = "" B = a*a*a* B = *a
		 * 
		 * 
		 * 
		 */

		for (int j = 1; j < m + 1; j++) {
			if (B.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 1];
			}
		}

		for (int i = 1; i <= n; i++) {
			char curA = A.charAt(i - 1);
			for (int j = 1; j <= m; j++) {
				char curB = B.charAt(j - 1);
				if (curB == '*') {
					dp[i][j] = (dp[i][j - 1] == 1 || dp[i - 1][j] == 1) ? 1 : 0;
				} else if (curB == '?' || curA == curB) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 0;
				}

			}
		}

		return dp[n][m];
	}

	public static boolean strmatch(char txt[], char pat[], int n, int m) {
// empty pattern can only 
// match with empty string. 
// Base Case : 
		if (m == 0)
			return (n == 0);

// step-1 : 
// initailze markers : 
		int i = 0, j = 0, index_txt = -1, index_pat = -1;

		while (i < n) {

// For step - (2, 5) 
			if (j < m && txt[i] == pat[j]) {
				i++;
				j++;
			}

// For step - (3) 
			else if (j < m && pat[j] == '?') {
				i++;
				j++;
			}

// For step - (4) 
			else if (j < m && pat[j] == '*') {
				index_txt = i;
				index_pat = j;
				j++;
			}

// For step - (5) 
			else if (index_pat != -1) {
				j = index_pat + 1;
				i = index_txt + 1;
				index_txt++;
			}

// For step - (6) 
			else {
				return false;
			}
		}

// For step - (7) 
		while (j < m && pat[j] == '*') {
			j++;
		}

// Final Check 
		if (j == m) {
			return true;
		}

		return false;
	}

}
