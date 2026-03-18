package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class WordBreakProblem {

	public static void main(String[] args) {

		String temp_dictionary[] = { "mobile", "samsung", "sam", "sung", "man", "mango", "icecream", "and", "go", "i",
				"like", "ice", "cream" };

		System.out.println(wordBreak_v2("ilikesamsung", temp_dictionary));
		System.out.println(wordBreak("iiiiiiii", temp_dictionary));
		System.out.println(workBreak_Recursion("", temp_dictionary));
		System.out.println(wordBreak("ilikelikeimangoiii", temp_dictionary));
		System.out.println(wordBreak("samsungandmango", temp_dictionary));
		System.out.println(workBreak_Recursion("samsungandmangok", temp_dictionary));

	}

	/***** Brute Force Approach *******/
	static Set<String> dict = new HashSet<String>();

	public static boolean workBreak_Recursion(String A, String[] B) {

		for (String s : B)
			dict.add(s);

		return recursion(A);

	}

	private static boolean recursion(String s) {
		int size = s.length();
		if (size == 0)
			return true;

		for (int i = 1; i <= size; i++) {

			// prefix : s.substring(0, i)
			// suffix : s.substring(i, size)

			if (dict.contains(s.substring(0, i)) && recursion(s.substring(i, size)))
				return true;

		}
		return false;
	}

	/********************/

	public static int wordBreak(String A, String[] B) {

		Set<String> dict = new HashSet<String>();

		for (String s : B)
			dict.add(s);

		return workBreakHelper(A, dict);

	}

	private static int workBreakHelper(String s, Set<String> dict) {
		int n = s.length();
		boolean[] f = new boolean[n + 1];

		f[0] = true;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (f[j] && dict.contains(s.substring(j, i))) {
					f[i] = true;
					break;
				}
			}
		}

		return f[n] ? 1 : 0;
	}

	public static int wordBreak_v2(String A, String[] B) {

		Set<String> set = new HashSet<String>();

		int m = A.length();

		boolean[] dp = new boolean[m + 1];

		dp[0] = true;

		if (B.length == 0)

			return 0;

		if (A == null || A.length() == 0)

			return 1;

		for (int i = 0; i < B.length; i++)

			set.add(B[i]);

		for (int len = 1; len <= m; len++)

		{

			for (int i = len; i >= 0; i--)

			{

				if (dp[i] && set.contains(A.substring(i, len)))

				{

					dp[len] = true;

					break;

				}

			}

		}

		// System.out.println(Arrays.toString(dp));

		int result = (dp[m] == true) ? 1 : 0;

		return result;

	}

}
