package java8;

import java.util.function.Supplier;

public class Suppliers {

	/****
	 * 
	 * Suppliers produce a result of a given generic type. Unlike Functions,
	 * Suppliers don't accept arguments.
	 * 
	 ************/

	public static void demoSuppliers() {
		Supplier<Person> personSupplier = Person::new;
		Person person = personSupplier.get(); // new Person
		System.out.println(person);

	}
	
	public static void main(String[] args) {
		demoSuppliers();
	}

}
