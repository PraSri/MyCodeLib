package CompanyQuestions;

public class ConsecutiveNumberSum {

	public static void main(String[] args) {

	}

	/*
	 * Return number of ways we can write A as a sum of consecutive positive
	 * integers.
	 */
	/*
	 * NEED TO UNDERSTAND THE SOLUTION
	 * 
	 */
	public int solve(int A) {
		long num = A;
		long sumOfFirstIntegers = 3;
		long count = 0;
		for (long i = 2; sumOfFirstIntegers <= num; ++i) {
			// check if even or not
			if ((i % 2 == 0) ? (num % i == i / 2) : (num % i == 0)) {
				++count;
			}
			sumOfFirstIntegers += i + 1;
		}
		return (int) count + 1;
	}

}
