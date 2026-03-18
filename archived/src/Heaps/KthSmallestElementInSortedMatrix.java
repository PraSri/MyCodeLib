package Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestElementInSortedMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static class Tuple implements Comparator<Tuple> {
		int x;
		int y;
		int v;

		public Tuple(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public int compare(Tuple o1, Tuple o2) {
			return o1.v - o2.v;
		}

	}

	public int solve(int[][] A, int B) {

		int n = A.length;
		int m = A[0].length;
		PriorityQueue<Tuple> minHeap = new PriorityQueue<KthSmallestElementInSortedMatrix.Tuple>();
		for (int j = 0; j < m; j++) {
			minHeap.offer(new Tuple(0, j, A[0][j]));
		}

		for (int i = 0; i < B - 1; i++) {
			Tuple t = minHeap.poll();
			if (t.x == n - 1)
				continue;
			minHeap.offer(new Tuple(t.x + 1, t.y, A[t.x + 1][t.y]));
		}

		return minHeap.poll().v;

	}

}
