
vector<int> Solution::solve(vector<int> &A, vector<vector<int> > &B) {
	int n = A.size();
	//store the value for if we can do atmost 1 swap
	int val[n];
	memset(val, 0, sizeof(val));
	int max_val = 0;

	for (int i = n - 1; i >= 0; i--) {

		// get maximum value from array
		max_val = max(max_val, A[i]);

		// sieve
		for (int j = 1; j * j <= (i + 1); j++) {

			// if j is divisor of i+1
			if ((i + 1) % j == 0) {

				val[j - 1] = max(val[j - 1], A[i]);

				val[i] = max(val[i], A[j - 1]);

				if ((i + 1) / j != j) {

					int temp = (i + 1) / j;

					val[temp - 1] = max(val[temp - 1], A[i]);

					val[i] = max(val[i], A[temp - 1]);

				}

			}
		}
	}
	int m = B.size();
	vector<int> ans;
	for (int i = 0; i < m; i++) {
		int idx = B[i][0];
		int k = B[i][1];
		if (k >= 2) {
			ans.push_back(max_val);
		} else if (k == 0) {
			ans.push_back(A[idx - 1]);
		} else {
			ans.push_back(val[idx - 1]);
		}
	}
	return ans;
}

