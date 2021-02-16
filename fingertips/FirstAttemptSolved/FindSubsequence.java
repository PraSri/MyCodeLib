package FirstAttemptSolved;

public class FindSubsequence {

	public static void main(String[] args) {

		System.out.println(solve("apple", "appel"));
		System.out.println(solve("bit", "dfbkjijgbbiihbmmt"));

	}

	/****
	 * 
	 * Given two strings A and B, find if A is a subsequence of B. Return "YES" if A
	 * is a subsequence of B, else return "NO".
	 * 
	 ******/

	/**
	 * Input 1: A = "bit" B = "dfbkjijgbbiihbmmt" Output 1: YES
	 * 
	 * Input 2: A = "apple" B = "appel" Output 2: "NO"
	 **/

	public static String solve(String A, String B) {
		int n = A.length();
		int m = B.length();

		int i = 0;
		for (int j = 0; j < m; j++) {
			if (B.charAt(j) == A.charAt(i)) {
				i++;
			}
			if (i == n) {
				return "YES";
			}
		}

		return "NO";

	}

}
