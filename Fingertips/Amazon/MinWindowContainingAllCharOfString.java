package Amazon;

import java.util.HashMap;
import java.util.Map;

public class MinWindowContainingAllCharOfString {

	public static void main(String[] args) {

		MinWindowContainingAllCharOfString ob = new MinWindowContainingAllCharOfString();
		System.out.println(ob.minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(ob.minWindow("this is a test string", "tist"));

	}

	public String minWindow(String s, String t) {

		// two pointers
		int start = 0, end = 0;
		// increase end to find the valid window
		// increase start to minimize the window
		int minStart = 0;
		int minLength = Integer.MAX_VALUE;
		int size = s.length();
		// this counter indicates that the window is found as it equals zero
		// decrease the window length till counter is zero by increasing start pointer and reducing the window size
		int counter = t.length();

		// keep track of frequency of chars in pattern
		Map<Character, Integer> m = new HashMap<Character, Integer>();

		for (char ch : t.toCharArray()) {
			if (!m.containsKey(ch)) {
				m.put(ch, 1);
			} else {
				m.put(ch, m.get(ch) + 1);
			}
		}

		while (end < size) {

			if (m.containsKey(s.charAt(end))) {
				if (m.get(s.charAt(end)) > 0) {
					counter--;
				}

				m.put(s.charAt(end), m.get(s.charAt(end)) - 1);

			}

			end++;

			// when u found valid window
			while (counter == 0) {
				if (end - start < minLength) {
					minStart = start;
					minLength = end - start;
				}
				if (m.containsKey(s.charAt(start))) {
					m.put(s.charAt(start), m.get(s.charAt(start)) + 1);
					if (m.get(s.charAt(start)) > 0) {
						counter++;
					}
				}
				start++;
			}

		}

		if (minLength != Integer.MAX_VALUE) {
			return s.substring(minStart, minStart + minLength);
		}

		return "";

	}

}
