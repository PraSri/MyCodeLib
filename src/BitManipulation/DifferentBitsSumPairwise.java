package BitManipulation;

public class DifferentBitsSumPairwise {

	public static void main(String[] args) {

	}

	/****
	 * We define f(X, Y) as number of different corresponding bits in binary
	 * representation of X and Y. For example, f(2, 7) = 2, since binary
	 * representation of 2 and 7 are 010 and 111, respectively. The first and the
	 * third bit differ, so f(2, 7) = 2.
	 * 
	 * You are given an array of N positive integers, A1, A2 ,..., AN. Find sum of
	 * f(Ai, Aj) for all pairs (i, j) such that 1 ≤ i, j ≤ N. Return the answer
	 * modulo 109+7.
	 ***/
	public int cntBits(int[] A) {
		int n = A.length;
		long res = 0;
		for (int i = 0; i < 32; i++) {
			long x = 0;
			for (int j = 0; j < n; j++) {
				if (((A[j] >> i) & 1) == 1) {
					x++;
				}
			}
			res += 2 * ((x) * (n - x)) % 1000000007;// 10^9+7 = 1000000007
			res %= 1000000007;
		}
		return (int) res % 1000000007;
	}
}
