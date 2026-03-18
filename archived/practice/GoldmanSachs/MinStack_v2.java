package GoldmanSachs;

import java.util.Stack;

public class MinStack_v2 {

	public static void main(String[] args) {

	}

	Integer min = Integer.MAX_VALUE;
	Stack<Integer> s = new Stack<>();

	public void push(int x) {
		if (s.empty()) {
			s.push(x);
			min = x;
		} else {
			if (x >= min) {
				s.push(x);
			} else {
				long l = (long) (2 * x) - min;
				s.push((int) l);
				min = x;
			}
		}
	}

	public void pop() {
		if (s.empty())
			return;
		int y = s.pop();
		if (y < min) {
			long l = (long) (2 * min) - y;
			min = (int) l;
		}
	}

	public int top() {
		if (s.empty()) {
			return -1;
		}
		if(s.peek() < min) {
			return min;
		}
		return s.peek();

	}

	public int getMin() {
		if (s.empty() || min == Integer.MAX_VALUE)
			return -1;
		return min;
	}

}
