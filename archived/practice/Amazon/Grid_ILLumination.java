package Amazon;

import java.util.HashMap;
import java.util.Map;

import QuickHelper.DirectionsInMatrix;

public class Grid_ILLumination {

	public static void main(String[] args) {

		Grid_ILLumination g = new Grid_ILLumination();

		int[][] lamps = { { 0, 0 }, { 0, 4 } };
		int[][] queries = { { 0, 4 }, { 0, 1 }, { 1, 4 } };

		int n = 5;

		for (int x : g.gridIllumination(n, lamps, queries)) {
			System.out.print(x + ", ");
		}

	}

	int[][] dirs = DirectionsInMatrix.DIRECTIONS_8_ADJACENTS;

//	https://leetcode.com/problems/grid-illumination/

	public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {

		// key : row number value : count of lamps
		Map<Integer, Integer> m1 = new HashMap<Integer, Integer>();

		// key : column number value : count of lamps
		Map<Integer, Integer> m2 = new HashMap<Integer, Integer>();

		// key : diagonal top right to bottom left (x-y) value : count of lamps
		Map<Integer, Integer> m3 = new HashMap<Integer, Integer>();

		// key : diagonal top left to bottom right (x+y) value : count of lamps
		Map<Integer, Integer> m4 = new HashMap<Integer, Integer>();

		// key : lamp identifier value : lamp is ON/OFF
		Map<String, Boolean> m5 = new HashMap<String, Boolean>();

		// increment counters while adding lamps
		for (int[] l : lamps) {
			int x = l[0], y = l[1];
			String id = new StringBuilder().append(x).append(y).toString();
			m1.put(x, m1.getOrDefault(x, 0) + 1);
			m2.put(y, m2.getOrDefault(y, 0) + 1);
			m3.put(x - y, m3.getOrDefault(x - y, 0) + 1);
			m4.put(x + y, m4.getOrDefault(x + y, 0) + 1);
			m5.put(id, true);
		}

		int[] ans = new int[queries.length];

		int i = 0;

		// address queries

		for (int[] q : queries) {

			int x = q[0];
			int y = q[1];

			if (m1.getOrDefault(x, 0) > 0 || m2.getOrDefault(y, 0) > 0 || m3.getOrDefault(x - y, 0) > 0
					|| m4.getOrDefault(x + y, 0) > 0) {

				ans[i++] = 1;

			} else {
				ans[i++] = 0;
			}

			// switch off the lamps if any in adjacent directions

			for (int[] d : dirs) {

				int x1 = x + d[0];
				int y1 = y + d[1];
				String id = new StringBuilder().append(x1).append(y1).toString();

				if (m5.getOrDefault(id, false)) {
					// lamp is on , turn OFF , decrement the count
					m1.put(x1, m1.getOrDefault(x1, 1) - 1);
					m2.put(y1, m2.getOrDefault(y1, 1) - 1);
					m3.put(x1 - y1, m3.getOrDefault(x1 - y1, 1) - 1);
					m4.put(x1 + y1, m4.getOrDefault(x1 + y1, 1) - 1);
					m5.put(id, false);
				}

			}

		}

		return ans;

	}
}
