package TwoPointers;

public class CountOfPairsWithTheGivenSumII {

	public static void main(String[] args) {
		System.out.println(solve_v2(new int[] { 1, 1, 1 }, 2));
	}

	/*
	 * Given a sorted array of integers (not necessarily distinct) A and an integer
	 * B, find and return how many pair of integers ( A[i], A[j] ) such that i != j
	 * have sum equal to B.
	 */

	public static int mod = 1000000007;

	public static int solve(int[] A, int B) {

		int n = A.length;
		int c = 0;

		int i = 0;

		while (i < n) {
			int j = n - 1;
			while (j > i) {
				if (A[i] + A[j] == B) {
					c = (c % mod + 1) % mod;
				}
				j--;
			}
			i++;

		}

		return c % mod;

	}

	public static int solve_v2(int[] A, int B) {

		int n = A.length;
		int c = 0;

		int i = 0;

		int j = n - 1;
		while (i < j) {
			int sum = A[i] + A[j];
			if (sum == B) {
				int x = A[i], xi = i;
				while ((i < j) && A[i] == x) {
					i++;
				}
				int y = A[j], yj = j;
				while ((j >= i) && A[j] == y) {
					j--;
				}
				if (x == y) {
					int t = i - xi + yj - j - 1;
					long p = (t % mod * (t + 1) % mod) % mod;
					p = p / 2;
					c = (int) ((c % mod + p % mod) % mod);

				} else {
					long p = (i - xi) % mod * (yj - j) % mod;
					c = (int) ((c % mod + p % mod) % mod);
				}

			} else {
				if (sum > B) {
					j--;
				} else {
					i++;
				}
			}
		}

		return c % mod;

	}

}
