package java8;

import java.util.function.Consumer;

public class Consumers {

	public static void main(String[] args) {
		demoConsumers();
	}

	/***
	 * Consumers represent operations to be performed on a single input argument.
	 * 
	 ********/

	public static void demoConsumers() {
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getName());
		greeter.andThen(p -> System.out.println("..and then")).accept(new Person("Luke", "Skywalker"));
		greeter.andThen(p -> System.out.println("..and then"));
	}

}
