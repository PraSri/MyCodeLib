/*******
 *
 *
 * You are given an array A (containing only 0 and 1) as element of N length.
 *
 * Given L and R, you need to determine value of XOR of all elements from L to R
 * (both inclusive) and number of unset bits (0's) in the given range of the
 * array.
 *
 *
 *****/
vector<vector<int> > Solution::solve(vector<int> &A, vector<vector<int> > &B) {
	int a[A.size() + 1];    // Prefix sum array to get number of ones in a range
	a[0] = 0;
	for (int i = 1; i <= A.size(); i++) {
		a[i] = A[i - 1];
		a[i] += a[i - 1];
	}
	vector < vector<int> > ans;
	for (int i = 0; i < B.size(); i++) {
		vector<int> temp;
		int l, r, _xor = 0, ones;
		l = B[i][0];
		r = B[i][1];
		ones = a[r] - a[l - 1];  // number of ones in range l to r

		if (ones & 1) {
			_xor = 1;            // if number of 1 are odd then xor is 1
		}

		temp.push_back(_xor);
		temp.push_back(r - l + 1 - ones);
		ans.push_back(temp);
	}
	return ans;
}
