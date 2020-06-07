package Hashing;

import java.util.HashMap;
import java.util.Map;

public class PointsOnSameLine {

	public static void main(String[] args) {

		/*
		 * A : [ 3, 1, 4, 5, 7, -9, -8, 6 ] B : [ 4, -8, -3, -2, -1, 5, 7, -4 ]
		 * 
		 * 
		 */

		int[] A = new int[] { 0, 1, 2, 3 };
		int[] B = new int[] { 0, 1, 2, 3 };

		System.out.println(solve(A, B));

	}

	public static int solve(int[] A, int[] B) {

		int n = A.length;
		int ans = 0, curmax = 0;

		Map<String, Integer> hm;

		for (int i = 0; i < n; i++) {

			int ol = 0, v = 0;
			curmax = 0;
			hm = new HashMap<>();

			for (int j = i + 1; j < n; j++) {
				int dx = A[j] - A[i];
				int dy = B[j] - B[i];
				if (dx == 0 && dy == 0) {
					ol++;
					continue;
				}
				int gcd = gcd(dx, dy);

				int x = dx / gcd;
				int y = dy / gcd;

				String m = x + "," + y;

				if (!hm.containsKey(m)) {
					hm.put(m, 1);
				} else {
					hm.put(m, hm.get(m) + 1);
				}

				curmax = Math.max(curmax, hm.get(m));
			}

			ans = Math.max(ans, curmax + ol + 1);

		}

		return ans;

	}

	private static int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}

}
