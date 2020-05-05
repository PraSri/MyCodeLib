package String;

public class MinimumCharactersRequiredToMakeAStringPalindromic {

	public static void main(String[] args) {
		System.out.println(solve("ABC"));
	}

	public static int solve(String A) {
		char[] c = A.toCharArray();
		reverse(c, 0, c.length - 1);
		String rev = String.valueOf(c);
		String checker = A + "$" + rev;
		int[] lps = lps(checker);
		int ans = A.length() - lps[lps.length - 1];
		return ans;
	}

	public static int[] lps(String s) {
		int[] lps = new int[s.length()];
		lps[0] = 0;
		int i = 1;
		int j = 0;
		int n = s.length();
		while (i < n) {
			if (s.charAt(i) == s.charAt(j)) {
				j++;
				lps[i] = j;
				i++;
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	public static void reverse(char[] array, int start, int end) {
		char temp;
		int i;

		for (i = 0; i < (end - start + 1) / 2; i++) {
			temp = array[start + i];
			array[start + i] = array[end - i];
			array[end - i] = temp;
		}

	}

}
