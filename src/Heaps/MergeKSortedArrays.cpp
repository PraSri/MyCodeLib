#include <bits/stdc++.h>
using namespace std;
// Merge k Sorted arrays
typedef pair<int, pair<int, int>> ppi;
vector<int> mergedArrays(vector<vector<int>> a) {
	vector<int> output;
	priority_queue<ppi, vector<ppi>, greater<ppi>> pq;
	for (int i = 0; i < a.size(); i++) {
		pq.push( { a[i][0], { i, 0 } });
	}
	while (!pq.empty()) {
		ppi curr = pq.top();
		pq.pop();
		output.push_back(curr.first);
		int i = curr.second.first;
		int j = curr.second.second;
		if (j + 1 < a[i].size()) {
			pq.push( { a[i][j + 1], { i, j + 1 } });
		}
	}
	return output;
}
int main() {
	vector<vector<int>> v = { { 2, 6, 12 }, { 4, 7 }, { 123, 145, 232, 822 } };
	vector<int> output = mergedArrays(v);
	for (auto a : output) {
		cout << a << " , ";
	}
	return 0;
}
