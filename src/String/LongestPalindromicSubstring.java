package TestPackage;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		System.out.println(solve("forgeeksskeegfor").equals(solveSpaceOptimized("forgeeksskeegfor")));
	}

	/**
	 * 
	 * 
	 * Given a string A of size N, find and return the longest palindromic substring
	 * in A.
	 * 
	 * Substring of string A is A[i...j] where 0 <= i <= j < len(A)
	 * 
	 * Palindrome string: A string which reads the same backwards. More formally, A
	 * is palindrome if reverse(A) = A.
	 * 
	 * Incase of conflict, return the substring which occurs first ( with the least
	 * starting index).
	 * 
	 * 
	 * 
	 */

	public static String solve(String A) {

		int n = A.length();

		int start = 0;

		boolean[][] dp = new boolean[n][n];

		// for length one every string is palindrome

		int maxLength = 1;

		// dp[i][i] will be true

		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
		}

		// for length = 2 need to check both char is equal

		for (int i = 0; i < n - 1; i++) {
			if (A.charAt(i) == A.charAt(i + 1)) {
				dp[i][i + 1] = true;
				start = i;
				maxLength = 2;
			}
		}

		// for length > 2 , considering sub string of length k

		for (int k = 3; k <= n; k++) {

			// fixing the starting index
			for (int i = 0; i < n - k + 1; i++) {

				// calculate ending index j with starting index = i and length = k

				int j = i + k - 1;

				// check that the substring from index i+1 to j-1 is palindrome or not

				if (dp[i + 1][j - 1] && A.charAt(i) == A.charAt(j)) {
					dp[i][j] = true;
					if (k > maxLength) {
						start = i;
						maxLength = k;
					}
				}

			}

		}

		return A.substring(start, start + maxLength);

	}

	public static String solveSpaceOptimized(String A) {

		int n = A.length();

		int start = 0;

		int end = 0;

		for (int i = 0; i < n; i++) {

			char c = A.charAt(i);

			int left = i;
			int right = i;

			while (left >= 0 && A.charAt(left) == c) {
				left--;
			}

			while (right < n && A.charAt(right) == c) {
				right++;
			}

			while (left >= 0 && right < n) {
				if (A.charAt(left) != A.charAt(right)) {
					break;
				}
				left--;
				right++;
			}

			left = left + 1;

			// palindrome substring will left + 1 and right -1

			if (end - start < right - left) {
				start = left;
				end = right;
			}

		}

		return A.substring(start, end);

	}

}
