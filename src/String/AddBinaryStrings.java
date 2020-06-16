package String;

public class AddBinaryStrings {

	public static void main(String[] args) {

		System.out.println(addBinary("100", "11"));

	}

	public static String addBinary(String A, String B) {

		int i = A.length() - 1;
		int j = B.length() - 1;

		int carry = 0;

		StringBuilder sb = new StringBuilder();

		while (i >= 0 || j >= 0) {

			int sum = carry;

			if (i >= 0) {
				sum += A.charAt(i) - '0';
				i--;
			}
			if (j >= 0) {
				sum += B.charAt(j) - '0';
				j--;
			}
			sb.append(sum % 2);
			carry = sum / 2;
		}

		if (carry != 0) {
			sb.append(carry);
		}

		return sb.reverse().toString();

	}

}
