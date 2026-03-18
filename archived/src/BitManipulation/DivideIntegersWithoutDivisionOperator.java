package BitManipulation;

public class DivideIntegersWithoutDivisionOperator {

	public static void main(String[] args) {

	}

	/**********************
	 * 
	 * dividend = quotient * divisor + remainder
	 * 
	 ********************/

	// Time Complexity : O(a)
	// Space Complexity : O(1)
	// Function to divide a by b and
	// return floor value it
	static int divide_LinearTimeComplexity(int dividend, int divisor) {

		// Calculate sign of divisor i.e.,
		// sign will be negative only iff
		// either one of them is negative
		// otherwise it will be positive
		int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

		// Update both divisor and
		// dividend positive
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		// Initialize the quotient
		int quotient = 0;

		while (dividend >= divisor) {
			dividend -= divisor;
			++quotient;
		}

		return sign * quotient;
	}

	// Time Complexity : O(log(dividend))
	// Space Complexity : O(1)
	public int divide(int A, int B) {

		long n = A, m = B;

		// determine sign of the quotient
		int sign = 1;
		if (n < 0)
			sign *= -1;
		if (m < 0)
			sign *= -1;

		// remove sign of operands
		n = Math.abs(n);
		m = Math.abs(m);

		// q stores the quotient in computation
		long q = 0, t = 0;

		// test down from the highest bit
		// accumulate the tentative value for valid bits
		for (int i = 31; i >= 0; i--) {
			if (t + (m << i) <= n) {
				t += m << i;
				q |= (long) 1 << i;
			}

		}

		// assign back the sign
		if (sign < 0)
			q = -q;

		// check for overflow and return
		if (q > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else
			return (int) q;

	}

}
