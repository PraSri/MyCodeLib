package QuickHelper;

public class GCD_one_liner {

	public static void main(String[] args) {
		System.out.println(gcd(98, 56));
	}

	/***** O(log(min(a,b))) *****/
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

}
