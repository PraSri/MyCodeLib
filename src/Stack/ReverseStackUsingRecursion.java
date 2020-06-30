package Stack;

import java.util.Stack;

public class ReverseStackUsingRecursion {

	public static void main(String[] args) {

//		solve(new int[] { 1, 5, 3, 2, 4 });
		for (int i : solve(new int[] { 1, 5, 3, 2, 4 }))
			System.out.print(i + " , ");

	}

	public static Stack<Integer> newS = new Stack<>();

	public static int[] solve(int[] A) {

		Stack<Integer> s = new Stack<>();
		for (int i : A) {
			s.push(i);
		}

		newS.clear();

		rev(s);

		int k = A.length-1;

		while (!newS.isEmpty()) {
			A[k--] = newS.pop();
		}

		return A;
	}

	public static void rev(Stack<Integer> old) {
		if (old.isEmpty()) {
			return;
		}
		newS.push(old.pop());
		rev(old);
	}

}
