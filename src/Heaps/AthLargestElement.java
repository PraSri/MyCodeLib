package Heaps;

import java.util.PriorityQueue;

public class AthLargestElement {

	public static void main(String[] args) {

		AthLargestElement at = new AthLargestElement();
		int[] a = at.solve_v2(2, new int[] { 15, 20, 99, 1 });
		for (int i : a) {
			System.out.print(i + " ");
		}

	}

//	public int[] solve(int A, int[] B) {
//
//		int k = A;
//
//		int n = B.length;
//		int[] a = B;
//
//		int[] b = new int[n];
//
//		if (k > n) {
//			return b;
//		}
//
//		for (int i = 0; i < k; i++) {
//			b[i] = -1;
//		}
//
//		if (k == n) {
//			return b;
//		}
//
//		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//
//		int i;
//
//		for (i = 0; i < k; i++) {
//			minHeap.add(a[i]);
//		}
//		i--;
//		while (!minHeap.isEmpty() && i < n) {
//			int x = minHeap.peek();
//			b[i] = x;
//			if (x < a[i]) {
//				minHeap.poll();
//				minHeap.add(a[i]);
//			}
//			i++;
//		}
//
//		return b;
//
//	}

	public int[] solve_v2(int A, int[] B) {
		int[] res = new int[B.length];

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		int n = A;

		int i = 0;

		for (; i < n; i++) {

			pq.add(B[i]);

			if (i < n - 1)

				res[i] = -1;

			else

				res[i] = pq.peek();

		}

		while (i < B.length) {

			if (pq.peek() < B[i]) {

				pq.poll();

				pq.add(B[i]);

				res[i] = pq.peek();

			}

			else {

				res[i] = pq.peek();

			}

			i++;

		}

		return res;

	}

}
