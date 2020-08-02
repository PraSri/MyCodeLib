package BinarySearchTrees;

import java.util.Stack;

public class CommonNodesInTwoBST {

	public static void main(String[] args) {

	}

	public int solve(TreeNode A, TreeNode B) {

		return helper(A, B);

	}

	private int helper(TreeNode a, TreeNode b) {
		long count = 0;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		while (true) {
			if (a != null) {
				s1.add(a);
				a = a.left;
			} else if (b != null) {
				s2.add(b);
				b = b.left;
			} else if (!s1.isEmpty() && !s2.isEmpty()) {
				a = s1.peek();
				b = s2.peek();
				if (a.val == b.val) {
					count = (count + a.val) % 1000000007;
					s1.pop();
					s2.pop();
					a = a.right;
					b = b.right;
				} else if (a.val < b.val) {
					s1.pop();
					a = a.right;
					b = null;
				} else if (a.val > b.val) {
					s2.pop();
					a = null;
					b = b.right;
				}
			} else {
				break;
			}

		}

		return (int) count;
	}

}
