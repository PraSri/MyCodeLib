package Tries;

import java.util.ArrayList;

public class PrefixAndSuffix {

	public static void main(String[] args) {
		PrefixAndSuffix prefixAndSuffix = new PrefixAndSuffix();

		boolean test = prefixAndSuffix.isValid(
				"gszbqxlvfmkhjdbxigtuwrirbsywfdsczgszbqxlvfmkhjdbxigtuwrirbsywfdsczsucvzqlgszbqxlvfmkhjdbxigtuwrirbsywfdscz",
				3);
		System.out.println(test);
		System.out.println("szbqxlv".substring(7-3));

	}

	public class Trie {
		public Trie[] next;
		public int f;

		public Trie() {
			this.next = new Trie[26];
			for (int i = 0; i < 26; i++) {
				this.next[i] = null;
			}
			this.f = 0;
		}
	}

	public void insertInTrie(Trie root, String s) {
		Trie t = root;
		int n = s.length();
		for (int i = 0; i < n; i++) {
			int in = s.charAt(i) - 'a';
			if (t.next[in] == null) {
				t.next[in] = new Trie();
			}
			t.next[in].f++;
			t = t.next[in];
		}
	}

	public static int M;

	public ArrayList<Integer> solve(ArrayList<String> A, ArrayList<String> B) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		Trie root = new Trie();

		int m = B.get(0).length();
		M = m;

		for (String s : A) {
			if (isValid(s, m)) {
				insertInTrie(root, s);
			}
		}

		for (String s : B) {
			res.add(countOfPrefixInTrie(root, s, m));
		}

		return res;

	}

	private Integer countOfPrefixInTrie(Trie root, String s, int m) {

		int i = 0, c = 0;
		Trie t = root;
		while (i < s.length()) {
			int in = s.charAt(i) - 'a';
			if (t.next[in] == null) {
				return 0;
			}
			t = t.next[in];
			c++;
			if (c == m) {
				return t.f;
			}
			i++;
		}

		return 0;
	}

	private boolean isValid(String s, int m) {

		int i = 0;
//		aabaab
		int n = s.length();
		if(m>n) {
			return false;
		}
		System.out.println("n=" + n);
		int j = n - m;
		System.out.println("BEFORE WHILE ......");
		while (i < m && j < n-1) {
			System.out.println(s.charAt(i) + ":" + s.charAt(j));
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j++;
		}
		System.out.println("AFTER WHILE ......");
		return true;
	}
	
}
