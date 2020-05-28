/*
 * You have a newspaper which has A lines to read.
 *
 * You decided to start reading the 1st line from Monday. You are given a
 * schedule, B, where B[i] = number of lines you can read on that day.
 *
 * You are very strict in reading the newspaper and you will read as much as you
 * can every day.
 *
 * Determine and return, which day of the week you will read the last line of
 * the newspaper.
 *
 */
int Solution::solve(int A, vector<int> &B) {
	int n = B.size();
	int i = 0;
	int sum = 0;
	int ans;
	while (true) {
		sum = sum + B[i];
		if (sum >= A) {
			ans = i + 1;
			break;
		}
		i = (i + 1) % 7;
	}
	return ans;
}
