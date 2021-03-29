package java8;

public class FunctionInterfaceImpl {

	public static void applyFunctionalInterface() {
		FunctionalInterfaceConverter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123
	}

	public static void useMethodReference() {
		FunctionalInterfaceConverter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123
	}

	public static void main(String[] args) {
		applyFunctionalInterface();
		useMethodReference();
	}

}
