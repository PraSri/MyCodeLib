package ArrayAndMath;

public class PowerfulArray {

	public static void main(String[] args) {

	}

	// having issue with large inputs...why this approach is not working??

	public int solve(int[] A, int[] B) {

		long mod = 1000000001;
		int n = A.length;
		for (int i = 0; i < n; i++) {
			A[i] = A[i] - B[i];
		}
		long sumn = 0;
		long sump = 0;
		for (int i = 0; i < n; i++) {
			if (A[i] < 0) {
				A[i] = A[i] * (-1);
				sumn = (sumn % mod + A[i] % mod) % mod;
			} else {
				sump = (sump % mod + A[i] % mod) % mod;
			}
		}

		if (sumn > sump) {
			return 0;
		}

		return 1;

	}

	/**
	 * loop through the array and sum up the log of elements.
	 * 
	 * log(A1*A2*A3..) = logA1+logA2+logA3...
	 * 
	 * Do this for both the arrays and compare the result.
	 * 
	 */
	public int solve_v2(int[] A, int[] B) {

		double sumA = 0;
		double sumB = 0;
		for (int i = 0; i < A.length; i++)
			sumA += Math.log(A[i]);
		for (int i = 0; i < B.length; i++)
			sumB += Math.log(B[i]);

		if (sumA < sumB) {
			return 0;
		}

		return 1;

	}

}
