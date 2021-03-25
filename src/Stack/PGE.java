package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PGE {

	public static void main(String[] args) {

		int[] a = new int[] { 10, 4, 2, 20, 40, 12, 30 };
		System.out.println(pge(a));

	}

	public static List<Integer> pge(int[] a) {

		List<Integer> res = new ArrayList<Integer>();

		Stack<Integer> stack = new Stack<Integer>();

		int n = a.length;

		stack.push(a[0]);

		res.add(-1);

		for (int i = 1; i < n; i++) {

			int current = a[i];

			while (!stack.isEmpty() && stack.peek() < current) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				res.add(-1);
			} else {
				res.add(stack.peek());
			}

			stack.push(current);

		}

		return res;

	}

}
