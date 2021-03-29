package java8;

@FunctionalInterface
public interface FunctionalInterfaceConverter<F, T> {
	T convert(F form);
}
