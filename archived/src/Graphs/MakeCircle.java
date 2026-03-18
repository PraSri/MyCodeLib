package Graphs;

import java.util.LinkedList;

public class MakeCircle {

	public static void main(String[] args) {

	}

	public int solve(String[] A) {

		LinkedList<Integer>[] adj = new LinkedList[26];

		for (int i = 0; i < 26; i++) {
			adj[i] = new LinkedList<>();
		}

		boolean[] visited = new boolean[26];

		int[] in = new int[26];
		int[] out = new int[26];

		for (int i = 0; i < A.length; i++) {
			int f = A[i].charAt(0) - 'a';
			int l = A[i].charAt(A[i].length() - 1) - 'a';

			visited[f] = true;
			visited[l] = true;

			in[l]++;
			out[f]++;

			adj[f].add(l);
		}

		for (int i = 0; i < 26; i++) {
			if (in[i] != out[i]) {
				return 0;
			}
		}

		return isConnected(adj, visited, A[0].charAt(0) - 'a');
	}

	private int isConnected(LinkedList<Integer>[] adj, boolean[] marked, int s) {
		boolean[] visited = new boolean[26];
		dfs(adj, visited, s);
		for (int i = 0; i < 26; i++) {
			if (marked[i] && !visited[i]) {
				return 0;
			}
		}

		return 1;

	}

	private void dfs(LinkedList<Integer>[] adj, boolean[] visited, int s) {

		visited[s] = true;

		for (int l : adj[s]) {
			if (!visited[l])
				dfs(adj, visited, l);
		}

	}

}
