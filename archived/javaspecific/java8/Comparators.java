package java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comparators {

	public static void demoComparator() {
		Comparator<Person> comparator = (p1, p2) -> p1.getName().compareTo(p2.getName());
		Person p1 = new Person("Ab", "Doe");
		Person p2 = new Person("Aa", "Wonderland");
		int x = comparator.compare(p1, p2); // > 0
		int y = comparator.reversed().compare(p1, p2); // < 0
		System.out.println(x + " -> " + y);
		List<Person> l = new ArrayList<Person>();
		l.add(p1);
		l.add(p2);
		Collections.sort(l, comparator.reversed());
		System.out.println(l);
	}

	public static void main(String[] args) {
		demoComparator();
	}

}
