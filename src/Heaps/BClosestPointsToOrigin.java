package Heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BClosestPointsToOrigin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static class Pair {
		int x, y;
		Long d;

		public Pair(int x, int y, Long d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

	}

	public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A, int B) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		Comparator<Pair> comparator = new Comparator<BClosestPointsToOrigin.Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.d.compareTo(o2.d);
			}
		};
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(comparator);

		for (ArrayList<Integer> a : A) {
			int x = a.get(0);
			int y = a.get(1);

			Long d = (long) x * x + (long) y * y;

			Pair p = new Pair(x, y, d);

			minHeap.add(p);
		}

		while (B > 0) {

			Pair p = minHeap.poll();
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(p.x);
			l.add(p.y);
			res.add(l);
			B--;
		}

		return res;
	}

}
