package fingertips;

public class CountAndSay {

	public static void main(String[] args) {
		System.out.println(countAndSay(5));
	}

	public static String countAndSay(int A) {

		int n = A;

		String s = "1";

		if (n == 1) {
			return s;
		}
		// 1, 11, 21, 1211, 111221, ...

		s = "11";

		if (n == 2) {
			return s;
		}

		for (int i = 3; i <= n; i++) {
			int ct = 1;
			s = s + "$";
			String t = "";
			char[] c = s.toCharArray();
			for (int j = 1; j < s.length(); j++) {
				if (c[j] != c[j - 1]) {
					t = t + String.valueOf(ct) + String.valueOf(c[j - 1]);
					ct = 1;
				} else {
					ct++;
				}
			}
			s = t;
		}
		return s;
	}

}
