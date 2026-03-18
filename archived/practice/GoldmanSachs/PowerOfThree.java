package GoldmanSachs;

public class PowerOfThree {

	public static void main(String[] args) {

	}

	public boolean isPowerOfThree(int n) {
		return (Math.log10(n) / Math.log10(3)) % 1 == 0;
	}

	public boolean isPowerOfThree_v2(int n) {
		if (n > 1)
			while (n % 3 == 0)
				n /= 3;
		return n == 1;
	}

	/****
	 * In Base 10, all powers of 10 start with the digit 1 and then are followed
	 * only by 0 (e.g. 10, 100, 1000). This is true for other bases and their
	 * respective powers. For instance in base 2, the representations of 10_210 2 ​
	 * , 100_2100 2 ​ and 1000_21000 2 ​ are 2_{10}2 10 ​ , 4_{10}4 10 ​ and 8_{10}8
	 * 10 ​ respectively. Therefore if we convert our number to base 3 and the
	 * representation is of the form 100...0, then the number is a power of 3.
	 *********/
	public boolean isPowerOfThree_v3(int n) {
		return Integer.toString(n, 3).matches("^10*$");
	}

}
