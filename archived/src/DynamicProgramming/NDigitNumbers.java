package DynamicProgramming;

import java.util.Arrays;

public class NDigitNumbers {

	public static void main(String[] args) {

		NDigitNumbers n = new NDigitNumbers();

		System.out.println(n.solve(1, 3));
		System.out.println(n.solve(5, 3));

	}

	static int mod = 1000000007;

	public int solve(int A, int B) {

		int[][] lookup = new int[A + 1][B + 1];

		for (int[] a : lookup) {
			Arrays.fill(a, -1);
		}

		return finalcount(A, B, lookup);

	}

	private int finalcount(int n, int sum, int[][] lookup) {
		if (n == 0) {
			return sum == 0 ? 1 : 0;
		}
		if (lookup[n][sum] != -1)
			return lookup[n][sum];

		int ans = 0;

		for (int i = 1; i < 10; i++) {
			if (sum - i >= 0)
				ans = (ans % mod + count(n - 1, sum - i, lookup) % mod) % mod;
		}

		return lookup[n][sum] = ans;
	}

	private int count(int n, int sum, int[][] lookup) {

		if (n == 0) {
			return sum == 0 ? 1 : 0;
		}
		if (lookup[n][sum] != -1)
			return lookup[n][sum];

		int ans = 0;

		for (int i = 0; i < 10; i++) {
			if (sum - i >= 0)
				ans = (ans % mod + count(n - 1, sum - i, lookup) % mod) % mod;
		}

		return lookup[n][sum] = ans;
	}
}
