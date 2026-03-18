package Stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EvaluateExpression {

	public static void main(String[] args) {

		System.out.println(evalRPN(new String[] { "5", "1", "2", "+", "4", "*", "+", "3", "-" }));

	}

	/*
	 * 
	 * A = ["2", "1", "+", "3", "*"]
	 * 
	 * * 3 + 1 2
	 * 
	 * 3*1+2
	 * 
	 * 
	 */

	public static int evalRPN(String[] A) {

		Set<String> operators = new HashSet<>();
		operators.addAll(Arrays.asList("+", "-", "*", "/"));

		Stack<Integer> st = new Stack<>();

		for (String s : A) {
			if (operators.contains(s)) {
				// 2 pops and evaluate and then push back

				int x = st.pop();
				int y = st.pop();
				int exp = 0;
				if (s.equals("+")) {
					exp = x + y;
				} else if (s.equals("-")) {
					exp = y - x;
				} else if (s.equals("*")) {
					exp = x * y;
				} else {
					exp = y / x;
				}

				st.push(exp);

			} else {
				st.push(Integer.parseInt(s));
			}
		}

		return st.peek();
	}

}
