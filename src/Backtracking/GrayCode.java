package Backtracking;

import java.util.ArrayList;
import java.util.BitSet;

public class GrayCode {

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

		System.out.println(grayCode(0));
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

}
