package java8;

import java.util.Objects;
import java.util.function.Predicate;

public class Predicates {

	/*****
	 * Predicates are boolean-valued functions of one argument. The interface
	 * contains various default methods for composing predicates to complex logical
	 * terms (and, or, negate)
	 ********/

	public static void demoPredicates() {
		Predicate<String> predicate = (s) -> s.length() > 0;

		predicate.test("foo"); // true
		System.out.println(predicate.test("foo"));
		predicate.negate().test("foo"); // false

		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		

		Predicate<String> isEmpty = String::isEmpty;
		System.out.println(isEmpty.test(""));
		System.out.println(predicate.test(""));
		System.out.println(isEmpty.and(predicate).test(""));
		Predicate<String> isNotEmpty = isEmpty.negate();
	}

	public static void main(String[] args) {

		demoPredicates();

	}

}
