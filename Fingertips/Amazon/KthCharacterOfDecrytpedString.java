package Amazon;

import QuickHelper.CheckCharacterAlphaDigitSpecial;

public class KthCharacterOfDecrytpedString {

	public static void main(String[] args) {
		
		System.out.println(getKthCharFromDecryptString("a2b2c3", 5));

	}

	/***
	 * 
	 * 
	 * Given an encoded string where repetitions of substrings are represented as
	 * substring followed by count of substrings. For example, if encrypted string
	 * is “ab2cd2” and k=4, so output will be ‘b’ because decrypted string is
	 * “ababcdcd” and 4th character is ‘b’.
	 * 
	 * 
	 *******/

	/****** Space Complexity O(n) solution is straight forward *******/

	/*********
	 * Space Complexity O(1) solution is tricky and here is solution
	 *******/

	public static char getKthCharFromDecryptString(String s, int k) {

		return helper(s.toCharArray(), k);

	}

	private static char helper(char[] c, int k) {

		int n = c.length;

		int i = 0, j = 0, len = 0;

		while (i < n) {

			j = i;

			// len of substring
			len = 0;

			int f = 0;

			// loop till digit is not found
			while (j < n && isAlpha(c[j])) {
				j++;
				len++;
			}

			// loop till char is not found
			while (j < n && isDigit(c[j])) {
				f = f * 10 + (c[j] - '0');// frequency can be of more than one digit
				j++;
			}

			int num = len * f;

			if (num < k) {
				k = k - num;
				i = j;
			} else {
				k--;// k is 1 indexed
				k = k % len;
				return c[i + k];
			}

		}

		return c[k - 1];
	}

	private static boolean isAlpha(char c) {
		return CheckCharacterAlphaDigitSpecial.isLetter(c);
	}

	private static boolean isDigit(char c) {
		return CheckCharacterAlphaDigitSpecial.isDigit(c);
	}

}
