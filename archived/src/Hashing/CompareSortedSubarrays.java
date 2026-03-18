package Hashing;

import java.util.HashSet;
import java.util.Set;

public class CompareSortedSubarrays {

	public static void main(String[] args) {
//		int i = 0, j = 0;
//		while (i < 5) {
//			while (j < 5) {
//				j++;
//				if (j == 1)
//					break;
//				System.out.println("nested");
//			}
//			i++;
//			System.out.println("HELLO");
//		}
//
//		System.out.println("Out");

		int[] A = new int[] { 100000, 100000, 100000, 100000, 100000 };
		int[][] B = new int[][] { { 0, 3, 1, 4 } };
		for (int i : solve_v2(A, B)) {
			System.out.print(i + " ");
		}

	}

	/* GETTING TLE */
	public static int[] solve(int[] A, int[][] B) {

		int q = B.length;
		int[] a = new int[q];
		int k = 0;
		Set<Integer> s = new HashSet<>();
		int f;
		for (int i = 0; i < q; i++) {
			int l1 = B[i][0];
			int r1 = B[i][1];
			int l2 = B[i][2];
			int r2 = B[i][3];
			f = 1;
			if (r1 - l1 != r2 - l2) {
				f = 0;
				a[k] = 0;
			} else {
				while (l1 <= r1) {
					s.add(A[l1]);
					l1++;
				}
				while (l2 <= r2) {
					if (!s.contains(A[l2])) {
						a[k] = 0;
						f = 0;
						break;
					}
					l2++;
				}
				if (f != 0) {
					a[k] = 1;
				}
			}

			k++;
			s.clear();
		}

		return a;

	}

	/* optimized n successful solution */
	public static int[] solve_v2(int[] A, int[][] B) {
		int n = A.length;
		int q = B.length;
		int k = 0;
		int[] a = new int[q];
		int[] ps = new int[n];
		ps[0] = A[0];
		if (n < 2) {
			a[0] = 1;
			return a;
		}
		for (int i = 1; i < n; i++) {
			ps[i] = ps[i - 1] + A[i];
		}
		for (int i = 0; i < q; i++) {
			int l1 = B[i][0];
			int r1 = B[i][1];
			int l2 = B[i][2];
			int r2 = B[i][3];
			if (!checkSumIsEqual(ps, l1, r1, l2, r2)) {
				a[k] = 0;
			} else {
				int x = 0;
				while (l1 <= r1) {
					x = x ^ A[l1];
					l1++;
				}
				while (l2 <= r2) {
					x = x ^ A[l2];
					l2++;
				}
				if (x == 0) {
					a[k] = 1;
				} else {
					a[k] = 0;
				}
			}
			k++;
		}

		return a;
	}

	private static boolean checkSumIsEqual(int[] ps, int l1, int r1, int l2, int r2) {
		int s1 = 0, s2 = 0;
		if (l1 == 0) {
			s1 = ps[r1];
		} else {
			s1 = ps[r1] - ps[l1 - 1];
		}
		if (l2 == 0) {
			s2 = ps[r2];
		} else {
			s2 = ps[r2] - ps[l2 - 1];
		}
		return s1 == s2;
	}
}
