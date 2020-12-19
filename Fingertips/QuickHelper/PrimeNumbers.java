package QuickHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeNumbers {

	public static void main(String[] args) {

	}
	
	public static boolean[] sievePrime() {
		boolean[] prime = new boolean[1000006];

		Arrays.fill(prime, true);

		for (int i = 2; i * i <= 1000005; i++) {
			if (prime[i]) {
				for (int j = i * i; j <= 1000005; j += i) {
					prime[j] = false;
				}
			}
		}

		prime[0] = false;
		prime[1] = false;

		return prime;
	}
	
	public static ArrayList<Integer> sievePrimeList() {
		boolean[] prime = new boolean[1000006];

		Arrays.fill(prime, true);

		ArrayList<Integer> primes = new ArrayList<>();

		for (int i = 2; i * i <= 1000005; i++) {
			if (prime[i]) {
				primes.add(i);
				for (int j = i * i; j <= 1000005; j += i) {
					prime[j] = false;
				}
			}
		}
		return primes;
	}

}
