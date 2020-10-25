package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class WordBreakProblem {

	public static void main(String[] args) {

	}

	public int wordBreak(String A, String[] B) {

		Set<String> dict = new HashSet<String>();

		for (String s : B)
			dict.add(s);

		return workBreakHelper(A, dict);

	}

	private int workBreakHelper(String s, Set<String> dict) {
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

	public int wordBreak_v2(String A, String[] B) {

		Set<String> set = new HashSet();

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
