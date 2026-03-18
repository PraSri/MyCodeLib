package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.BitSet;

public class GrayCode {

	/******
	 * 
	 * The gray code is a binary numeral system where two successive values differ
	 * in only one bit.
	 * 
	 * Given a non-negative integer A representing the total number of bits in the
	 * code, print the sequence of gray code.
	 * 
	 * A gray code sequence must begin with 0.
	 * 
	 * A = 2 , o/p : [0, 1, 3, 2]
	 * 
	 ******************/

	public static void main(String[] args) {

		BitSet bitSet = new BitSet(32);
		System.out.println(bitSet);
		bitSet.set(0);
		bitSet.set(4);
		System.out.println(bitSet);
		bitSet.flip(0);
		bitSet.flip(2);
		System.out.println(bitSet);

		System.out.println("&&&&&&&&&");

		System.out.println(grayCode_v2(2));
	}

	public static ArrayList<Integer> grayCode(int a) {

		ArrayList<Integer> res = new ArrayList<>();

		BitSet bits = new BitSet();

		backtrack(res, bits, a);

		return res;

	}

	private static void backtrack(ArrayList<Integer> res, BitSet bits, int a) {

		if (a == 0) {
			res.add(convertToInteger(bits));
		} else {
			backtrack(res, bits, a - 1);
			bits.flip(a - 1);
			backtrack(res, bits, a - 1);
		}

	}

	private static Integer convertToInteger(BitSet bits) {

		int value = 0;

		for (int i = 0; i < bits.length(); i++) {
			value += bits.get(i) ? (1L << i) : 0L;
		}

		return value;
	}

	/*****************************
	 * SCALER SOLUTION
	 **********************************************/

	/****
	 * 
	 * 
	 * The bruteforce method would be to start with 0, change any of the bits,
	 * keeping track of the numbers already constructed. When you run into a number
	 * where there is no way forward possible, you backtrack, and try to change some
	 * other bit instead. This might however be inefficient.
	 * 
	 * You need to come up with something smart this time.
	 * 
	 * You can notice that if a sequence is gray code, then its reverse is also a
	 * gray code. How can you use this to construct the solution?
	 * 
	 * 
	 ********************************************/

	/******
	 * Note the following :
	 * 
	 * Let G(n) represent a gray code of n bits. Note that reverse of G(n) is also a
	 * valid sequence. Let R(n) denote the reverse of G(n).
	 * 
	 * Note that we can construct G(n+1) as the following : 0G(n) 1R(n)
	 * 
	 * Where 0G(n) means all elements of G(n) prefixed with 0 bit and 1R(n) means
	 * all elements of R(n) prefixed with 1. Note that last element of G(n) and
	 * first element of R(n) is same. So the above sequence is valid.
	 * 
	 * Example G(2) to G(3) : 0 00 0 01 0 11 0 10 1 10 1 11 1 01 1 00
	 * 
	 * For example, when n=3, we can get the result based on n=2. 00,01,11,10 ->
	 * (000,001,011,010 ) (110,111,101,100). The middle two numbers only differ at
	 * their highest bit, while the rest numbers of part two are exactly symmetric
	 * of part one.
	 * 
	 ******************/

	public static ArrayList<Integer> grayCode_v2(int a) {

		ArrayList<Integer> res = new ArrayList<>();

		res.add(0);

		for (int i = 0; i < a; i++) {
			int curr = res.size();

			for (int j = curr - 1; j >= 0; j--) {
				res.add(res.get(j) + (1 << i));
			}
		}

		return res;

	}

}
