package String;

public class ImplementStrStr {

	public static void main(String[] args) {

	}

	public static int strStr(final String A, final String B) {

		int a = A.length();
		int b = B.length();

		if (a == 0 || b == 0) {
			return -1;
		}

		for (int i = 0; i < a; i++) {
			if (i + b > a)
				break;
			for (int j = 0; j < b; j++) {

				if (A.charAt(i + j) != B.charAt(j)) {
					break;
				}

				if (j == b) {
					return i;
				}

			}
		}

		return -1;

	}

}
