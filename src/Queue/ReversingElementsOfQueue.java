package Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReversingElementsOfQueue {

	public static void main(String[] args) {

		for (int i : solve_v2(new int[] { 1, 2, 3, 4, 5 }, 3))

			System.out.print(i + " ");

	}

	public static int[] solve(int[] A, int B) {

		Stack<Integer> st = new Stack<>();

		Queue<Integer> q = new LinkedList<>();

		for (Integer i : A) {
			q.add(i);
		}
		int k = 0;
		while (!q.isEmpty() && k < B) {
			st.push(q.poll());
			k++;
		}

		while (!st.isEmpty()) {
			q.add(st.pop());
		}

		int n = A.length;

		for (int i = 0; i < n - B; i++) {
			q.add(q.peek());
			q.remove();
		}
		int j = 0;
		while (!q.isEmpty()) {
			A[j++] = q.poll();
		}

		return A;

	}

	public static int[] solve_v2(int[] A, int B) {

		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < B; i++)
			st.push(A[i]);
		for (int i = 0; i < B; i++) {
			A[i] = st.pop();
		}

		return A;

	}

}
