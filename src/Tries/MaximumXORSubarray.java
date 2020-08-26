package Tries;

import java.util.HashMap;
import java.util.Map;

public class MaximumXORSubarray {

	public static void main(String[] args) {

		MaximumXORSubarray maximumXORSubarray = new MaximumXORSubarray();

		int[] A = new int[] { 1 };

		int[] ans = maximumXORSubarray.solve(A);

		System.out.println(ans[0] + " -  " + ans[1]);

	}

	/*
	 * 
	 * 
	 * Build a prefXor array in which the ith element represents the xor of all
	 * elements from 0 to i. To find the xor of any subarray[l:r], we can just take
	 * the xor of prefXor[r] and prefXor[l-1].
	 * 
	 * To find the maximum xor subarray ending at the index i, insert the bit
	 * representation(starting from most significant bit) of all the elements of
	 * prefXor array upto i-1 into the trie data structure. We have two possible
	 * cases at ith index.
	 * 
	 * The prefix itself has maximum xor. We need to remove some prefix (ending at
	 * index from 0 to i-1).Try to have most significant bit to be set bit i.e. 1.
	 * As we have maintained the trie data structure of bit representation of i-1
	 * elements of prefXor array, we can find the maximum xor in O(logm) where m is
	 * the maximum number present in the given array. We can find the maximum
	 * subarray ending at every index and return the subarray which has maximum XOR
	 * value
	 * 
	 * 
	 */

	public class Trie {
		int v;
		Trie[] children = new Trie[2];

		public Trie() {
			v = 0;
			children[0] = null;
			children[1] = null;
		}

	}

	private int getIthBit(int n, int i) {
		return (n & (1 << i)) == 0 ? 0 : 1;
	}

	private void insert(Trie root, int num) {
		Trie curr = root;
		for (int i = 31; i >= 0; i--) {
			int bit = getIthBit(num, i);
			if (curr.children[bit] == null) {
				curr.children[bit] = new Trie();
			}
			curr = curr.children[bit];
		}

		curr.v++;
	}

	private int findXOR(Trie root, int num) {
		Trie curr = root;
		int ans = 0;
		for (int i = 31; i >= 0; i--) {
			int bit = getIthBit(num, i);
			if (curr.children[bit ^ 1] != null) {
				ans = ans + (1 << i);
				curr = curr.children[bit ^ 1];
			} else {
				curr = curr.children[bit];
			}
		}
		return ans;
	}

	public int[] solve(int[] A) {
		int n = A.length;

		if (n == 1) {
			return new int[] { 1, 1 };
		}

		int[] prefixXOR = new int[n];

		prefixXOR[0] = A[0];
		for (int i = 1; i < n; i++) {
			prefixXOR[i] = prefixXOR[i - 1] ^ A[i];
		}

		int[] ans = new int[2];

		int max = prefixXOR[0];

		Trie root = new Trie();

		insert(root, prefixXOR[0]);

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		map.put(prefixXOR[0], 0);

		for (int i = 1; i < n; i++) {

			map.put(prefixXOR[i], i);

			if (prefixXOR[i] > max) {
				max = prefixXOR[i];
				ans[0] = 1;
				ans[1] = i + 1;
			} else if (prefixXOR[i] == max) {
				if (ans[0] != ans[1]) {
					ans[0] = i + 1;
					ans[1] = i + 1;
				}
			}

			int curMax = findXOR(root, prefixXOR[i]);

			int res = curMax ^ prefixXOR[i];

			int j = map.get(res);

			j++;

			if (curMax > max) {
				max = curMax;
				ans[0] = j + 1;
				ans[1] = i + 1;
			} else if (curMax == max) {
				int curL = i - j + 1;
				int ansL = ans[1] - ans[0] + 1;
				if (curL < ansL) {
					ans[0] = j + 1;
					ans[1] = i + 1;
				}
			}

			insert(root, prefixXOR[i]);
		}

		return ans;
	}

}
