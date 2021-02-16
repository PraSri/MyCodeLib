package Important;

import java.util.Stack;

public class NextElementTypeProblem implements NextGreaterElements {

	public static void main(String[] args) {

		NextElementTypeProblem nextElementTypeProblem = new NextElementTypeProblem();
		System.out.println("HELLO");
		int[] a = nextElementTypeProblem.getNextElementInArrayRightOfEachElement(new int[] { 11, 13, 21, 3, 4, 2 });
		System.out.println("HELLO");
		for (int x : a) {
			System.out.println(x);
		}

	}

	@Override
	public int[] getNextElementInArrayRightOfEachElement(int[] a) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(a[0]);
		int n = a.length, k = 0;
		int[] ans = new int[n];

		for (int i = 1; i < n; i++) {

			// while stack is not empty and top of stack is less than curr element keep
			// printing
			while (!s.empty() && s.peek() < a[i]) {
				ans[k++] = a[i];
				s.pop();
			}

			// push the current element
			s.push(a[i]);

		}

		// if no greater element then print -1

		while (!s.empty()) {
			ans[k++]=-1;
			s.pop();
		}

		return ans;
	}


	@Override
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		return null;
	}

	@Override
	public int[] nextGreaterElements(int[] nums) {
		return null;
	}

	@Override
	public int nextGreaterElement(int n) {
		return 0;
	}

	@Override
	public int[] dailyTemperatures(int[] T) {
		return null;
	}

	@Override
	public void nextPermutation(int[] nums) {

	}

}
