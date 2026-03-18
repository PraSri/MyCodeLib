package java8;

public class ConstructorMethodReference {
	
//	https://github.com/winterbe/java8-tutorial

	public static void applyReference() {

		Something something = new Something();

		FunctionalInterfaceConverter<String, String> converter = something::startsWith;
		String converted = converter.convert("JAva");
		System.out.println(converted);

	}

	public static void applyConstructorReference() {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Prakhar", "INDIA");
		System.out.println(person);
	}

	public static void main(String[] args) {
		applyReference();
		applyConstructorReference();
	}

}
