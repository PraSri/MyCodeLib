package SegmentTree;

import java.util.ArrayList;

public class PowerOf3 {

	public static void main(String[] args) {
		Integer x = '1' - '0';
		System.out.println(x.getClass());

	}
	
	/*****>>>>>WRONG SOLUTION<<<<<******/

	public int[] solve(String A, int[][] B) {
		char[] c = A.toCharArray();
		int n = c.length;
		ArrayList<Integer> res = new ArrayList<Integer>();
		int[] tree = new int[4 * n];

		for (int[] b : B) {
			int[] q = b;

			int type = q[0];
			int l = q[1];
			int r = q[2];

			if (type == 1) {
				res.add(update(tree, c, 0, n - 1, 0, l));
			} else {
				res.add(query(tree, c, 0, n - 1, 0, l, r));
			}

		}

		int[] resArr = new int[res.size()];
		int j = 0;
		for (int i : res) {
			resArr[j++] = i;
		}
		return resArr;

	}

	public int update(int[] tree, char[] c, int s, int e, int treeIndex, int flipIndex) {

		if (s == e) {
			if (c[flipIndex] == '0') {

				c[flipIndex] = '1';

				tree[treeIndex] = 1;
			}
			return -1;
		}

		int mid = (s + e) / 2;
		int leftChild = 2 * treeIndex + 1;
		int rightChild = 2 * treeIndex + 2;

		if (s <= flipIndex && flipIndex <= mid) {
			update(tree, c, s, mid, leftChild, flipIndex);
		} else {
			update(tree, c, mid + 1, e, rightChild, flipIndex);
		}

		return -1;

	}

	public int query(int[] tree, char[] c, int s, int e, int treeIndex, int l, int r) {

		if (r < s || e < l) {
			return 0;
		}

		if (l <= s && r >= e) {

			return tree[treeIndex];
		}

		int mid = (s + e) / 2;
		int leftChild = 2 * treeIndex + 1;
		int rightChild = 2 * treeIndex + 2;

		int sum = 0;
		int j = 0;
		for (int i = r; i >= l; i--) {
			sum = (int) (sum + (c[i] - '0') * Math.pow(2, j++));
		}

		tree[leftChild] = sum;

		tree[rightChild] = sum;

		return query(tree, c, s, mid, leftChild, l, r) + query(tree, c, mid + 1, e, rightChild, l, r);

	}

}
