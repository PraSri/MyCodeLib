package SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;

public class BobAndQueries {

	public static void main(String[] args) {

		int A = 5;

		int[][] B = new int[][] { { 1, 1, -1 }, { 1, 2, -1 }, { 1, 3, -1 }, { 3, 1, 3 }, { 3, 2, 4 } };

		int[] ans = new BobAndQueries().solve(A, B);

		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " , ");
		}

	}

	public int[] solve(int A, int[][] B) {

		ArrayList<Integer> res = new ArrayList<Integer>();
		int[] tree = new int[4 * A];

		int[] a = new int[A + 1];

		Arrays.fill(a, 0);
		Arrays.fill(tree, 0);

//		buildSegementTree(tree, a, 1, 1, A);

		for (int[] q : B) {

			int type = q[0];
			int y = q[1];
			int z = q[2];
			if (type == 1) {
				update1(tree, a, 0, 0, A - 1, y-1);
			} else if (type == 2) {
				update2(tree, a, 0, 0, A - 1, y-1);
			} else {
				int ans = query(tree, 0, 0, A - 1, y-1, z-1);
				res.add(ans);
			}

		}

		int[] resArr = new int[res.size()];
		int j = 0;
		for (int i : res) {
			resArr[j++] = i;
		}
		return resArr;
	}

	public void buildSegementTree(int[] tree, int[] a, int treeIndex, int s, int e) {

		if (s == e) {
			tree[treeIndex] = a[s];
			return;
		}

		int mid = (s + e) / 2;

		int leftChild = 2 * treeIndex + 1;
		int rightChild = 2 * treeIndex + 2;

		buildSegementTree(tree, a, leftChild, s, mid);
		buildSegementTree(tree, a, rightChild, mid + 1, e);

		/*
		 * 
		 * here your logic is required ..on what purpose u r building the tree..
		 * 
		 * 
		 * like u want to store sum of child nodes or min of child nodes
		 * 
		 * 
		 */
		tree[treeIndex] = getCountOf1(leftChild) + getCountOf1(rightChild);

	}

	private int getCountOf1(int leftChild) {
		return 0;
	}

	public void update1(int[] tree, int[] a, int treeIndex, int s, int e, int y) {

		if (s == e) {

			a[y]++;
			tree[treeIndex]++;

		} else {

			int mid = (s + e) / 2;
			int leftChild = 2 * treeIndex + 1;
			int rightChild = 2 * treeIndex + 2;

			if (s <= y && y <= mid) {
				update1(tree, a, leftChild, s, mid, y);
			} else {
				update1(tree, a, rightChild, mid + 1, e, y);
			}

			tree[treeIndex] = tree[leftChild] + tree[rightChild];

		}

	}

	public void update2(int[] tree, int[] a, int treeIndex, int s, int e, int y) {

		if (s == e) {

			if (a[y] == 0) {
				return;
			}
			a[y]--;
			tree[treeIndex]--;

		} else {

			int mid = (s + e) / 2;
			int leftChild = 2 * treeIndex + 1;
			int rightChild = 2 * treeIndex + 2;

			if (s <= y && y <= mid) {
				update2(tree, a, leftChild, s, mid, y);
			} else {
				update2(tree, a, rightChild, mid + 1, e, y);
			}

			tree[treeIndex] = tree[leftChild] + tree[rightChild];

		}
	}

	public int query(int[] tree, int treeIndex, int s, int e, int l, int r) {

		if (r < s || e < l) {
			return 0;
		}

		if (l <= s && r >= e) {
			return tree[treeIndex];
		}

		int mid = (s + e) / 2;
		int leftChild = 2 * treeIndex + 1;
		int rightChild = 2 * treeIndex + 2;

		int q1 = query(tree, leftChild, s, mid, l, r);
		int q2 = query(tree, rightChild, mid + 1, e, l, r);

		return q1 + q2;

	}

}
