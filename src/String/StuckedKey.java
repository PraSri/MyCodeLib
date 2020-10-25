package String;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class StuckedKey {

	// https://leetcode.com/discuss/interview-question/679321/Google-or-Onsite-or-Determine-if-word-is-typo-because-of-stuck-key/573400

	public static void main(String[] args) {

		String[] A = new String[] { "hello", "cat", "world", "dog", "bird", "grass", "green", "help", "greet",
				"great" };

		String B = "";

		System.out.println(solve(A, B));

	}

	public static int solve(String[] A, String B) {

		Set<String> a = new HashSet<String>();

		for (String s : A) {
			a.add(s);
		}

		if (a.contains(B))
			return 0;

		Set<Character> b = new LinkedHashSet<Character>();

		for (char c : B.toCharArray()) {
			b.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character c : b) {
			sb.append(c);
		}

		if (a.contains(sb.toString())) {
			return 1;
		}

		return 0;

	}

}
