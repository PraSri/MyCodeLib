package Graphs;

public class FloydWarshallAlgorithm {

	public static void main(String[] args) {

	}

	/*
	 * Given a matrix of integers A of size N x N, where A[i][j] represents the
	 * weight of directed edge from i to j (i ---> j).
	 * 
	 * If i == j, A[i][j] = 0, and if there is no directed edge from vertex i to
	 * vertex j, A[i][j] = -1.
	 * 
	 * Return a matrix B of size N x N where B[i][j] = shortest path from vertex i
	 * to vertex j.
	 * 
	 * If there is no possible path from vertex i to vertex j , B[i][j] = -1
	 * 
	 * Note: Rows are numbered from top to bottom and columns are numbered from left
	 * to right.
	 * 
	 * 
	 */
	public int[][] solve(int[][] A) {
		int n = A.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (A[i][j] == -1) {
					A[i][j] = 100000005;
				}
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (A[i][k] + A[k][j] < A[i][j]) {
						A[i][j] = A[i][k] + A[k][j];
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (A[i][j] == 100000005) {
					A[i][j] = -1;
				}
			}
		}
		return A;
	}
}
