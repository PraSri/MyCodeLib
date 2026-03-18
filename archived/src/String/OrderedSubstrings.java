package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class OrderedSubstrings {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>(Arrays.asList("a", "b", "ab"));
		for (String S : solve(a)) {
			System.out.print(S + " ");
		}
//		System.out.println(findLargestString(a));
	}
	/*
	 * A: [abc, abcd, a, abc]
	 * 
	 * Output: [a, abc, abc, abcd]
	 * 
	 * abc
	 * 
	 * xyz, xz, xyzzy
	 * 
	 */

	public static ArrayList<String> solve(ArrayList<String> A) {

		ArrayList<String> a = new ArrayList<>();
		String l = findLargestString(A);
		LinkedList<Integer>[] f = new LinkedList[101];
		for (int j = 0; j < 101; j++) {
			f[j] = new LinkedList<>();
		}
		int i = 0;
		for (String s : A) {
			boolean isSubstring = l.contains(s);
			if (isSubstring == false) {
				return new ArrayList<>(Arrays.asList("NO"));
			}
			f[s.length()].add(i);
			i++;
		}

		for (LinkedList<Integer> ll : f) {
			for (Integer in : ll) {
				a.add(A.get(in));
			}
		}

		for (int k = 0; k < a.size() - 1; k++) {
			if (!a.get(k + 1).contains(a.get(k))) {
				return new ArrayList<>(Arrays.asList("NO"));
			}
		}

		return a;
	}

	private static String findLargestString(ArrayList<String> a) {
		int max = -1;
		int i = 0, maxIn = 0;
		for (String s : a) {
			if (a.get(i).length() > max) {
				max = a.get(i).length();
				maxIn = i;
			}
			i++;
		}
		return a.get(maxIn);
	}

}
