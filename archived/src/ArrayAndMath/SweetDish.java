package ArrayAndMath;

public class SweetDish {

	public static void main(String[] args) {

	}

	public int solve(int[] A, int B, int C) {
		int n = A.length;
		boolean[] isPrime = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (isPrimeCheck(A[i])) {
				isPrime[i] = true;
			}
		}
		int count = 0;
		int size = (int) Math.pow(2, n);
		for (int i = 1; i < size; i++) {
			boolean prime = false;
			int sum = 0;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					sum += A[j];
					if (!prime && isPrime[j]) {
						prime = true;
					}
				}
			}
			if (checkSumExistInRange(sum, B, C) && prime) {
				count++;
			}
		}

		return count;

	}

	private boolean checkSumExistInRange(int sum, int l, int r) {
		if (sum >= l && sum <= r) {
			return true;
		}
		return false;
	}

	private boolean isPrimeCheck(int n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}

		for (int i = 5; i < n; i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

}
