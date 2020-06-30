package Stack;

import java.util.Stack;

public class OrderThem {

	public static void main(String[] args) {

		System.out.println(solve(new int[] { 5, 1, 2, 4, 3 }));// 1
		System.out.println(solve(new int[] { 5, 3, 1, 4, 2 }));// 0
		System.out.println(solve(new int[] { 5, 2, 1, 4, 3 }));
	}

	public static int solve(int[] A) {
		int n = A.length;

		int[] B = new int[n];

		Stack<Integer> C = new Stack<>();
		int diff = n - 1;
		int k = 0;
		for (int i = 0; i < n; i++) {
			if (n - A[i] == diff) {
				B[k++] = A[i];
				diff--;
			} else {
				C.push(A[i]);
			}
			if (!C.isEmpty()) {
				int top = C.peek();
				if (k > 0 && top - B[k - 1] == 1) {
					B[k++] = top;
					C.pop();
					diff = n - top;
				}
			}
		}
		int end = B[k - 1];
		while (!C.isEmpty()) {
			int top = C.pop();
			if (top - end == 1) {
				end = top;
			} else if (top - end != 1) {
				return 0;
			}
		}
		return 1;
	}

}
/*
 * System.out.println("B"); for (int i = 0; i < k; i++) { System.out.print(B[i]
 * + " "); } System.out.println("C"); while (!C.isEmpty()) {
 * System.out.print(C.pop() + " "); }
 */