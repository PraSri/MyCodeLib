package Stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RedundantBraces {

	public static void main(String[] args) {
		System.out.println(braces("(B+((a+b)))"));
	}

	public static int braces(String A) {

		Stack<Character> st = new Stack<>();

		Set<Character> set = new HashSet<>();

		set.addAll(Arrays.asList('+', '-', '*', '/'));

		int n = A.length();

		int i = 0;

		while (i < n) {

			char c = A.charAt(i);

			if (c == '(' || set.contains(c)) {
				st.push(c);
			} else if (c == ')') {
				char t = st.peek();
				if (t == '(') {
					return 1;
				} else {
					while (!st.empty() && st.peek() != '(') {
						st.pop();
					}
					st.pop();
				}
			}

			i++;

		}

		return 0;

	}

}
