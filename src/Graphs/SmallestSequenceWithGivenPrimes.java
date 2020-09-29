package TestPackage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SmallestSequenceWithGivenPrimes {

	public static void main(String[] args) {

	}

	public int[] solve(int A, int B, int C, int D) {

		int[] res = new int[D];

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		Set<Integer> set = new HashSet<Integer>();

		pq.add(A);
		pq.add(B);
		pq.add(C);

		set.add(A);
		set.add(B);
		set.add(C);

		int[] primes = new int[3];

		primes[0] = A;
		primes[1] = B;
		primes[2] = C;

		Arrays.sort(primes);

		int i = 0;

		int lowest = 0;


		while (i<D) {

			int curr = pq.poll();

			if (curr > lowest) {
				res[i++] = curr;
				lowest = curr;
			}

			for (int p : primes) {
				int newCurr = curr * p;

				if (!set.contains(newCurr)) {
					set.add(newCurr);
					pq.add(newCurr);
				}
			}
		}

		return res;

	}

}
