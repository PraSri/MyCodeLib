package Trees;

import java.util.HashMap;
import java.util.Map;

public class MaximumDistinctNodesInRootToLeafPath {

	/*****
	 * https://www.geeksforgeeks.org/maximum-distinct-nodes-in-a-root-to-leaf-path/
	 ***/
	public static void main(String[] args) {

	}
	
	
	
	public int largestUniquePath(TreeNode node) {
		if(node==null)
			return 0;
		Map<Integer, Integer> map  = new HashMap<Integer, Integer>();
		
		return helper(node,map);
	}



	private int helper(TreeNode node, Map<Integer, Integer> map) {
		
		if(node == null) {
			map.size();
		}
		
		if(map.containsKey(node.val)) {
			map.put(node.val, map.get(node.val)+1);
		}else {
			map.put(node.val,1);
		}
		
		int maxPath = Math.max(helper(node.left, map), helper(node.right, map));
		
		if(map.containsKey(node.val)) {
			map.put(node.val,map.get(node.val)-1);
		}
		
		if(map.get(node.val)==0) {
			map.remove(node.val);
		}
		
		return maxPath;
	}

}
