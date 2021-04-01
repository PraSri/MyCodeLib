package java8;

import java.util.Optional;

public class Optionals {

	public static void main(String[] args) {
		demoOptionals();
	}

	/****
	 * Optional is a simple container for a value which may be null or non-null.
	 * Think of a method which may return a non-null result but sometimes return
	 * nothing. Instead of returning null you return an Optional in Java 8.
	 *******/
	public static void demoOptionals() {

		Optional<String> optional = Optional.of("bam");
		optional.isPresent(); // true
		optional.get(); // "bam"
		optional.orElse("fallback"); // "bam"

		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"

		optional = Optional.empty();
		optional.ifPresentOrElse(s -> System.out.println("empty consumer"), () -> {
			System.out.println("empty action");
		});

	}

}
