package Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BthSmallestPrimeFraction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class Solution {
		public int[] solve(int[] A, int k) {
			int n = A.length;
			Comparator<int[]> comparator = new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[] b) {
					return (A[a[0]] * A[b[1]]) - (A[b[0]] * A[a[1]]);
				}
			};

			PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(comparator);

			for (int i = 0; i < n; i++) {
				minHeap.add(new int[] { i, n - 1 });
				if (i == k - 1) {
					break;
				}
			}

			while (!minHeap.isEmpty()) {

				if (--k == 0) {
					break;
				}

				int[] top = minHeap.poll();

				top[1]--;

				if (top[1] > top[0]) {
					minHeap.add(top);
				}

			}

			return new int[] { A[minHeap.peek()[0]], A[minHeap.peek()[1]] };
		}
	}

}
