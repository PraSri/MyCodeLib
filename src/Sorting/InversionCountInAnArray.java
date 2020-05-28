package Sorting;

public class InversionCountInAnArray {

	public static void main(String[] args) {
		System.out.println(solve_v2(new int[] { 3, 2, 1 }));

	}

	static int mod = 1000000007;
	static int[] temp = new int[100000];

	public static int solve(int[] A) {
		int n = A.length;
		int ct = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (i < j && A[i] > A[j]) {
					ct = (ct % mod + 1 % mod) % mod;
				}
			}
		}
		return ct % mod;
	}

	/* More optimized solution */

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
			invCt += mergeSort(a, s, mid);
			invCt += mergeSort(a, mid + 1, e);
			invCt += merge(a, s, mid + 1, e);
		}

		return invCt % mod;
	}

	private static int merge(int[] a, int s, int mid, int e) {

		int i = s, j = mid, k = s;
		int ic = 0;

		while (i <= mid - 1 && j <= e) {
			if (a[i] <= a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
				ic = ic + (mid - i);
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
