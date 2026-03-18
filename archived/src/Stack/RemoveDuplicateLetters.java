package Stack;

import java.util.Stack;

public class RemoveDuplicateLetters {

	public static void main(String[] args) {
		System.out.println(solve("cbacdcbc"));

	}

	public static String solve(String A) {

		// visited frequency

		int[] freq = new int[26];

		char[] ch = A.toCharArray();

		for (char c : ch) {
			freq[c - 'a']++;
		}

		boolean[] visited = new boolean[26];

		Stack<Character> st = new Stack<>();

		int i = 0;
		for (char x : ch) {
			i = x - 'a';
			freq[i]--;
			if (visited[i]) {
				continue;
			}

			while (!st.empty() && x < st.peek() && freq[st.peek() - 'a'] != 0) {
				visited[st.pop() - 'a'] = false;
			}

			st.push(x);
			visited[i] = true;

		}

		StringBuilder sb = new StringBuilder();

		while (!st.empty()) {
			sb.append(st.pop());
		}

		return sb.reverse().toString();

	}

}
