package GameTheory;

public class LittlePonnyDivisibility {

	public static void main(String[] args) {
		System.out.println(solve(2));
	}

	/**
	 * On each move, a player can take out:
	 * 
	 * 1 or 2 stones if A is divisible by 3. 1 or 3 stones if (A - 1) is divisible
	 * by 3. 1, 2 or 3 stones if (A - 2) is divisible by 3.
	 * 
	 * 
	 * 1 2 3 4 5 6 a a b a a b
	 * 
	 * 
	 **/

	public static String solve(int A) {

		if (A % 3 == 0)
			return "bob";
		return "alice";

	}

}
