package Heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class ProductOf3 {

	public static void main(String[] args) {

		int[] A = new int[] {10, 2, 13, 4 };

		for (int i : solve(A)) {
			System.out.print(i + " "); // -1 -1 6 24 60
		}

	}

	public static int[] solve(int[] A) {

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

		for (int i = 0; i < A.length; i++) {
			minHeap.add(A[i]);// 1 2 3
			maxHeap.add(A[i]);// 3 2 1
			if (i > 1) {
				int x1 = maxHeap.poll(); // 3
				int x2 = maxHeap.poll();// 2
				int x3 = maxHeap.poll();// 1
				maxHeap.add(x1);
				maxHeap.add(x2);
				maxHeap.add(x3);
				int p1 = x1 * x2 * x3;
				int y1 = minHeap.poll();// 1
				int y2 = minHeap.poll();// 2
				minHeap.add(y1);
				minHeap.add(y2);
				int p2 = x1 * y1 * y2;
				A[i] = Math.max(p1, p2);
			} else {
				A[i] = -1;
			}
		}

//		int i = 0;
//		while (i < A.length) {
//			if (i < 2) {
//				A[i++] = -1;
//				continue;
//			}
//
//			i++;
//		}

		return A;

	}

}
