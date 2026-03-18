package GoldmanSachs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {

	/***
	 * Given [3, 30, 34, 5, 9], the largest formed number is 9534330.
	 * 
	 ***/
	public static void main(String[] args) {

		System.out.println(largestNumber(new ArrayList<Integer>(Arrays.asList(3, 30, 34, 5, 9))));
		System.out.println(largestNumber(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0))));

	}

	public static String largestNumber(final List<Integer> A) {

		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String a, String b) {
				return (b + a).compareTo(a + b);
			}

		};

		List<String> l = new ArrayList<String>();
		for (int i : A) {

			l.add(String.valueOf(i));

		}

		Collections.sort(l, comparator);

		if (l.get(0).equals("0"))
			return "0";

		StringBuilder sb = new StringBuilder();

		for (String s : l) {
			sb.append(s);
		}

		return sb.toString();

	}

}
