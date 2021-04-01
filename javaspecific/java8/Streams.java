package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Streams {

	private static List<String> stringCollection = new ArrayList<>();

	public static void main(String[] args) {
		demoStreams();
		demoFilter();
		demoFilterForOddElementsInListOfIntegers();
		demoSorted();
		demoMap();
		demoMatch();
		demoCount();
		demoReduce();
	}

	/*****
	 * A java.util.Stream represents a sequence of elements on which one or more
	 * operations can be performed. Stream operations are either intermediate or
	 * terminal. While terminal operations return a result of a certain type,
	 * intermediate operations return the stream itself so you can chain multiple
	 * method calls in a row. Streams are created on a source, e.g. a
	 * java.util.Collection like lists or sets (maps are not supported). Stream
	 * operations can either be executed sequentially or parallely.
	 *************/
	public static void demoStreams() {

		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		System.out.println(stringCollection.stream().count());

	}

	/**
	 * Filter accepts a predicate to filter all elements of the stream. This
	 * operation is intermediate which enables us to call another stream operation
	 * (forEach) on the result. ForEach accepts a consumer to be executed for each
	 * element in the filtered stream. ForEach is a terminal operation. It's void,
	 * so we cannot call another stream operation.
	 ********/

	public static void demoFilter() {
		stringCollection.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
	}

	public static void demoFilterForOddElementsInListOfIntegers() {
		List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7);
		integers.stream().filter(i -> i % 2 != 0).forEach(System.out::println);
	}

	/*****
	 * Sorted is an intermediate operation which returns a sorted view of the
	 * stream. The elements are sorted in natural order unless you pass a custom
	 * Comparator.
	 * 
	 ******/

	/*****
	 * Keep in mind that sorted does only create a sorted view of the stream without
	 * manipulating the ordering of the backed collection. The ordering of
	 * stringCollection is untouched:
	 *******/

	public static void demoSorted() {
		stringCollection.stream().sorted().filter(s -> s.startsWith("a")).forEach(System.out::println);
		System.out.println(stringCollection);
	}

	/*****
	 * The intermediate operation map converts each element into another object via
	 * the given function. The following example converts each string into an
	 * upper-cased string. But you can also use map to transform each object into
	 * another type. The generic type of the resulting stream depends on the generic
	 * type of the function you pass to map
	 ********/

	public static void demoMap() {
		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);
	}

	public static void demoMatch() {
		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ); // true
	}

	public static void demoCount() {

		long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

		System.out.println(startsWithB); // 3

	}

	public static void demoReduce() {
//		This terminal operation performs a reduction on the elements of the stream with the given function. 
//		The result is an Optional holding the reduced value.

		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

	}

}
