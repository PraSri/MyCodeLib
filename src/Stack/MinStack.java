package Stack;

import java.util.Stack;

public class MinStack {

	public static void main(String[] args) {

	}

	Integer min = Integer.MAX_VALUE;
	Stack<Integer> s = new Stack<>();

	public void push(int x) {
		if (x <= min) {
			s.push(min);
			min = x;
		}
		s.push(x);
	}

	public void pop() {
		if (!s.empty() && s.pop() == min) {
			min = s.pop();
		}
	}

	public int top() {
		if (s.empty()) {
			return -1;
		}
		return s.peek();

	}

	public int getMin() {
		if (min == Integer.MAX_VALUE)
			return -1;
		return min;
	}

}
