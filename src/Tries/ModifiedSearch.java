package Tries;

public class ModifiedSearch {

	public static void main(String[] args) {

		ModifiedSearch modifiedSearch = new ModifiedSearch();
		TrieNode root = modifiedSearch.new TrieNode();
		modifiedSearch.insertWordInTrie(root, "circle");
		System.out.println(modifiedSearch.findWordExistInTrieWithExactly1Change(root, "circel"));
//		System.out.println();
//		modifiedSearch.insertWordInTrie(root, "circle");
//		System.out.println(modifiedSearch.findWordExistInTrieWithExactly1Change(root, "circel"));

		String[] A = new String[] { "data", "circle", "cricket" };

		String[] B = new String[] { "date", "circel", "crikket", "data", "circl" };

//		String ans = modifiedSearch.solve(A, B);

//		System.out.println(ans);// 10100
	}

	/*
	 * 
	 * 
	 * Given two arrays of strings A of size N and B of size M.
	 * 
	 * Return a binary string C where C[i] = '1' if B[i] can be found in dictionary
	 * A using exactly one modification in B[i], Else C[i] = '0'.
	 * 
	 * NOTE: modification is defined as converting a character into another
	 * character.
	 * 
	 * A = [data, circle, cricket] B = [date, circel, crikket, data, circl]
	 * 
	 */

	public class TrieNode {
		public TrieNode[] children;
		public int count;

		public TrieNode() {

			this.children = new TrieNode[26];
			this.count = 0;

			for (int i = 0; i < 26; i++) {
				this.children[i] = null;
			}
		}
	}

	public void insertWordInTrie(TrieNode root, String s) {
		TrieNode curr = root;
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (curr.children[index] == null) {
				curr.children[index] = new TrieNode();
			}
			curr.count++;
			curr = curr.children[index];
		}
	}

	public boolean findWordExistInTrie(TrieNode root, String s) {

		TrieNode curr = root;

		for (int i = 0; i < s.length(); i++) {

			int index = s.charAt(i) - 'a';

			if (curr.children[index] != null) {
				curr = curr.children[index];
			} else {
				return false;
			}

		}

		return true;

	}

	public boolean findWordExistInTrieWithExactly1Change(TrieNode root, String s) {

		// circle
		// circel

		TrieNode curr = root;
		int changeCount = 0;
		for (int i = 0; i < s.length(); i++) {

			int index = s.charAt(i) - 'a';
			if (curr.children[index] == null) {
				changeCount++;
				System.out.print("NHI Mila - " + (char) (index + 'a') + " , ");
			} else {
				curr = curr.children[index];
				System.out.print("Mila - " + (char) (index + 'a') + " , ");
			}
		}
		if (changeCount == 1)
			return true;
		return false;

	}

	public String solve(String[] A, String[] B) {
		TrieNode root = new TrieNode();
		for (String s : A) {
			insertWordInTrie(root, s);
		}

		StringBuilder sb = new StringBuilder();

		for (String s : B) {
			boolean b = findWordExistInTrieWithExactly1Change(root, s);

			if (b) {
				sb.append(1);
			} else {
				sb.append(0);
			}
		}

		return sb.toString();
	}

}
