package DynamicProgramming;

public class WaysToDecode {

	public static void main(String[] args) {
		System.out.println(numDecodings("8"));
	}
	
//	https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation/29468
	
	
	/*
	 * 
	 * 
	 * I wrote some notes for myself reference, hope it might help someone to
	 * understand this solution.
	 * 
	 * dp[i]: represents possible decode ways to the ith char(include i), whose
	 * index in string is i-1
	 * 
	 * Base case: dp[0] = 1 is just for creating base; dp[1], when there's one
	 * character, if it is not zero, it can only be 1 decode way. If it is 0, there
	 * will be no decode ways.
	 * 
	 * Here only need to look at at most two digits before i, cuz biggest valid code
	 * is 26, which has two digits.
	 * 
	 * For dp[i]: to avoid index out of boundry, extract substring of (i-1,i)- which
	 * is the ith char(index in String is i-1) and substring(i-2, i)
	 * 
	 * First check if substring (i-1,i) is 0 or not. If it is 0, skip it, continue
	 * right to check substring (i-2,i), cuz 0 can only be decode by being together
	 * with the char before 0.
	 * 
	 * Second, check if substring (i-2,i) falls in 10~26. If it does, means there
	 * are dp[i-2] more new decode ways.
	 * 
	 * Time: should be O(n), where n is the length of String Space: should be O(n),
	 * where n is the length of String
	 */

	public static int numDecodings(String A) {

		int n = A.length();
		int mod = 1000000007;
		if (A.charAt(0) == '0')
			return 0;
		if (n == 1) {
			return 1;
		}
		int[] dp = new int[n + 1];

		dp[0] = 1;
		dp[1] = (A.charAt(0) != '0') ? 1 : 0;

		for (int i = 2; i <= n; i++) {

			/*
			 * 123 i = 2 x = 2 dp[2] = dp[1] = 1 y = 12 dp[2] += dp[0] = 2;
			 * 
			 * 
			 */

			int x = Integer.parseInt(A.substring(i - 1, i));
			int y = Integer.parseInt(A.substring(i - 2, i));

			if (x >= 1 && x <= 9) {
				dp[i] += dp[i - 1] % mod;
			}

			if (y >= 10 && y <= 26) {
				dp[i] += dp[i - 2] % mod;
			}

		}

		return dp[n] % mod;

	}

}
