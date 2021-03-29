package java8;

@FunctionalInterface
public interface PersonFactory<P extends Person> {
	
	P create(String name , String address);

}
