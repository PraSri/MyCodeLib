package Tries;

public class ShortestUniquePrefix {

	public static void main(String[] args) {

	}

	// Given a list of N words. Find shortest unique prefix to represent each word
	// in the list.
	// input : A = ["zebra", "dog", "duck", "dove"]
	// output : B = ["z", "dog", "du", "dov"]

	public class Trie {

		public Trie[] next;
		public boolean unique;

		public Trie() {
			this.next = new Trie[26];
			for (int i = 0; i < 26; i++) {
				this.next[i] = null;
			}
			this.unique = true;
		}

	}

	public void insert(Trie root, String s) {
		Trie t = root;
		int n = s.length();
		for (int i = 0; i < n; i++) {
			int in = s.charAt(i) - 'a';
			if (t.next[in] == null) {
				t.next[in] = new Trie();
			} else {
				t.next[in].unique = false;
			}
			t = t.next[in];
		}
	}

	public String getUniquePrefix(Trie root, String s) {

		StringBuilder sb = new StringBuilder();
		int i = 0;
		Trie t = root;
		while (i < s.length() && t.unique == false) {
			t = t.next[s.charAt(i) - 'a'];
			sb.append(s.charAt(i));
			i++;
		}
		return sb.toString();
	}

	public String[] prefix(String[] A) {

		Trie root = new Trie();
		for (String s : A) {
			insert(root, s);
		}

		int i = 0;

		for (String s : A) {
			A[i++] = getUniquePrefix(root, s);
		}

		return A;
	}

}
//https://ide.geeksforgeeks.org/VPDBQ3EPKx