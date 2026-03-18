package String;

import java.util.ArrayList;

public class ReverseTheString {

	public static void main(String[] args) {
		System.out.println(solve("the sky is blue"));
	}

	public static String solve(String A) {

		ArrayList<String> a = new ArrayList<>();
		String[] s = A.split(" ");
		String res = "";
		int n = s.length;
		for (int i = 0; i < n; i++) {
			res = res + s[n - 1 - i];
			res = res + " ";
		}
		return res.trim();
	}

	/*
	 * 
	 * 
	 * "the sky is blue" if(space ) put in string array
	 * 
	 * eht yks si eulb
	 * blue is sky the
	 * 
	 * 
	 * 
	 **/

	public class Solution {

		public String reverseWords(String A) {
			char[] array = A.toCharArray();
			int i;
			int n = A.length();

			for (i = 0; i < n; i++) {
				while (i < n && array[i] == ' ')
					i++;
				int start = i;
				int end = -1;
				while (i < n && array[i] != ' ')
					i++;
				end = i - 1;
				if (end < start)
					break;

				reverse(array, start, end);
			} 

			reverse(array, 0, n - 1);

			return removeExtraSpaces(array);
		}

		public String removeExtraSpaces(char[] array) {
			int n = array.length;
			int index = 0;
			boolean cond = false;

			for (int i = 0; i < n;) {

				if (array[i] != ' ') {
					array[index] = array[i];
					index++;
					cond = true;
				} else {
					if (cond)
						array[index++] = ' ';
					cond = false;
				}

				i++;
			}

			if (index - 1 >= 0 && index - 1 < n && array[index - 1] == ' ')
				index--;

			return new String(array, 0, index);

		}

		public void reverse(char[] array, int start, int end) {
			char temp;
			int i;

			for (i = 0; i < (end - start + 1) / 2; i++) {
				temp = array[start + i];
				array[start + i] = array[end - i];
				array[end - i] = temp;
			}

		}

	}

}
