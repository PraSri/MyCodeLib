package GoldmanSachs;

public class Binary_Search_in_forest {

	/******
	 * There are n tree in a forest. Heights of the trees is stored in array tree[
	 * ]. If ith tree is cut at height h, the wood obtained is tree[i]-h, given that
	 * tree[i]>h. If total wood needed is k (not less, neither more) find the height
	 * at which every tree should be cut (all trees have to be cut at the same
	 * height).
	 * 
	 * Input: N = 5, K = 4 tree[] = {2, 3, 6, 2, 4} Output: 3
	 * 
	 * Input: N = 6, K = 8 tree[] = {1 7 6 3 4 7} Output: 4
	 ************/
	public static void main(String[] args) {
		System.out.println(find_height(new int[] { 2, 3, 6, 2, 4 }, 5, 4));
	}

	static int wood_collected(int tree[], int n, int h) {
	
		int ret = 0;

		// counting the amount of wood that gets collected
		// if we cut trees at height h
		for (int i = 0; i < n; i++)
			if (tree[i] > h)
				ret += tree[i] - h;

		return ret;
	}

	static int find_height(int tree[], int n, int k) {
		// l is lower limit of binary search
		// h is upper limit
		int l = 0, h = 0;
		for (int i = 0; i < n; i++)
			if (tree[i] >= h)
				h = tree[i];

		while (l <= h) {
			int mid = (l + h) / 2;
			// treat mid as the height at which trees should be cut & get answer for that
			int val = wood_collected(tree, n, mid);

			// if answer is equal to k from height = mid return mid
			if (val == k)
				return mid;

			if (val > k)
				l = mid + 1;
			// if wood collected is too much, we increase lower limit
			else
				h = mid - 1;
			// if wood collected is too less, we decrease uppwer limit
		}
		return -1;
	}
}
