package Graphs;

import java.util.HashMap;
import java.util.Map;

public class CloneGraph {

	public static void main(String[] args) {

	}

	Map<Integer, UndirectedGraphNode> hm = new HashMap<>();

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		return dfs(node);
	}

	private UndirectedGraphNode dfs(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}

		if (hm.containsKey(node.label)) {
			return hm.get(node.label);
		}

		UndirectedGraphNode u = new UndirectedGraphNode(node.label);
		hm.put(u.label, u);
		for (UndirectedGraphNode neighbours : node.neighbors) {
			u.neighbors.add(dfs(neighbours));
		}

		return u;
	}

}
