package Hashing;

import java.util.LinkedHashMap;
import java.util.Map;

public class Equal {

	public static void main(String[] args) {
		int[] d = equal(new int[] { 1, 1, 1, 1, 1 });
		for (int x : d)
			System.out.print(x + " ");

	}

	/*
	 * 1) Return the indices `A1 B1 C1 D1`, so that A[A1] + A[B1] = A[C1] + A[D1]
	 * A1< B1, C1 < D1 A1 < C1, B1 != D1, B1 != C1
	 * 
	 * 2) If there are more than one solutions, then return the tuple of values
	 * which are lexicographical smallest.
	 * 
	 * Assume we have two solutions S1 : A1 B1 C1 D1 ( these are values of indices
	 * in the array ) S2 : A2 B2 C2 D2
	 * 
	 * S1 is lexicographically smaller than S2 if: A1 < A2 OR A1 = A2 AND B1 < B2 OR
	 * A1 = A2 AND B1 = B2 AND C1 < C2 OR A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 <
	 * D2
	 * 
	 */
	public static class Pair {

		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

	public static int[] equal(int[] A) {

		int[] a = new int[4];

		Map<Integer, Pair> m = new LinkedHashMap<>();
		Pair p = new Pair(9999999, 99999999);
		Pair l = new Pair(9999999, 9999999);
		Pair p1 = null, p2 = null;
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				int sum = A[i] + A[j];
				if (!m.containsKey(sum)) {
					m.put(sum, new Pair(i, j));
				} else if (checkPossible(m.get(sum), i, j)) {
					p1 = m.get(sum);
					p2 = new Pair(i, j);
					if (p1.first < p.first || (p1.first == p.first && p1.second < p.second)
							|| (p1.first == p.first && p1.second == p.second && p2.first < l.first)
							|| (p1.first == p.first && p1.second == p.second && p2.first == l.first
									&& p2.second < l.second)) {
						p = p1;
						l = p2;
					}
				}
			}
		}

		a[0] = p.first;
		a[1] = p.second;
		a[2] = l.first;
		a[3] = l.second;

		return a;

	}

	private static boolean checkPossible(Pair pair, int i, int j) {
		int a = pair.first;
		int b = pair.second;
		int c = i;
		int d = j;
		if (a < b && c < d && a < c && b != d && b != c) {
			return true;
		}
		return false;
	}

}
