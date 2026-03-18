package Tries;

public class MaximumXOR {

	public static void main(String[] args) {

		MaximumXOR m = new MaximumXOR();
		int x = m.solve(new int[] { 5, 6 });
		System.out.println(x);

	}

//Given an array of integers A, find and return the maximum result of A[i] XOR A[j], where i, j are the indexes of the array.
	class Trie {
		Trie[] children;

		public Trie() {
			children = new Trie[2];
		}
	}

	public int solve(int[] A) {
		int ans = Integer.MIN_VALUE;
		Trie root = new Trie();
		Trie curr;
		for (int num : A) {
			curr = root;
			for (int i = 30; i >= 0; i--) {
				int bit = getIthBit(num, i);
				if (curr.children[bit ^ 1] == null) {
					curr.children[bit ^ 1] = new Trie();
				}
				curr = curr.children[bit ^ 1];
			}
		}
		for (int num : A) {

			curr = root;
			int res = 0;
			for (int i = 30; i >= 0; i--) {
				int bit = getIthBit(num, i);
				if (curr.children[bit] != null) {
					curr = curr.children[bit];
					res += (1 << i);
				} else {
					curr = curr.children[bit ^ 1];
				}

			}

			ans = Math.max(ans, res);
			if (ans == Integer.MAX_VALUE) {
				break;
			}
		}

		return ans;
	}

	private int getIthBit(int n, int i) {
		return (n & (1 << i)) == 0 ? 0 : 1;
	}

}
