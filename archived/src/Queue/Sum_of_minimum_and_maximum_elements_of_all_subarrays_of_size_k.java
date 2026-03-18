package Queue;

import java.util.Deque;
import java.util.LinkedList;

public class Sum_of_minimum_and_maximum_elements_of_all_subarrays_of_size_k {

	public static void main(String[] args) {

		System.out.println(solve(new int[] { 2, 5, -1, 7, -3, -1, -2 }, 4));
	}

	public static int solve(int[] A, int B) {

		Deque<Integer> g = new LinkedList<>(); // first max
		Deque<Integer> s = new LinkedList<>(); // first min

		int i;

		/*
		 * A[] = {2, 5, -1, 7, -3, -1, -2} B = 4
		 * 
		 * i = 0 g : 0 s : 0 i = 1 g : 1 s : 0 1 i = 2 g : 1 2 s : 2 i = 3 g : 3 s : 2 3
		 * 2,5-1,7 max = 7 at 3 min = -1 at 2
		 * 
		 */

		for (i = 0; i < B; i++) {
			// remove from previous smaller elements useless
			while (!g.isEmpty() && A[g.peekLast()] <= A[i]) {

				g.removeLast();

			}

			g.addLast(i);

			// remove from previous greater elements

			while (!s.isEmpty() && A[s.peekLast()] >= A[i]) {
				s.removeLast();
			}

			s.addLast(i);
		}

		int sum = 0;

		for (; i < A.length; i++) {

			sum = sum + A[g.peekFirst()] + A[s.peekFirst()];

			// remove elements which are not the part of window

			while (!g.isEmpty() && g.peekFirst() <= i - B) {

				g.removeFirst();

			}

			while (!s.isEmpty() && s.peekFirst() <= i - B) {

				s.removeFirst();
			}

			// remove elements which are part of window but useless

			while (!g.isEmpty() && A[g.peekLast()] <= A[i]) {

				g.removeLast();

			}

			while (!s.isEmpty() && A[s.peekLast()] >= A[i]) {
				s.removeLast();
			}

			// add current element in deque

			g.addLast(i);
			s.addLast(i);

		}

		sum = sum + A[g.peekFirst()] + A[s.peekFirst()];

		return sum;

	}

	public static class Solution {
		public static int solve(int[] A, int B) {

			Deque<Integer> g = new LinkedList<>(); // first max
			Deque<Integer> s = new LinkedList<>(); // first min

			int i;

			long mod = 1000000007;

			/*
			 * A[] = {2, 5, -1, 7, -3, -1, -2} B = 4
			 * 
			 * i = 0 g : 0 s : 0 i = 1 g : 1 s : 0 1 i = 2 g : 1 2 s : 2 i = 3 g : 3 s : 2 3
			 * 2,5-1,7 max = 7 at 3 min = -1 at 2
			 * 
			 */

			for (i = 0; i < B; i++) {
				// remove from previous smaller elements useless
				while (!g.isEmpty() && A[g.peekLast()] <= A[i]) {

					g.removeLast();

				}

				g.addLast(i);

				// remove from previous greater elements

				while (!s.isEmpty() && A[s.peekLast()] >= A[i]) {
					s.removeLast();
				}

				s.addLast(i);
			}

			long sum = 0;

			for (; i < A.length; i++) {

				sum = (sum % mod + A[g.peekFirst()] % mod + A[s.peekFirst()] % mod) % mod;

				// remove elements which are not the part of window

				while (!g.isEmpty() && g.peekFirst() <= i - B) {

					g.removeFirst();

				}

				while (!s.isEmpty() && s.peekFirst() <= i - B) {

					s.removeFirst();
				}

				// remove elements which are part of window but useless

				while (!g.isEmpty() && A[g.peekLast()] <= A[i]) {

					g.removeLast();

				}

				while (!s.isEmpty() && A[s.peekLast()] >= A[i]) {
					s.removeLast();
				}

				// add current element in deque

				g.addLast(i);
				s.addLast(i);

			}

			sum = (sum % mod + A[g.peekFirst()] % mod + A[s.peekFirst()] % mod) % mod;
			if (sum < 0) {
				sum = sum + mod;
			}

			return (int) (sum);

		}
	}

}
