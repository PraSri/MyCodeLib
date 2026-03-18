package QuickHelper;

public class Modulo_1000000007 {

	public static void main(String[] args) {

	}

	public static int mod = 1000000007;

	// for negative remainder
	static int mod(int a, int m) {
		return (a % m + m) % m;
	}

}
