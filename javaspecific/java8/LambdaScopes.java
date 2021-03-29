package java8;

public class LambdaScopes {

	final static int x = 1;

	public static void demoLambdaScope() {

		final int y = 2;
		int z = 3;

		FunctionalInterfaceConverter<Integer, String> convertedInteger = (from) -> {
			System.out.println(x);
			return String.valueOf(from + x + y + z);
		};

		String ans = convertedInteger.convert(5);

//		z = 4; => changing z here will give compilation error & enforce z to be final

		System.out.println(ans);

	}

	public static void main(String[] args) {
		demoLambdaScope();
	}

}
