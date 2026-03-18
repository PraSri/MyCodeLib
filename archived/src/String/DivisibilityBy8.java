package String;

public class DivisibilityBy8 {

	public static void main(String[] args) {
//		System.out.println('3' - '0');
//		int i = '3' - '0';
//		System.out.println(i);
		System.out.println(solve("160"));
	}

	public static int solve(String A) {
		int n = A.length();
		int i = A.charAt(n - 1) - '0';
		if (n>1 && i % 2 != 0) {
			return 0;
		}
		if (n < 4) {
			return Integer.parseInt(A) % 8 == 0 ? 1 : 0;
		}

		int lastThreeDigits = (A.charAt(n - 3) - '0') * 100 + (A.charAt(n - 2) - '0') * 10 + (A.charAt(n - 1) - '0');

		return lastThreeDigits % 8 == 0 ? 1 : 0;

	}

}
