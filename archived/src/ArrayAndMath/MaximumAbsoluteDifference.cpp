int Solution::maxArr(vector<int> &A) {
	int n = A.size();
	int min1 = INT_MAX;
	int min2 = INT_MAX;
	int max1 = INT_MIN;
	int max2 = INT_MIN;
	for (int i = 0; i < n; i++) {
		min1 = min(min1, A[i] - i);
		min2 = min(min2, A[i] + i);
		max1 = max(max1, A[i] - i);
		max2 = max(max2, A[i] + i);
	}
	return max(max1 - min1, max2 - min2);
}
/*You are given an array of N integers, A1, A2, .... AN.
 *Return the maximum value of f(i, j) for all 1 ≤ i, j ≤ N. f(i, j) is defined as
 *Return  |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.*****/
/**
	 * 
	 * |A[i]-A[j]| + |i-j|
	 * remove modulus according to modulus function: (a[i]-i) + (j-a[j]) = (a[i]-i) - (a[j]-j)
	 * (a[i]+i)-(a[j]+j) or (a[i]-i) - (a[j]-j)
	 * For max value = MAX-MIN
	 * 
	 * 
	 * */
