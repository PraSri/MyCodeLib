package Graphs;

import java.util.LinkedList;

public class CycleInDirectedGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solve(int A, int[][] B) {

		boolean[] visited = new boolean[A + 1];
		boolean[] inStack = new boolean[A + 1];

		LinkedList<Integer>[] adjList = new LinkedList[A + 1];

		for (int i = 0; i <= A; i++) {
			adjList[i] = new LinkedList<Integer>();
		}

		for (int i = 1; i <= B.length; i++) {
			adjList[B[i - 1][0]].add(B[i - 1][1]);
		}

		boolean isCyclic = dfs(adjList, A, visited, inStack);

		return isCyclic ? 1 : 0;

	}

	private boolean dfs(LinkedList<Integer>[] adjList, int V, boolean[] visited, boolean[] inStack) {

		for (int i = 1; i <= V; i++) {
			if (dfsUtil(i, adjList, visited, inStack)) {
				return true;
			}
		}

		return false;
	}

	private boolean dfsUtil(int i, LinkedList<Integer>[] adjList, boolean[] visited, boolean[] inStack) {
		visited[i] = true;
		inStack[i] = true;
		for (int node : adjList[i]) {
			if (!visited[node] && dfsUtil(node, adjList, visited, inStack) || inStack[node]) {
				return true;
			}
		}
		inStack[i] = false;
		return false;
	}
}
