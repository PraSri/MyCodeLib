package Stack;

import java.util.Stack;

public class Kth_character_in_double_decrypted_string {

	public static void main(String[] args) {
		System.out.println(solve("tu16mj75s", 56));
	}

	/*
	 * 
	 * ASKED IN GOOGLE
	 * 
	 * 
	 * 
	 * kth character in double decrypted string
	 * 
	 * Given a String A and an integer B. String A is encoded consisting of
	 * lowercase English letters and numbers. A is encoded in a way where
	 * repetitions of substrings are represented as substring followed by the count
	 * of substrings.
	 * 
	 * For example: if the encrypted string is “ab2cd2” and B=6, so the output will
	 * be ‘d’ because the decrypted string is “ababcdababcd” and 4th character is
	 * ‘b’.
	 * 
	 * You have to find and return the Bth character in the decrypted string.
	 * 
	 * Note: Frequency of encrypted substring can be of more than one digit. For
	 * example, in “ab12c3”, ab is repeated 12 times. No leading 0 is present in the
	 * frequency of substring.
	 */

	public static class Pair {
		long l;
		char c;

		/**
		 * @param l
		 * @param c
		 */
		public Pair(long l, char c) {
			this.l = l;
			this.c = c;
		}

	}

	public static String solve(String A, int B) {

		char[] s = A.toCharArray();

		long len = 0;

		Stack<Pair> st = new Stack<>();

		for (int i = 0; i < s.length;) {
			if (isAlpha(s[i])) {
				len++;
				st.push(new Pair(len, s[i]));
				i++;
			} else if (isDigit(s[i])) {
				int x = 0;
				while (i < s.length && isDigit(s[i])) {
					x = x * 10 + (int) (s[i] - '0');
					i++;
				}
				len = len * (long) x;
			}
			if (len > B) {
				break;
			}
		}

		while (!st.empty()) {
			Pair p = st.pop();

			B = B % (int) p.l;

			if (B == 0) {
				return "" + p.c;
			}

		}

		return "";
	}

	private static boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	private static boolean isAlpha(char c) {
		return !Character.isDigit(c);
	}

}
