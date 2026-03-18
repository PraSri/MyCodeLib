#define inf 1000000000000
#define ll long long
using namespace std;
vector<int> Solution::solve(int A, int B, int C, vector<int> &D, vector<int> &E,
		vector<int> &F, vector<int> &G, vector<int> &H) {
	ll dp[205][205] = { };
	for (int i = 0; i < 205; i++) {
		for (int j = 0; j < 205; j++) {
			if (i != j)
				dp[i][j] = inf;
			else
				dp[i][j] = 0;
		}
	}
	for (int i = 0; i < B; i++) {
		int uu = D[i] - 1;
		int vv = E[i] - 1;
		dp[uu][vv] = min(dp[uu][vv], (ll) F[i]);
		dp[vv][uu] = min(dp[vv][uu], (ll) F[i]);
	}

	for (int k = 0; k < A; k++) {
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				if (dp[i][k] + dp[k][j] < dp[i][j])
					dp[i][j] = dp[i][k] + dp[k][j];
			}
		}
	}
	vector<int> answer;
	for (int i = 0; i < C; i++) {
		int aa = G[i] - 1;
		int bb = H[i] - 1;
		if (dp[aa][bb] == inf) {
			answer.push_back(-1);
		} else {
			answer.push_back((int) dp[aa][bb]);
		}
	}
	return answer;

}
