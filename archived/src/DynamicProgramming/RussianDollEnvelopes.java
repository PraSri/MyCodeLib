package DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * A = [ [5, 4] [6, 4] [6, 7] [2, 3] ]
	 * 
	 * A = [[2, 3] [5, 4] [6, 7] [6, 4] ]
	 * 
	 * 3,4,7,4
	 * 
	 */

	public int solve(int[][] A) {

		Comparator<int[]> comparator = new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {

				if (o1[0] == o2[0]) {
					return o2[1] - o1[1];
				}

				return o1[0] - o2[0];
			}
		};

		Arrays.sort(A, comparator);

		int[] dp = new int[A.length];
		int l = 0;
		for (int[] a : A) {
			int i = Arrays.binarySearch(dp, 0, l, a[1]);
			if (i < 0)
				i = -(i + 1);
			dp[i] = a[1];
			if (i == l) {
				l++;
			}
		}

		return l;
	}

}
