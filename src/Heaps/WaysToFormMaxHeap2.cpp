#include <iostream>
using namespace std;
//https://ide.codingblocks.com/s/336066
#define MAXN 1005

long long int dp[MAXN]; /* dp[i] = number of max heaps for i distinct integers */
long long int dp1[MAXN]; /*dp1[i]=number of max heaps for i-1 distinct integers */
long long int nck[MAXN][MAXN]; /* nck[i][j] = i choose j if i>=j else 0 */
int log_2[MAXN]; /* log_2[i] = int(log_2 base 2 of i) */
long long int MOD = 1000000007;
long long int choose(int, int);
int getL(int);
long long int getNumberOfMaxHeaps(int);
long long int getNumberOfMaxHeaps2(int);
long long int choose(int n, int k) {
	if (k > n)
		return 0;
	if (n <= 1)
		return 1;
	if (k == 0)
		return 1;

	if (nck[n][k] != -1)
		return nck[n][k];
	long long int answer = choose(n - 1, k - 1) + choose(n - 1, k);
	answer %= MOD;
	nck[n][k] = answer;
	return answer;
}

int getL(int n) {
	if (n == 1)
		return 0;

	int h = log_2[n];
	int p = n - ((1 << (h)) - 1);
	int m = (1 << h);
	if (p >= (m / 2))
		return (1 << (h)) - 1;
	else
		return (1 << (h)) - 1 - ((m / 2) - p);
}

long long int getNumberOfMaxHeaps(int n) {
	if (n <= 1)
		return 1;

	if (dp[n] != -1)
		return dp[n];

	int L = getL(n);
	long long int ans = (choose(n - 1, L) * getNumberOfMaxHeaps(L)) % MOD
			* (getNumberOfMaxHeaps(n - 1 - L));
	ans %= MOD;
	dp[n] = ans;
	return ans;
}
long long int getNumberOfMaxHeaps2(int n) {
	if (n < 2)
		return (long long int) 0;
	if (n < 4)
		return (long long int) 1;
	if (n == 4)
		return (long long int) 2;
	if (n == 5)
		return (long long int) 4;
	if (dp1[n] != 0)
		return dp1[n];
	int l = getL(n);
	int r = n - l - 1;
	long long int ans =
			(((choose(n - 3, l - 2) * getNumberOfMaxHeaps2(l)) % MOD)
					* getNumberOfMaxHeaps(r)) % MOD;
	ans = (ans
			+ (((choose(n - 3, r - 2) * getNumberOfMaxHeaps(l)) % MOD)
					* getNumberOfMaxHeaps2(r)) % MOD) % MOD;
	ans = (ans
			+ (((choose(n - 3, l - 1) * getNumberOfMaxHeaps(l)) % MOD)
					* getNumberOfMaxHeaps(r)) % MOD) % MOD;
	dp1[n] = ans;
	return ans;
}

int Solution::solve(int n, int arr[]) {
	for (int i = 0; i <= n; i++)
		dp[i] = -1;

	for (int i = 0; i <= n; i++)
		for (int j = 0; j <= n; j++)
			nck[i][j] = -1;

	int currlog_2Answer = -1;
	int currPower2 = 1;
	for (int i = 1; i <= n; i++) {
		if (currPower2 == i) {
			currlog_2Answer++;
			currPower2 *= 2;
		}
		log_2[i] = currlog_2Answer;
	}
	int max = -1;
	int min = 10000000;
	int maxcount = 0;
	int mincount = 0;
	for (int i = 0; i < n; i++) {
		if (arr[i] < min) {
			min = arr[i];
			mincount = 1;
		} else if (arr[i] == min) {
			mincount++;
		}
		if (arr[i] > max) {
			max = arr[i];
			maxcount = 1;
		} else if (arr[i] == max) {
			maxcount++;
		}
	}
	if (maxcount == 2)
		return (int) getNumberOfMaxHeaps(n);
	else
		return (int) getNumberOfMaxHeaps2(n);
}
