package DynamicProgramming;

import java.util.Stack;

public class LongestBalancedSubstring {
	public static void main(String[] args) {
		// ))(}[[[[)[(({))((()]]{][[[][{]]][)[]}]])])]}}[]](]())[[}[]}){[[)[][)(]{)]))[[[]{][([[
		System.out.println(new LongestBalancedSubstring().LBSlength_v3("({))((()"));
	}

	// https://leetcode.com/submissions/detail/381660880/
	// this approach is applicable only when one type of braces is given here '()'
	public int LBSlength(final String A) {

		int left = 0, right = 0;

		int n = A.length();

		int ml = 0;

		for (int i = 0; i < n; i++) {
			char c = A.charAt(i);
			if (c == '(')
				left++;
			else
				right++;
			if (left == right)
				ml = Math.max(ml, 2 * right);
			else if (right > left) {
				left = 0;
				right = 0;
			}

		}

		left = 0;
		right = 0;

		for (int i = n - 1; i >= 0; i--) {
			char c = A.charAt(i);
			if (c == '(')
				left++;
			else
				right++;
			if (left == right)
				ml = Math.max(ml, 2 * left);
			else if (right < left) {
				left = 0;
				right = 0;
			}

		}

		return ml;

	}

	// this is a wrong approch ..getting wrong answer
	public int LBSlength_v2(final String A) {

		int n = A.length();

		int ml = 0;

		Stack<Character> s = new Stack<Character>();
		/*
		 * 
		 * 
		 * ( { ) ) ( ( ( ) 0 0
		 * 
		 *
		 */

		int[] lbs = new int[n + 1];

		lbs[0] = 0;

		for (int i = 1; i <= n; i++) {

			char c = A.charAt(i - 1);

			if (c == '[' || c == '{' || c == '(') {
				s.add(c);
				lbs[i] = lbs[i - 1];
			}

			else if (c == ']' || c == '}' || c == ')') {
				if (!s.empty()) {

					if (c == ']') {

						char top = s.peek();
						if (top == '[') {
							lbs[i] = lbs[i - 1] + 2;
						}

						s.pop();

					} else if (c == '}') {
						char top = s.peek();
						if (top == '{') {
							lbs[i] = lbs[i - 1] + 2;
						}

						s.pop();

					} else if (c == ')') {

						char top = s.peek();
						if (top == '(') {
							lbs[i] = lbs[i - 1] + 2;
						}

						s.pop();

					} else {
						lbs[i] = 0;
					}

				} else {
					lbs[i] = 0;
				}
			}

		}

		for (int i = 0; i <= n; i++) {
			ml = Math.max(ml, lbs[i]);
		}

		return ml;

	}

	// this is the right approach ..all tc passing
	public int LBSlength_v3(final String A) {
		int n = A.length();

		int ml = 0;

		Stack<Integer> s = new Stack<Integer>();
		s.add(-1);

		for (int i = 0; i < n; i++) {
			char c = A.charAt(i);

			if (c == '[' || c == '{' || c == '(') {
				s.add(i);
			} else {
				if (!s.empty() && s.peek() != -1 && ((c == ']' && A.charAt(s.peek()) == '[')
						|| (c == '}' && A.charAt(s.peek()) == '{') || (c == ')' && A.charAt(s.peek()) == '('))) {
					s.pop();
					ml = Math.max(ml, i - s.peek());
				} else {
					s.add(i);
				}
			}

		}

		return ml;

	}

	// TODO : do it in DP
}
