package java8;

import java.util.function.Function;

public class Functions {

	/*****
	 * Functions accept one argument and produce a result. Default methods can be
	 * used to chain multiple functions together (compose, andThen).
	 ****************/

	public static void demoFunctions() {
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		String ans = backToString.apply("123"); // "123"
		System.out.println(ans);
	}
	
	public static void main(String[] args) {
		demoFunctions();
	}

}
