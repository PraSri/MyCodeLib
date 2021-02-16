package helperimpl;

import java.util.Arrays;

import helper.MyMath;

public class MyMathImpl implements MyMath {

	public static void main(String[] args) {

		int[] a = { 2, 4, 6, 1, 8, 9 };

		System.out.println(" MAX : " + new MyMathImpl().max(a));
		System.out.println(" MIN : " + new MyMathImpl().min(a));

		System.out.println(" MAX : " + MyMathImpl.MAX(a));
		System.out.println(" MIN : " + MyMathImpl.MIN(a));

	}

	private static MyMath math = new MyMathImpl();

	@Override
	public int max(int[] a) {
		return Arrays.stream(a).max().getAsInt();
	}

	@Override
	public int min(int[] a) {
		return Arrays.stream(a).min().getAsInt();
	}

	public static int MAX(int[] a) {
		return math.max(a);
	}

	public static int MIN(int[] a) {
		return math.min(a);
	}

}
