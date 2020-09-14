#include <bits/stdc++.h>
using namespace std;
bool isBalance(int a, int b) {
	if (abs(a - b) == 1 || a == b) {
		// cout << "balance ";
		return true;
	}
	// cout << "unbalanced ";
	return false;
}
int main() {
	int n, t, m;
	priority_queue<int> qmax;
	priority_queue<int, vector<int>, greater<int>> qmin;
	scanf("%d", &n);
	if (n != -1) {
		qmax.push(n);
	}
	while (scanf("%d", &n) != EOF) {
		if (isBalance(qmax.size(), qmin.size())) {
			if (qmax.size() > qmin.size()) {
				m = qmax.top();
			} else if (qmax.size() < qmin.size()) {
				m = qmin.top();
			} else if (qmax.size() == qmin.size()) {
				m = (qmax.top() + qmin.top()) / 2;
			}
		} else {
			if (qmax.size() > qmin.size()) {
				t = qmax.top();
				qmax.pop();
				qmin.push(t);
			} else if (qmax.size() < qmin.size()) {
				t = qmin.top();
				qmin.pop();
				qmax.push(t);
			}
			if (qmax.size() > qmin.size()) {
				m = qmax.top();
			} else if (qmax.size() < qmin.size()) {
				m = qmin.top();
			} else if (qmax.size() == qmin.size()) {
				m = (qmax.top() + qmin.top()) / 2;
			}
		}
		if (n != -1 && n != 0) {
			if (n > m) {
				qmin.push(n);
			} else {
				qmax.push(n);
			}
		}
		if (n == -1) {
			cout << m << endl;
			// cout << "QMAX" << " ";
			// while(!qmax.empty()){
			// 	cout << qmax.top() << " ";
			// 	qmax.pop();
			// }
			// cout << "QMIN" << " ";
			// while(!qmin.empty()){
			// 	cout << qmin.top() << " ";
			// 	qmin.pop();
			// }
			if (qmax.size() >= qmin.size()) {
				// cout << "Hello" << qmax.top();
				qmax.pop();
			} else if (qmax.size() < qmin.size()) {
				// cout << "World" << qmin.top();
				qmin.pop();
			}
		}
		if (n == 0) {
			cout << endl;
			while (!qmax.empty()) {
				qmax.pop();
			}
			while (!qmin.empty()) {
				qmin.pop();
			}
		}
	}
	return 0;
}
