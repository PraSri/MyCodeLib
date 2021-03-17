package singleton;

public class Doubleton {

	/******
	 * 
	 * 
	 * For any Java class, if we are allowed to create at-most two objects, such
	 * type of class is called as ‘Doubleton Class’. Make a doubleton which returns
	 * the first object in even call of getInstance() and second instance for the
	 * odd call.
	 * 
	 * 
	 *******/

	private static volatile Doubleton INSTANCE1;

	private static volatile Doubleton INSTANCE2;

	private static int call = 0;

	private Doubleton() {
	}

	public static Doubleton getInstance() {
		
		if (call++ % 2 == 0) {

			if (INSTANCE1 == null) {
		
				synchronized (Doubleton.class) {
				
					if (INSTANCE1 == null) {
						INSTANCE1 = new Doubleton();
					}

				}
			}
			
			return INSTANCE1;
			
		} else {
			
			if (INSTANCE2 == null) {
				
				synchronized (Doubleton.class) {
					
					if (INSTANCE2 == null) {
						INSTANCE2 = new Doubleton();
					}
				}
			}

			return INSTANCE2;
		}
	}

}
