package Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class NGE {

	public static void main(String[] args) {

		int[] a = new int[] { 11, 13, 21, 3 };

		System.out.println(nge(a));

		a = new int[] { 4, 5, 2, 25 };

		System.out.println(nge(a));

		a = new int[] { 11, 13, 21, 3, 4, 2 };

		System.out.println(nge(a));

	}

	public static List<Integer> nge(int[] a) {
		List<Integer> res = new ArrayList<Integer>();

		Stack<Integer> stack = new Stack<Integer>();

		int n = a.length;

		// iterate in reverse order
		for (int i = n - 1; i >= 0; i--) {

			int curr = a[i];

			while (!stack.isEmpty() && stack.peek() <= curr) {
				stack.pop();
			}

			if (stack.empty()) {
				res.add(-1);
			} else {
				res.add(stack.peek());
			}

			stack.push(a[i]);

		}

		Collections.reverse(res);

		return res;
	}

}
