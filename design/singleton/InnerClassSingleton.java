package singleton;

public class InnerClassSingleton {

	private InnerClassSingleton() {
	}

	private static class InnerSingleton {
		private static final InnerClassSingleton instance = new InnerClassSingleton();
	}

	public static InnerClassSingleton getInstance() {
		return InnerSingleton.instance;
	}

}
