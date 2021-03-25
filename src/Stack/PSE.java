package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PSE {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 6, 4, 10, 2, 5 };
		System.out.println(pse(a));
	}

	public static List<Integer> pse(int[] a) {

		int n = a.length;

		Stack<Integer> stack = new Stack<Integer>();

		List<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {

			int curr = a[i];

//			 while stack is not empty & stack top is greater than current element ,
//			keep popping from stack
			while (!stack.isEmpty() && stack.peek() >= curr) {

				stack.pop();

			}

			// if stack is empty that means no smaller elements before current element
			if (stack.isEmpty()) {
				res.add(-1);
			} else {
				// if exist then add in list
				res.add(stack.peek());
			}

			// push it to stack
			stack.push(curr);

		}

		return res;

	}

}
