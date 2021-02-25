package GoldmanSachs;

public class RunLengthEncoding {

	/*****
	 * For example, if the input string is “wwwwaaadexxxxxx”, then the function
	 * should return “w4a3d1e1x6”
	 *******/

	public static void main(String[] args) {

		String str = "wwwwaaadexxxxxxywww";
		printRLE(str);

	}

	public static String printRLE(String str) {
		int n = str.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {

			// Count occurrences of current character
			int count = 1;
			while (i < n - 1 && str.charAt(i) == str.charAt(i + 1)) {
				count++;
				i++;
			}

			sb.append(str.charAt(i));
			sb.append(count);

			// Print character and its count
			System.out.print(str.charAt(i));
			System.out.print(count);
		}
		return sb.toString();
	}

}
