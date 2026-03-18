package Amazon;

import java.util.Arrays;
import java.util.List;

public class MaxDistance {

	public static void main(String[] args) {

		System.out.println(maximumGap(new int[] { 3, 5, 4, 2 }));
		System.out.println(maximumGap(Arrays.asList(3, 5, 4, 2)));

	}

	// Given an array A of integers, find the maximum of j - i subjected to the
	// constraint of A[i] <= A[j].

	public static int maximumGap(final int[] A) {

		// create an array for maxFromEnd
		int n = A.length;

		int[] maxFromEnd = new int[n + 1];

		Arrays.fill(A, Integer.MIN_VALUE);

		for (int i = n - 1; i >= 0; i--) {

			maxFromEnd[i] = Math.max(maxFromEnd[i + 1], A[i]);

		}

		// do binary for each element to find rightmost greater number

		int res = 0;

		for (int i = 0; i < n; i++) {

			int s = i + 1;
			int e = n - 1;
			int ans = -1;

			while (s <= e) {

				int mid = s + (e - s) / 2;

				if (A[i] <= maxFromEnd[mid]) {

					ans = Math.max(ans, mid);

					s = mid + 1;

				} else {

					e = mid - 1;

				}

			}
			res = Math.max(res, ans - i);
		}

		return res;

	}

	static class Pair implements Comparable<Pair> {
		int val, idx;

		Pair(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}

		public int compareTo(Pair p) {
			return this.val - p.val;
		}
	}

	public static int maximumGap(final List<Integer> A) {

		Pair P[] = new Pair[A.size()];

		for (int i = 0; i < A.size(); i++) {
			P[i] = new Pair(A.get(i), i);
		}

		Arrays.sort(P);

		int currMin = P[0].idx, res = 0;

		for (int i = 1; i < P.length; i++) {
			res = Math.max(res, P[i].idx - currMin);
			currMin = Math.min(currMin, P[i].idx);
		}

		return res;
	}

}
