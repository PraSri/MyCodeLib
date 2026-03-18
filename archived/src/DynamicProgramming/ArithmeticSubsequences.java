package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class ArithmeticSubsequences {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solve(int[] A) {

		int n = A.length;

		Map<Integer, Integer>[] dp = new HashMap[n];

		int res = 0;

		for (int i = 0; i < n; i++) {

			dp[i] = new HashMap<Integer, Integer>();

			for (int j = 0; j < i; j++) {
				long diff = (long) A[i] - A[j];
				if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE)
					continue;
				int d = (int) diff;
				int c1 = dp[i].getOrDefault(d, 0);
				int c2 = dp[j].getOrDefault(d, 0);
				res += c2;
				dp[i].put(d, 1 + c1 + c2);
			}
		}

		return res;

	}

}
