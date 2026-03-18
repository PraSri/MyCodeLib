package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NearestSmallerElement {

	public static void main(String[] args) {
		
		System.out.println(prevSmaller(new ArrayList<>(Arrays.asList(4, 5, 2, 10, 8))));
		System.out.println(prevSmaller(new ArrayList<>(Arrays.asList(3,2,1))));

	}

	public static ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {

		ArrayList<Integer> a = new ArrayList<>();

		Stack<Integer> s = new Stack<>();

		int n = A.size();

		for (int i = 0; i < n; i++) {

			while (!s.empty() && s.peek() >= A.get(i)) {
				s.pop();
			}

			if (s.empty())
				a.add(-1);
			else {
				a.add(s.peek());
			}

			s.push(A.get(i));
		}

		return a;

	}

}
