package Sorting;

import java.util.Arrays;

public class ReversePairs {

	static int mod = 1000000007;
	static int[] temp = new int[100000];

	public static void main(String[] args) {

		System.out.println(solve_v2(new int[] { 1, 3, 2, 3, 1 }));

	}

	public static int solve_v2(int[] A) {
		int n = A.length;

		int s = 0;
		int e = n - 1;

		return mergeSort(A, s, e);
	}

	private static int mergeSort(int[] a, int s, int e) {

		int invCt = 0;
		if (s < e) {

			int mid = (s + e) / 2;
			invCt += mergeSort(a, s, mid) + mergeSort(a, mid + 1, e);

			for (int i = s, j = mid + 1; i <= mid; i++) {
				while (j <= e && a[i] / 2.0 > a[j]) {
					j++;
				}
				invCt += (j - (mid + 1));
			}
			Arrays.sort(a, s, e + 1);
		}

		return invCt;
	}

	private static int merge(int[] a, int s, int mid, int e) {

		int i = s, j = mid, k = s;
		int ic = 0;

		while (i <= mid - 1 && j <= e) {
			if (a[i] <= a[j]) {
				temp[k++] = a[i++];
			} else {
				if (a[i] > 2 * a[j]) {
					System.out.println("i=" + i + " j=" + j);
					ic = ic + (j - (mid + 1));
				}
				temp[k++] = a[j++];
			}
		}
		while (i <= mid - 1) {
			temp[k++] = a[i++];
		}
		while (j <= e) {
			temp[k++] = a[j++];
		}
		for (int i1 = s; i1 <= e; i1++) {
			a[i1] = temp[i1];
		}

		return ic % mod;
	}

}
