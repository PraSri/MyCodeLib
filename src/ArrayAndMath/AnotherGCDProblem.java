package ArrayAndMath;

public class AnotherGCDProblem {

	/**
	 * Given an integer array A of size N. Find the maximum length of a subarray Al,
	 * Al+1 ... Ar such that gcd(A[l], A[l+1], ... A[r]) > 1.
	 * 
	 * NOTE: If no such subarray exists, return -1.
	 * 
	 */

	public static void main(String[] args) {

	}

	public int solve(int[] A) {

		int n = A.length;

//		int mv = Arrays.stream(A).max().getAsInt();

		int s = 2, e = n;
		;
		int ans = -1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (isExist(A, mid)) {
				ans = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return ans;

	}

	private boolean isExist(int[] a, int mid) {

		int g = 0;
		for (int i = 0; i < mid; i++) {
			for (int j = i; j < mid; j++) {
				g = gcd(g, a[j]);
			}
			if (g > 1) {
				return true;
			}
		}

		return false;
	}

	private int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}
