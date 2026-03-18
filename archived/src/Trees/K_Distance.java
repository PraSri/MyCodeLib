package Trees;

import java.util.HashMap;
import java.util.Map;

public class K_Distance {

	public static void main(String[] args) {

	}

	/*
	 * 
	 *
	 * 
	 * Given a balanced binary tree of integers and an integer B, count the number
	 * of pairs (a, b) where: a is ancestor of b. Absolute difference between value
	 * of node a and value of node b <= B
	 * 
	 * 
	 * 
	 * 
	 */

	public static int count = 0;
	public static int mod = 1000000007;

	public static Map<Integer, Integer> parents;

	public int solve(TreeNode A, int B) {

		parents = new HashMap<Integer, Integer>();

		count = 0;

		helper(A, B);

		return count;

	}

	private void helper(TreeNode a, int b) {

		if (a == null) {
			return;
		}
		count = (count % mod + getCountInRange(a.val - b, a.val + b) % mod) % mod;

		if (!parents.containsKey(a.val)) {
			parents.put(a.val, 1);
		} else {
			parents.put(a.val, parents.get(a.val) + 1);
		}

		helper(a.left, b);
		helper(a.right, b);

		parents.put(a.val, parents.get(a.val) - 1);

		if (parents.get(a.val) == 0) {
			parents.remove(a.val);
		}

	}

	private int getCountInRange(int l, int r) {

		int c = 0;

		for (int k : parents.keySet()) {
			if (l <= k && k <= r) {
				c = (c % mod + parents.get(k) % mod) % mod;
			}
		}

		return c % mod;
	}

}
