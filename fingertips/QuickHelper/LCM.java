package QuickHelper;

public class LCM {

	public static int getLcm(int a, int b) {
		return (a * b) / GCD_one_liner.gcd(a, b);
	}

}
