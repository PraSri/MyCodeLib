package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class FirstNonRepeatingCharacter {

	public static void main(String[] args) {
		
		System.out.println(solve("abcabc"));

	}

	/*
	 * Given a string A denoting a stream of lowercase alphabets.
	 * 
	 * You have to make new string B. B is formed such that we have to find first
	 * non-repeating character each time a character is inserted to the stream and
	 * append it at the end to B. if no non-repeating character is found then append
	 * '#' at the end of B.
	 * 
	 */

	public static String solve(String A) {

		int[] frequency = new int[26];
		Queue<Character> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for (char c : A.toCharArray()) {
			q.add(c);
			frequency[c - 'a']++;
			while (!q.isEmpty()) {
				if (frequency[q.peek() - 'a'] > 1) {
					q.remove();
				} else {
					sb.append(q.peek());
					break;
				}
			}
			if (q.isEmpty()) {
				sb.append('#');
			}
		}

		return sb.toString();

	}

}
