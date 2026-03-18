package Arrays;

public class FizzBuzz {

	public String[] fizzBuzz(int A) {
		int n = A;
		String[] s = new String[n];
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				s[i - 1] = "FizzBuzz";
			} else if (i % 3 == 0 && i % 5 != 0) {
				s[i - 1] = "Fizz";
			} else if (i % 5 == 0 && i % 3 != 0) {
				s[i - 1] = "Buzz";
			} else {
				s[i - 1] = String.valueOf(i);
			}
		}
		return s;
	}

	/*
	 * Given a positive integer A, return an array of strings with all the integers
	 * from 1 to N. But for multiples of 3 the array should have “Fizz” instead of
	 * the number. For the multiples of 5, the array should have “Buzz” instead of
	 * the number. For numbers which are multiple of 3 and 5 both, the array should
	 * have "FizzBuzz" instead of the number.
	 */

}
