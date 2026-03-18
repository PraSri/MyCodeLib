package String;

import java.util.Arrays;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		System.out.println(longestCommonPrefix(new String[] { "abcdefgh", "aefghijk", "abcefgh" }));
		System.out.println(longestCommonPrefix(new String[] { "abab", "ab", "abcd" }));
	}

	public static String longestCommonPrefix(String[] A) {
		Arrays.sort(A);
		int n = A.length;
		int l = Math.min(A[0].length(), A[n - 1].length());
		int i = 0;
		while (i < l && A[0].charAt(i) == A[n - 1].charAt(i)) {
			i++;
		}
		String res = A[0].substring(0, i);
		return res;
	}

}
