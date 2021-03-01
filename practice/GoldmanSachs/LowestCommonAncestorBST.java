package GoldmanSachs;

import BinarySearchTrees.TreeNode;

public class LowestCommonAncestorBST {
	TreeNode root;

	/*
	 * Function to find LCA of n1 and n2. The function assumes that both n1 and n2
	 * are present in BST
	 */
	TreeNode lca(TreeNode node, int n1, int n2) {
		
		if (node == null)
			return null;

		// If both n1 and n2 are smaller than root, then LCA lies in left
		if (node.val > n1 && node.val > n2)
			return lca(node.left, n1, n2);

		// If both n1 and n2 are greater than root, then LCA lies in right
		if (node.val < n1 && node.val < n2)
			return lca(node.right, n1, n2);

		return node;
	}
	
	static TreeNode lcaIterative(TreeNode root, int n1, int n2)  
	{  
	    while (root != null)  
	    {  
	        // If both n1 and n2 are smaller  
	        // than root, then LCA lies in left  
	        if (root.val > n1 &&  
	            root.val > n2)  
	        root = root.left;  
	  
	        // If both n1 and n2 are greater  
	        // than root, then LCA lies in right  
	        else if (root.val < n1 &&  
	                 root.val < n2)  
	        root = root.right;  
	  
	        else break;  
	    }  
	    return root;  
	}  

}
