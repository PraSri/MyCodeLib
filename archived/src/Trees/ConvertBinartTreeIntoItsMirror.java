/**
	 * Method 1. Recursive Algorithm – Mirror(tree):
	 * 
	 * (1) Call Mirror for left-subtree i.e., Mirror(left-subtree)
	 *  (2) Call Mirror for right-subtree i.e., Mirror(right-subtree) 
	 *  (3) Swap left and right subtrees :
	 *  temp = left-subtree 
	 *  left-subtree = right-subtree 
	 *  right-subtree = temp
	 * 
	 * 
	 * 
	 */
  
	/**
	 * 
	 * 
	 * 
	 * Method 2. (Iterative)
	 * 
	 * 
	 * The idea is to do queue based level order traversal. While doing traversal,
	 * swap left and right children of every node.
	 * 
	 */
   
