package QuickHelper;

public class CountOfNumbersDivisibleEitherOfValues {

	/****
	 * 
	 * Three positive nos. A,B,C.Find no. of pos integers less than D divisible by
	 * either a,b,c.
	 * 
	 * https://stackoverflow.com/questions/51464432/finding-the-count-of-numbers-divisible-by-either-of-3-values-how-can-it-be-more
	 *******/

	public static void main(String[] args) {

		System.out.println(naiveApproach(2, 3, 5, 10));
		System.out.println(optimizedApproach(2, 3, 5, 10));
		
		System.out.println(naiveApproach(2, 3, 7, 54));
		System.out.println(optimizedApproach(2, 3, 7, 54));

	}

	/******
	 * 
	 * 
	 * 
	 * *******/

	public static int naiveApproach(int a, int b, int c, int d) {

		int ct = 0;

		for (int i = 1; i < d; i++) {
			if (i % a == 0 || i % b == 0 || i % c == 0) {
				ct++;
			}
		}

		return ct;

	}

	public static int optimizedApproach(int a, int b, int c, int d) {

		// positive integers divisible by a less than d
		/*
		 * ex : a = 3 d= 11
		 * 
		 * positive integers divisible by 3 less than 11 are : 3 , 6 , 9 11/3 = 3.6=>3
		 * 
		 * 
		 **/
		int da = d / a;
		int db = d / b;
		int dc = d / c;

		int dab = d / LCM.getLcm(a, b);
		int dbc = d / LCM.getLcm(b, c);
		int dac = d / LCM.getLcm(a, c);

		int dabc = d / LCM.getLcm(LCM.getLcm(a, b), c);

		return da + db + dc - dab - dbc - dac + dabc-1;

	}

}
