package String;

public class RomanToInteger {

	public static void main(String[] args) {
		System.out.println(romanToInt("XI"));
	}

	public static int romanToInt(String A) {
		int n = A.length();
		int t = 0;
		for (int i = 0; i < n; i++) {
			int a = value(A.charAt(i));
			if (i + 1 < n) {
				int b = value(A.charAt(i + 1));
				if (a >= b) {
					t = t + a;
				} else {
					t = t + b - a;
					i++;
				}
			} else {
				t = t + a;
				i++;
			}
		}

		return t;

	}

	private static int value(char c) {
		if (c == 'I')
			return 1;
		if (c == 'V')
			return 5;
		if (c == 'X')
			return 10;
		if (c == 'L')
			return 50;
		if (c == 'C')
			return 100;
		if (c == 'D')
			return 500;
		if (c == 'M')
			return 1000;
		return -1;
	}

}
