package GoldmanSachs;

import java.util.HashMap;

public class FindRecurringSequenceInAFraction {

	/******
	 * Input : Numerator = 8, Denominator = 3 Output : Recurring sequence is 6
	 * Explanation : 8/3 = 2.66666666.......
	 ******/

	public static class LeetCodeSol {

		public String fractionToDecimal(int numerator, int denominator) {
			if (numerator == 0) {
				return "0";
			}
			StringBuilder res = new StringBuilder();
			// "+" or "-"
			res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
			long num = Math.abs((long) numerator);
			long den = Math.abs((long) denominator);

			// integral part
			res.append(num / den);
			num %= den;
			if (num == 0) {
				return res.toString();
			}

			// fractional part
			res.append(".");
			HashMap<Long, Integer> map = new HashMap<Long, Integer>();
			map.put(num, res.length());
			while (num != 0) {
				num *= 10;
				res.append(num / den);
				num %= den;
				if (map.containsKey(num)) {
					int index = map.get(num);
					res.insert(index, "(");
					res.append(")");
					break;
				} else {
					map.put(num, res.length());
				}
			}
			return res.toString();
		}

	}

	// This function returns repeating
	// sequence of a fraction. If
	// repeating sequence doesn't
	// exits, then returns empty String
	static String fractionToDecimal(int numr, int denr) {
		// Initialize result
		String res = "";

		// Create a map to store already
		// seen remainders. Remainder is
		// used as key and its position in
		// result is stored as value.
		// Note that we need position for
		// cases like 1/6. In this case,
		// the recurring sequence doesn't
		// start from first remainder.
		HashMap<Integer, Integer> mp = new HashMap<>();
		mp.clear();

		// Find first remainder
		int rem = numr % denr;

		// Keep finding remainder until
		// either remainder becomes 0 or repeats
		while ((rem != 0) && (!mp.containsKey(rem))) {
			// Store this remainder
			mp.put(rem, res.length());

			// Multiply remainder with 10
			rem = rem * 10;

			// Append rem / denr to result
			int res_part = rem / denr;
			res += String.valueOf(res_part);

			// Update remainder
			rem = rem % denr;
		}

		if (rem == 0)
			return "";
		else if (mp.containsKey(rem))
			return res.substring(mp.get(rem));

		return "";
	}

	// Driver code
	public static void main(String[] args) {
		int numr = 1, denr = 6;
		String res = fractionToDecimal(numr, denr);
		if (res == "")
			System.out.print("No recurring sequence");
		else
			System.out.print("Recurring sequence is " + res);
	}

}
