package Important;

import java.util.Stack;

public class NextElementTypeProblem implements NextGreaterElements {

	public static void main(String[] args) {

		NextElementTypeProblem nextElementTypeProblem = new NextElementTypeProblem();
		int[] a = nextElementTypeProblem.getNextElementInArrayRightOfEachElement(new int[] { 11, 13, 21, 3 });
		for (int x : a) {
			System.out.println(x);
		}

	}

	@Override
	public int[] getNextElementInArrayRightOfEachElement(int[] a) {
		Stack<Integer> s = new Stack<Integer>();

		s.push(a[0]);
		int n = a.length;
		int next, k = 0, x;
		int[] ans = new int[n];
		for (int i = 1; i < n; i++) {

			next = a[i];

			if (!s.empty()) {
				x = s.pop();
				while (x < next) {
					ans[k++] = next;
					if (s.empty())
						break;
					x = s.pop();
				}
				if (x > next) {
					s.push(x);
				}
			}

			s.push(next);
		}

		while (!s.empty()) {
			x = s.pop();
			a[k++] = -1;
		}

		return a;
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
