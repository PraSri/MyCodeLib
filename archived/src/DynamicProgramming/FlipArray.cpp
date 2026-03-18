struct node {
	int items;
	int weight;
} dp[105][10005];

int Solution::solve(const vector<int> &A) {
	int n = A.size();
	int sum = 0;

	for (int i = 0; i < n; i++)
		sum += A[i];

	//knapsack algorithm for capacity sum/2
	int temp = sum / 2;

	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= temp; j++) {

			if (i == 0 || j == 0)
				dp[i][j] = { 0, 0 };

			else {
				int prev_items = dp[i - 1][j].items;
				int prev_weight = dp[i - 1][j].weight;

				if (j - A[i - 1] >= 0) {

					int curr_weight = dp[i - 1][j - A[i - 1]].weight + A[i - 1];
					int curr_items = dp[i - 1][j - A[i - 1]].items + 1;

					if ((curr_weight > prev_weight)
							|| ((curr_weight == prev_weight)
									&& (curr_items < prev_items))) {

						dp[i][j] = { curr_items, curr_weight };

					} else {

						dp[i][j] = dp[i - 1][j];

					}
				} else {

					dp[i][j] = dp[i - 1][j];

				}
			}
		}
	}

	return dp[n][temp].items;
}
