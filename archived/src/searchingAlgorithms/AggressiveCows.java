package searchingAlgorithms;

import java.util.Arrays;

public class AggressiveCows {

	public static void main(String[] args) {

		int[] a = new int[] { 0, 10 };
		int b = 2;
		System.out.println(solve(a, b));

	}

	public static int solve(int[] A, int B) {
		int n = A.length;
		Arrays.sort(A);
		int s = 0, e = A[n - 1], mid, max = Integer.MIN_VALUE;
		while (s < e) {
			mid = s + (e - s) / 2;
//			mid = (s + e) / 2;
			mid = (int)Math.floor((e-s)/2) + s;
			System.out.print(mid + " ");
			if (isPossible(A, B, mid)) {
				if (mid > max) {
					max = mid;
				}
				s = mid + 1;
			} else {
				e = mid;
			}
		}
		return max;
	}

	public static boolean isPossible(int[] a, int cows, int mid) {
		int c = 1, pos = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] - pos >= mid) {
				pos = a[i];
				c++;
				if (c == cows) {
					return true;
				}
			}
		}
		return false;
	}

}
