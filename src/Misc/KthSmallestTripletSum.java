package Misc;

public class KthSmallestTripletSum {

	public static void main(String[] args) {

	}

	/*
	 * 
	 * NOT ABLE TO UNDERSTAND SOLUTION
	 * 
	 */

	public static int solve(int[] A, int B) {

		int n = A.length;
		int s = 1, e = A[n - 3] + A[n - 2] + A[n - 1];
		int sum = -1;
		int mid;
		while (s <= e) {
			mid = (s + e) / 2;
			if (isPossible(A, B, mid)) {
				sum = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return sum;

	}

	private static boolean isPossible(int[] a, int b, int mid) {
		int c = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int x = mid - a[i] - a[j];
				int k = j + 1;
				for (; k < a.length; k++) {
					if (a[k] >= x)
						break;
				}
				c = c + (k - 1) - j;
			}
		}

		if (c < b)
			return true;

		return false;
	}

}
