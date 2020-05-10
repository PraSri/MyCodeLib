package String;

public class IntegerToRoman {

	public static void main(String[] args) {
	}

	/*
	 * Given an integer A, convert it to a roman numeral, and return a string
	 * corresponding to its roman numeral version For the purpose of this question,
	 * https://projecteuler.net/about=roman_numerals has very detailed explanations.
	 * 
	 * I = 1 V = 5 X = 10 L = 50 C = 100 D = 500 M = 1000
	 * 
	 */

	public String intToRoman(int A) {

		String[] I = new String[] { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		String[] X = new String[] { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String[] C = new String[] { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String[] M = new String[] { "", "M", "MM", "MMM" };

		// 749 749%10 = 9 749%100=49/10=4

		String ans = M[(A / 1000)] + C[(A % 1000) / 100] + X[(A % 100) / 10] + I[A % 10];
		
		return ans;

	}

}
