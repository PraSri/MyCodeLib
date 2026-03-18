package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExpressions {

	private static List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

	public static void oldWay() {

		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		});
	}

	public static void shorterJava8way() {
		Collections.sort(names, (String a, String b) -> {
			return b.compareTo(a);
		});
	}

	public static void shortestJava() {
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
	}

	public static void otherShortestWay() {
		names.sort((a, b) -> b.compareTo(a));
	}

	public static void main(String[] args) {
		oldWay();
		System.out.println(names);
	}

}
