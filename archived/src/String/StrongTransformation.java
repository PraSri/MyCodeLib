package String;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrongTransformation {

	public static void main(String[] args) {

		System.out.println(solve("ababc", "adadg"));

	}

	/**
	 * Problem Description
	 * 
	 * Rick and Morty are best friends. Rick and Morty will change their name to
	 * strings A and B respectively.
	 * 
	 * They want to know if it is possible to transform A into B by doing zero or
	 * more conversions.
	 * 
	 * In one conversion you can convert all occurrences of one character in A to
	 * any other lowercase English character.
	 * 
	 * 
	 * 
	 * Problem Constraints
	 * 
	 * 1 <= len(A) <= 105 len(A) == len(B)
	 * 
	 * 
	 */

	/*** SOLUTION APPROACH **/

	/***
	 * 
	 * 
	 * 
	 * 
	 * 
	 * When a single character is changing into more than 1 character, return 0 as
	 * it will never be possible as in one operation all occurrence of a character
	 * is changed.
	 * 
	 * 
	 * When a single character is mapped to more than 1 character, transformation is
	 * not possible as all occurrence of a character is changed.
	 * 
	 * Is this the only condition, we have to check ?
	 * 
	 * For example: A = “abcd”, B = “bcda”
	 * 
	 * Convert a -> d
	 * 
	 * A = “dbcd”, Now if we change d to a, first occurence of d is also changed.
	 * 
	 * To solve this, we can change d to a dummy character first which is not in
	 * string.
	 * 
	 * For example: First convert d to e. A = “abce”. then perform following
	 * conversions: c to d, A = “abde” b to c, A = “acde” a to b, A = “bcde”
	 * 
	 * now change e to a, A = “bcda” which is equal to B
	 * 
	 * What will happend if have no seperate character to take as dummy character.
	 * Means all 26(a-z) characters are in B.
	 * 
	 * Then we cann’t change A to B unless A is equal to B intially.
	 * 
	 * 
	 * 
	 * 
	 */

	public static int solve(String A, String B) {

		if (A == B)
			return 1;
		int n = A.length();

		Map<Character, Character> m = new HashMap<Character, Character>();

		Set<Character> s = new HashSet<Character>();

		for (int i = 0; i < n; i++) {
			if (!m.containsKey(A.charAt(i))) {

				m.put(A.charAt(i), B.charAt(i));

			} else if (m.get(A.charAt(i)) != B.charAt(i)) {

				return 0;

			}

			s.add(B.charAt(i));

		}

		if (s.size() == 26)
			return 0;

		return 1;

	}

}
