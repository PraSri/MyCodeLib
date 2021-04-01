package java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class Annotations {

	public static void main(String[] args) {

		Hint hint = Person.class.getAnnotation(Hint.class);
		System.out.println(hint); // null
//
//		Hints hints1 = Person.class.getAnnotation(Hints.class);
//		System.out.println(hints1.value().length); // 2

		Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
		System.out.println(hints2.length); // 2
	}

	/**** Annotations in Java 8 are repeatable *****/

	/***
	 * Variant 1: Using the container annotation (old school)
	 ***/
	@Hints({ @Hint("hint1"), @Hint("hint2") })
	class Person {
	}

	/**
	 * Variant 2: Using repeatable annotations (new school)
	 ***/
	@Hint("hint1")
	@Hint("hint2")
	class Person2 {
	}

	@Target({ ElementType.TYPE_PARAMETER, ElementType.TYPE_USE })
	@interface MyAnnotation {
	}
}
