package Stack;

import java.util.Stack;

public class LargestRectangleInHistogram {

	public static void main(String[] args) {

		System.out.println(largestRectangleArea(new int[] { 69, 47, 84, 7, 70, 73, 4, 73, 70, 54, 2, 35, 32, 53, 99, 41,
				90, 53, 55, 6, 1, 95, 63, 63, 74, 12, 32, 89, 69, 71, 17, 49, 9, 40, 10, 56 }));
//		A : [ 69, 47, 84, 7, 70, 73, 4, 73, 70, 54, 2, 35, 32, 53, 99, 41, 90, 53, 55, 6, 1, 95, 63, 63, 74, 12, 32, 89, 69, 71, 17, 49, 9, 40, 10, 56 ]
	}

	public static int largestRectangleArea(int[] A) {

		int n = A.length;
		Stack<Integer> s = new Stack<>();

		int area = 0, max = 0;
		int i = 0;
		while (i < n) {
			int curr = A[i];
			if (s.empty() || curr > A[s.peek()]) {
				s.push(i);
				i++;
			} else if (curr <= A[s.peek()]) {
				int x = s.pop();
				if (s.empty()) {
					long l = A[x] * (i);
					area = (int) l;
				} else {
					long l = A[x] * (i - s.peek() - 1);
					area = (int) l;
				}
				if (area > max) {
					max = area;
				}
			}
		}

		while (!s.empty()) {
			int x = s.pop();
			if (s.empty()) {
				long l = A[x] * (i);
				area = (int) l;
			} else {
				long l = A[x] * (i - s.peek() - 1);
				area = (int) l;
			}
			if (area > max) {
				max = area;
			}
		}

		return max;
	}

}
