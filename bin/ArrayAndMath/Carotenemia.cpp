int Solution::solve(vector<int> &A, int B) {
	int n = A.size();
	int x = B;
	int sum = 0;
	int ans;
	for (int i = 0; i < n; i++) {
		sum = sum + A[i];
		if (sum >= B) {
			ans = i;
			break;
		}
	}
	if (sum < B) {
		return -1;
	}
	return ans;
}
/*
 *
 *
 * You are given an Array of boxes A,
 * where each box consists of oranges. You really love oranges, and you want to eat atleast B oranges.
 *  You start from the 0th index of the array, and keep eating oranges until you eat B oranges.
 *  If oranges from a box at ith index get depleted, you start eating from the (i+1)th box.
 * Determine index of the box where you finish
 * eating B number of oranges. If you don't eat B oranges even after eating from all the boxes, return -1.
 *
 *
 *
 *
 *
 *
 * */
