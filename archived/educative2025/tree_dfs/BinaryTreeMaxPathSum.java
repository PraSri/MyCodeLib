package tree_dfs;
import java.util.*;

public class BinaryTreeMaxPathSum {

        public int maxSum = Integer.MIN_VALUE;

        public int maxContrib(TreeNode<Integer> root) {
            if (root == null)
                return 0;

            // sum of the left and right subtree
            int maxLeft = this.maxContrib(root.left);
            int maxRight = this.maxContrib(root.right);

            int leftSubtree = 0;
            int rightSubtree = 0;

            //max sum on the left and right sub-trees of root
            if (maxLeft > 0)
                leftSubtree = maxLeft;

            if (maxRight > 0)
                rightSubtree = maxRight;

            // the value to start a new path where `node` is a highest node
            int valueNewpath = root.data + leftSubtree + rightSubtree;

            // update maxSum if it's better to start a new path
            this.maxSum = Math.max(this.maxSum, valueNewpath);

            // for recursion :
            // return the max contribution if continue the same path
            return root.data + Math.max(leftSubtree, rightSubtree);
        }

        public int maxPathSum(TreeNode<Integer> root) {
            this.maxSum = Integer.MIN_VALUE;
            this.maxContrib(root);
            return this.maxSum;
        }

        // Driver code
        public static void main(String args[]) {
            List<List<TreeNode<Integer>>> listOfTrees = Arrays.asList(
                    Arrays.asList(new TreeNode<Integer>(-8), new TreeNode<Integer>(2), new TreeNode<Integer>(17), new TreeNode<Integer>(1), new TreeNode<Integer>(4), new TreeNode<Integer>(19), new TreeNode<Integer>(5)),
                    Arrays.asList(new TreeNode<Integer>(7), new TreeNode<Integer>(3), new TreeNode<Integer>(4), new TreeNode<Integer>(-1), new TreeNode<Integer>(-3)),
                    Arrays.asList(new TreeNode<Integer>(-10), new TreeNode<Integer>(9), new TreeNode<Integer>(20), new TreeNode<Integer>(30), new TreeNode<Integer>(16), new TreeNode<Integer>(15), new TreeNode<Integer>(7)),
                    Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(2), new TreeNode<Integer>(3)),
                    Arrays.asList(new TreeNode<Integer>(0)),
                    Arrays.asList(new TreeNode<Integer>(-10), new TreeNode<Integer>(9), new TreeNode<Integer>(20), null, null, new TreeNode<Integer>(15), new TreeNode<Integer>(7)),
                    Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(-3), new TreeNode<Integer>(3),  new TreeNode<Integer>(5), null, null, new TreeNode<Integer>(-5))
            );

            List<BinaryTree<Integer>> inputTrees = new ArrayList<>();
            for (List<TreeNode<Integer>> ListOfNodes : listOfTrees) {
                BinaryTree<Integer> tree = new BinaryTree<>(ListOfNodes);
                inputTrees.add(tree);
            }

            BinaryTreeMaxPathSum sol = new BinaryTreeMaxPathSum();
            int i = 0;
            for (BinaryTree<Integer> tree : inputTrees) {
                System.out.println((i + 1) + ".\tBinary Tree");
                i++;
                System.out.println("\n\tMaximum path sum: " + sol.maxPathSum(tree.root));
                System.out.println(new String(new char[100]).replace('\0', '-'));
            }
        }
    }
