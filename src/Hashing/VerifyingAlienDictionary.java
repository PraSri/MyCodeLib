package Hashing;

public class VerifyingAlienDictionary {

	public static void main(String[] args) {

		VerifyingAlienDictionary v = new VerifyingAlienDictionary();

		System.out.println(v.solve(new String[] { "hello", "scaler", "interviewbit" }, "adhbcfegskjlponmirqtxwuvzy"));

	}

	int[] dict = new int[26];

	public int solve(String[] A, String B) {

		int i = 0;
		for (char c : B.toCharArray()) {
			dict[c - 'a'] = i++;
		}

		for (i = 1; i < A.length; i++) {
			if (checkOrder(A[i - 1], A[i])) { // check if A[i-1]>A[i] return true
				return 0;
			}
		}

		return 1;
	}

	private boolean checkOrder(String a, String b) {

		for (int i = 0; i < a.length() && i < b.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				return dict[a.charAt(i) - 'a'] > dict[b.charAt(i) - 'a'];
			}
		}

		return a.length() > b.length();
	}

}
