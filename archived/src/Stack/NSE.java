package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NSE {

	public static void main(String[] args) {

		int[] a = new int[] { 11, 13, 21, 3 };

		System.out.println(nse(a));

	}

	public static List<Integer> nse(int[] a) {
		List<Integer> res = new ArrayList<Integer>();

		Stack<Integer> stack = new Stack<Integer>();

		int n = a.length;

		stack.push(a[0]);

		for (int i = 1; i < n; i++) {
			int curr = a[i];

			if (stack.empty()) {
				stack.push(curr);
				continue;
			}

			while (!stack.isEmpty() && stack.peek() > curr) {
				res.add(curr);
				stack.pop();
			}

			stack.push(curr);
		}

		while (!stack.isEmpty()) {
			res.add(-1);
			stack.pop();
		}

		return res;

	}

}
