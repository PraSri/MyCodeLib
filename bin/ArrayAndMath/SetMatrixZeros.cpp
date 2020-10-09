//Given a matrix, A of size M x N of 0s and 1s. If an element is 0, set its entire row and column to 0.
//
//Note: This will be evaluated on the extra memory used. Try to minimize the space and time complexity.
//

/**
	 * 
	 * One brute force approach is that you can if u find any element 
	 * matrix[i][j] = 0 then you should mark its corresponding row and column as -1; after
	 * completing the traversal , once again iterate the matrix and change all -1 to 0.
	 * 
	 ********************************************************************************************** 
	 *
	 *
	 * One of the optimized solution is that : 
	 * first u check the 0th row for 0 and
	 * set the flag if present and break; 
	 * second check 0th column for 0 , set flag
	 * and break;
	 * 
	 * iterate the matrix from 1 to rowLength and 1 to colLength, if u find zero
	 * just mark its corresponding 0th row and 0th column as 0
	 * 
	 * Again iterate matrix , if its 0th row OR 0th column has 0 then mark that
	 * element as 0
	 * 
	 * At last , if flag is set for 0th row and 0th column , iterate for each row
	 * and column and fill zeroes.
	 * 
	 * 
	 */


void Solution::setZeroes(vector<vector<int> > &A) {
	// Do not write main() function.
	// Do not read input, instead use the arguments to the function.
	// Do not print the output, instead return values as specified
	// Still have a doubt. Checkout www.interviewbit.com/pages/sample_codes/ for more details

	int m = A.size();
	int n = A[0].size();
	int rf = 0, cf = 0;
	for (int i = 0; i < n; i++) {
		if (A[0][i] == 0) {
			rf = 1;
			break;
		}
	}
	for (int i = 0; i < m; i++) {
		if (A[i][0] == 0) {
			cf = 1;
			break;
		}
	}
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
			if (A[i][j] == 0) {
				A[i][0] = 0;
				A[0][j] = 0;
			}
		}
	}
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
			if (A[i][0] == 0 || A[0][j] == 0) {
				A[i][j] = 0;
			}
		}
	}
	if (rf == 1) {
		for (int i = 0; i < n; i++) {
			A[0][i] = 0;
		}
	}
	if (cf == 1) {
		for (int i = 0; i < m; i++) {
			A[i][0] = 0;
		}
	}
}
