package Queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FirstNegativeIntegerInWindowSizeB {

	public static void main(String[] args) {

		int[] a = solve(new int[] { -1, 2, 3, -4, 5 }, 2);

		for (int i : a) {
			System.out.print(i + " ");
		}

	}

	public static int[] solve(int[] A, int B) {

		int n = A.length;
		ArrayList<Integer> ans = new ArrayList<>();
		Deque<Integer> d = new LinkedList<>();
		int i;
		for (i = 0; i < B; i++) {
			if (A[i] < 0)
				d.addLast(i);
		}

		for (; i < n; i++) {
			if (!d.isEmpty()) {
				ans.add(A[d.peek()]);
			} else {
				ans.add(0);
			}
			while (!d.isEmpty() && d.peek() < i - B + 1) {
				d.remove();
			}
			if (A[i] < 0) {
				d.add(i);
			}
		}
		
		if (!d.isEmpty()) {
			ans.add(A[d.peek()]);
		} else {
			ans.add(0);
		}

		int[] a = new int[ans.size()];

		int k = 0;

		for (int j : ans) {
			a[k++] = j;
		}

		return a;

	}

}
