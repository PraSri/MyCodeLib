package GoldmanSachs;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

	/*****
	 * You are given an array of integers nums, there is a sliding window of size k
	 * which is moving from the very left of the array to the very right. You can
	 * only see the k numbers in the window. Each time the sliding window moves
	 * right by one position.
	 * 
	 * Return the max sliding window.
	 ********/

	public int[] maxSlidingWindow(int[] a, int k) {
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n - k + 1];
		int ri = 0;

		// store index
		Deque<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < a.length; i++) {

			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}

			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}

			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
	}

}
