package QuickHelper;

public class TotalNumberOfDivisors {

	public static void main(String[] args) {

		System.out.println(divCount(24));

	}

	public static int divCount(int n) {

		// get the prime numbers using sieve

		boolean[] primes = PrimeNumbers.sievePrime();

		int total = 1;

		for (int p = 2; p <= n; p++) {

			if (primes[p]) {

				// n = a^x * b^y * c^z
				// divisors = x+1 * y+1 * z+1

				int count = 0;

				if (n % p == 0) {

					while (n % p == 0) {
						n = n / p;
						count++;
					}

					total = total * (count + 1);

				}

			}

		}

		return total;

	}

}
