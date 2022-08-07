#define mod 1000000007
#define maxn 500005

int dp[maxn];

vector<int> child[maxn];       //child[i] --> children of node number i
vector<int> ggchild[maxn]; //ggchild[i] --> great grand children of node number i.

//parent is parent of node curr.
int dfs(vector<int> &value, int curr) {
	if (dp[curr] == -1) {
		//Take the current node
		int temp1 = value[curr - 1];

		for (int i = 0; i < ggchild[curr].size(); i++) {
			int v = ggchild[curr][i];
			temp1 = (temp1 + dfs(value, v)) % mod;
		}

		//Dont't take the current node
		int temp2 = 0;

		for (int i = 0; i < child[curr].size(); i++) {
			int v = child[curr][i];
			temp2 = (temp2 + dfs(value, v)) % mod;
		}

		dp[curr] = max(temp1, temp2);
	}

	return dp[curr];
}

int Solution::solve(vector<int> &A, vector<int> &B) {
	int n = A.size();

	//clear the arrays
	for (int i = 0; i <= n; i++) {
		child[i].clear();
		ggchild[i].clear();
		dp[i] = -1;
	}

	//Before: A[i] --> Parent of node number i+1
	//Now: P[i] --> Parent of node number i.
	vector<int> P(n + 1);
	P[0] = 0;
	for (int i = 0; i < A.size(); i++)
		P[i + 1] = A[i];

	//Fill the arrays
	for (int i = 1; i <= n; i++) {
		int par = P[i];                    //par --> parent of node number i
		int ggpar = P[P[P[i]]];  //ggpar --> great grand parent of node number i

		child[par].push_back(i);
		ggchild[ggpar].push_back(i);
	}

	//Calculate the answer using dfs
	return dfs(B, 1);
}
