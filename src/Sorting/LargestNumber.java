package Sorting;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

	public static void main(String[] args) {
		System.out.println("z".compareTo("d"));
		System.out.println("a".compareTo("d"));
		System.out.println("303".compareTo("330"));
	}

	/*
	 * Given a array A of non negative integers, arrange them such that they form
	 * the largest number.
	 * 
	 * Note: The result may be very large, so you need to return a string instead of
	 * an integer. A = [3, 30, 34, 5, 9] 9534330
	 * https://leetcode.com/problems/largest-number/discuss/53158/My-Java-Solution-
	 * to-share
	 * 
	 */

	/** >>>>>>>>>>>>NEED ATTENTION<<<<<<<<<<<<<DO GRASP CONCEPT<<<<<<<<<<<<<<< */

	public String largestNumber(final int[] A) {
		// convert the int[] to String[]
		int n = A.length;
		String[] s = new String[n];
		for (int i = 0; i < n; i++) {
			s[i] = String.valueOf(A[i]);
		}

		Comparator<String> c = new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				String a = s1 + s2;
				String b = s2 + s1;
				return b.compareTo(a);
			}

		};

		Arrays.sort(s, c);
		if (s[0].charAt(0) == '0') {
			return "0";
		}

		StringBuilder sb = new StringBuilder();
		for (String x : s) {
			sb.append(x);
		}

		return sb.toString();
	}

}
