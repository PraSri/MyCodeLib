package GoldmanSachs;

public class Count_pairs_in_an_array_whose_absolute_difference_is_divisible_by_K {

	// function to Count pairs in an array whose
	// absolute difference is divisible by k
	static void countPair(int arr[], int n, int k) {

		// intialize the count
		int cnt = 0;

		// making every element of arr in
		// range 0 to k - 1
		for (int i = 0; i < n; i++) {
			arr[i] = (arr[i] + k) % k;
		}

		// create an array hash[]
		int hash[] = new int[k];

		// store to count of element of arr
		// in hash[]
		for (int i = 0; i < n; i++) {
			hash[arr[i]]++;
		}

		// count the pair whose absolute
		// difference is divisible by k
		for (int i = 0; i < k; i++) {
			cnt += (hash[i] * (hash[i] - 1)) / 2;
		}

		// print the value of count
		System.out.print(cnt + "\n");
	}

	/****
	 * Given an array A[] and positive integer K, the task is to count total number
	 * of pairs in the array whose sum is divisible by K.
	 * 
	 *********/
	public static int countKdivPairs(int A[], int n, int K) {
		// Create a frequency array to count
		// occurrences of all remainders when
		// divided by K
		int freq[] = new int[K];

		// Count occurrences of all remainders
		for (int i = 0; i < n; i++)
			++freq[A[i] % K];

		// If both pairs are divisible by 'K'
		int sum = freq[0] * (freq[0] - 1) / 2;

		// count for all i and (k-i)
		// freq pairs
		for (int i = 1; i <= K / 2 && i != (K - i); i++)
			sum += freq[i] * freq[K - i];
		// If K is even
		if (K % 2 == 0)
			sum += (freq[K / 2] * (freq[K / 2] - 1) / 2);
		return sum;
	}

	// Driver Code
	public static void main(String[] args) {
		// input array
		int arr[] = { 1, 2, 3, 4 };
		int k = 2;

		// calculate the size of array
		int n = arr.length;
		countPair(arr, n, k);
	}

}
