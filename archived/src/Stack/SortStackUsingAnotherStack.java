package Stack;

import java.util.Stack;

public class SortStackUsingAnotherStack {

	public static void main(String[] args) {

		for (int i : solve(new int[] { 2, 5, 3, 6, 1 })) {
			System.out.print(i + "  ");
		}

	}

	public static int[] solve(int[] A) {
		Stack<Integer> a = new Stack<>();
		Stack<Integer> s = new Stack<>();
		for (Integer i : A) {
			a.push(i);
		}
		while (!a.isEmpty()) {
			Integer x = a.pop();
			while (!s.isEmpty() && s.peek() > x) {
				a.push(s.pop());
			}
			s.push(x);
		}
		int[] ans = new int[s.size()];
		int i = s.size()-1;
		while (!s.isEmpty()) {
			ans[i--] = s.pop();
		}
		return ans;
	}

}
