package Arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ChristmasTrees {

	/**
	 * 
	 * 
	 * You are given an array A consisting of heights of Christmas trees, and an
	 * array B of same size consisting of the cost of each of the trees (Bi is the
	 * cost of tree Ai, where 1 ≤ i ≤ size(A)), and you are supposed to choose 3
	 * trees (let's say, indices p, q and r), such that Ap < Aq < Ar, where p < q <
	 * r. The cost of these trees is Bp + Bq + Br.
	 * 
	 * You are to choose 3 such trees, so they have the minimum cost and find the
	 * minimum cost.
	 * 
	 * If not possible to choose 3 such trees, return -1.
	 * 
	 */

	public static void main(String[] args) {

		ChristmasTrees ct = new ChristmasTrees();
		int x = ct.solve(new int[] { 5, 9, 10, 4, 7, 8 }, new int[] { 5, 6, 4, 7, 2, 5 });

		System.out.println(x);

	}

	public class Tuple {
		public int index, height, cost;

		public Tuple(int index, int height, int cost) {
			this.index = index;
			this.height = height;
			this.cost = cost;
		}
	}

	// not a sucessful solution
	public int solve(int[] A, int[] B) {

		Comparator<Tuple> comparator = new Comparator<Tuple>() {

			@Override
			public int compare(Tuple t1, Tuple t2) {

				if (t1.cost > t2.cost) {
					return 1;
				} else {
					return -1;
				}
			}
		};

		int n = A.length;

		Tuple[] ta = new Tuple[n];

		for (int i = 0; i < n; i++) {

			Tuple t = new Tuple(i, A[i], B[i]);
			ta[i] = t;

		}

//		Arrays.sort(ta, comparator);

		for (int i = 0; i < n; i++) {
			System.out.println(ta[i].index + "-" + ta[i].height + "-" + ta[i].cost);
		}

//		int i = 0;
//		while (i < n) {
//			if (i >= n || i + 1 >= n || i + 2 >= n)
//				break;
//			if (isValid(ta[i], ta[i + 1], ta[i + 2])) {
//				return ta[i].cost + ta[i + 1].cost + ta[i + 2].cost;
//			}
//			i++;
//		}

		return getMaxTriplet(ta);
	}

	private int getMaxTriplet(Tuple[] ta) {

		int ans = Integer.MAX_VALUE;
		int n = ta.length;
		for (int i = 1; i < n - 1; i++) {
			int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (ta[j].height < ta[i].height) {
					if (ta[j].cost < min1) {
						min1 = ta[j].cost;
					}
				}
			}

			for (int k = i + 1; k < n; k++) {
				if (ta[k].height > ta[i].height) {
					if (ta[k].cost < min2) {
						min2 = ta[k].cost;
					}
				}
			}

			if (min1 == Integer.MAX_VALUE || min2 == Integer.MAX_VALUE)
				continue;
			else
				ans = Math.min(ans, min1 + min2 + ta[i].cost);

		}

		if (ans == Integer.MAX_VALUE)
			return -1;
		return ans;

	}

	private boolean isValid(Tuple t1, Tuple t2, Tuple t3) {

		int[] in = new int[3];
		in[0] = t1.index;
		in[1] = t2.index;
		in[2] = t3.index;

		int max = Math.max(in[0], Math.max(in[1], in[2]));
		int[] h = new int[max + 1];

		h[in[0]] = t1.height;
		h[in[1]] = t2.height;
		h[in[2]] = t3.height;

		Arrays.sort(in);
		if (h[in[0]] < h[in[1]] && h[in[1]] < h[in[2]]) {
			return true;
		}
		return false;
	}

	// sucessful solution
	public int solve_v2(int[] A, int[] B) {

		int resSum = Integer.MAX_VALUE;

		int n = A.length;

		for (int i = 1; i < n - 1; i++) {

			int indexP = -1;

			int minP = Integer.MAX_VALUE;

			for (int j = 0; j < i; j++) {

				if (A[j] < A[i]) {

					if (minP > B[j]) {

						indexP = j;

						minP = B[j];

					}

				}

				System.out.print(j + " ");

			}

			System.out.println();

			int indexR = -1;

			int minR = Integer.MAX_VALUE;

			for (int k = i + 1; k < n; k++) {

				if (A[k] > A[i]) {

					if (minR > B[k]) {

						indexR = k;

						minR = B[k];

					}

				}

				System.out.print(k + " ");

			}

			System.out.println();

			if (indexP == -1 || indexR == -1) {

				continue;

			} else {

				System.out.println("indexP: " + indexP + " indexQ: " + i + " indexR: " + indexR);

				resSum = Math.min(resSum, B[indexP] + B[i] + B[indexR]);

			}

		}

		if (resSum == Integer.MAX_VALUE) {

			return -1;

		}

		return resSum;

	}

}
