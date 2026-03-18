/**
 *
 *
 * Given an integer array A of size N. Find the maximum length of a subarray Al, Al+1 ... Ar such that
 * gcd(A[l], A[l+1], ... A[r]) > 1.
 *
 ** NOTE: If no such subarray exists, return -1.
 **
 **
 ** **/
/******Solution Approach**********/

/*****
 *
 *
 * First think about how many distinct prime factors can a number < 106 has.

 There can be atmost 10 distinct prime factors of a number. So, if we reduce every element into it’s distinct prime factors.
  There will be atmost 10*N elements.

 Consider a 2-D list V where V[i] contain the indexes of elements having i as one of the prime factor.
  These indexes are in sorted order.

 So for every list V[i], gcd of elements at consecutive indexes in the list must be atleast i as all these elements
  have factor i in common.

 So, our problem reduces to find the maximum length of consecutive elements in the all the lists. This can be easily be done.
 *
 *
 * ********/
const int MAXN = 1000001;
int spf[MAXN];// smallest prime factor

void sieve() {
	spf[1] = 1;
	for (int i = 2; i < MAXN; i++)

		// marking smallest prime factor for every
		// number to be itself.
		spf[i] = i;

	// separately marking spf for every even
	// number as 2
	for (int i = 4; i < MAXN; i += 2)
		spf[i] = 2;

	for (int i = 3; i * i < MAXN; i++) {
		// checking if i is prime
		if (spf[i] == i) {
			// marking SPF for all numbers divisible by i
			for (int j = i * i; j < MAXN; j += i)

				// marking spf[j] if it is not
				// previously marked
				if (spf[j] == j)
					spf[j] = i;
		}
	}
}

vector<int> indexList[MAXN];

int Solution::solve(vector<int> &A) {

	int n = A.size();
	for (int i = 0; i < MAXN; i++)
		indexList[i].clear();

	// find smallest prime factor of each number
	sieve();

	int maxA = A[0];
	for (int i = 0; i < n; i++) {

		maxA = max(maxA, A[i]);
		while (A[i] > 1) {
			// add i to the list having p prime number as index.
			int p = spf[A[i]];
			indexList[p].push_back(i);

			// remove all factors of p from A[i].
			while (A[i] % p == 0) {
				A[i] /= p;
			}
		}
	}

	int ans = -1;
	for (int i = 2; i <= maxA; i++) {
		int cur = 1;

		// checking the numbers having i as a prime factor
		for (int j = 0; j < indexList[i].size(); j++) {
			// if consecutive numbers are present (difference between index is 1)
			if (j != 0 && indexList[i][j] - indexList[i][j - 1] == 1) {
				cur++;
			} else {
				cur = 1;
			}

			ans = max(ans, cur);
		}
	}

	return ans;
}
