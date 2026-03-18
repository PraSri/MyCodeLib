package Easy;

public class AllPermutationsOfAString {

	public static void main(String[] args) {

		permuteString("abb");

	}

	public static void permuteString(String s) {
		int n = s.length();
		permute(s, 0, n - 1);
	}

	public static void permute(String s, int l, int r) {

		if (l == r)
			System.out.println(s);
		else {
			for (int i = l; i <= r; i++) {
				s = swap(s, i, l);
				permute(s, l + 1, r);
				s = swap(s, i, l);
			}
		}

	}

	private static String swap(String s, int i, int l) {

		char[] c = s.toCharArray();

		char t = c[i];
		c[i] = c[l];
		c[l] = t;

		return String.valueOf(c);

	}

}
