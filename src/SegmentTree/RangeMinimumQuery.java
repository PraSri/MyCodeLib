package SegmentTree;

import java.util.ArrayList;

public class RangeMinimumQuery {

	public static void main(String[] args) {
//		int[] a = new int[] { 1, 4, 1 };
//		int[][] b = new int[][] { { 1, 1, 3 }, { 0, 1, 5 }, { 1, 1, 2 } };
//		for (int x : solve(a, b)) {
//			System.out.print(x + " ");
//		}
		System.out.println("abc".hashCode());
		System.out.println("cba".hashCode());
	}

	public static int[] solve(int[] A, int[][] B) {
		int n = A.length;
//		int xi = (int) (Math.ceil(Math.log(n) / Math.log(2)));
//		int max_size = 2 * (int) Math.pow(2, xi) - 1;
		int[] tree = new int[2 * n - 1];
		build(tree, A, 0, 0, n - 1);
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < B.length; i++) {
			int x = B[i][0];
			int y = B[i][1];
			int z = B[i][2];
			if (x == 0) {
				update(tree, 0, 0, n - 1, y, z);
			} else if (x == 1) {
				a.add(query(tree, 0, 0, n - 1, y, z));
			}
		}
		int[] ans = new int[a.size()];
		for (int i = 0; i < a.size(); i++) {
			ans[i] = a.get(i);
		}
		return ans;
	}

	public static void build(int[] tree, int[] a, int i, int s, int e) {
		// if leaf node
		if (s == e) {
			tree[i] = a[s];
			return;
		}
		int mid = (s + e) / 2;
		int lc = 2 * i + 1;
		int rc = 2 * i + 2;
		build(tree, a, lc, s, mid);
		build(tree, a, rc, mid + 1, e);
		tree[i] = Math.min(tree[lc], tree[rc]);
	}

	public static int query(int[] tree, int i, int s, int e, int l, int r) {
		// no overlap
		if (r < s || l > e) {
			return Integer.MAX_VALUE;
		}
		// completely overlap
		if (l <= s & r >= e) {
			return tree[i];
		}
		int mid = (s + e) / 2;
		int lc = 2 * i + 1;
		int rc = 2 * i + 2;
		// partial overlap
		return Math.min(query(tree, lc, s, mid, l, r), query(tree, rc, mid + 1, e, l, r));
	}

	public static void update(int[] tree, int i, int s, int e, int in, int value) {
		if (s > in || e < in) {
			return;
		}
		if (s == e) {
			tree[i] = value;
			return;
		} else {
			int mid = (s + e) / 2;
			int lc = 2 * i + 1;
			int rc = 2 * i + 2;
			update(tree, lc, s, mid, in, value);
			update(tree, rc, mid + 1, e, in, value);
			tree[i] = Math.min(tree[lc], tree[rc]);
		}
	}

}
