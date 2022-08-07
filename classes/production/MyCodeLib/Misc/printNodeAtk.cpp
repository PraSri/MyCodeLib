/*
 Company Tags   : Amazon, Citicorp, Flipkart, Goldman Sachs, Hike, Ola Cabs, Samsung, Walmart
 Leetcode Link  : https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 Two approaches : O(N) space   and O(1) space
 */

class Solution {
public:
	////////////////Approach-1 starts here////////////////
	void collectKDown(TreeNode *root, vector<int> &result, int K) {
		if (!root || K < 0)
			return;
		if (K == 0) {
			result.push_back(root->val);
			return;
		}
		collectKDown(root->left, result, K - 1);
		collectKDown(root->right, result, K - 1);
	}
	int collectKNodes(TreeNode *root, TreeNode *target, int K,
			vector<int> &result) {
		if (!root)
			return -1;
		if (root == target) {
			collectKDown(root, result, K);
			return 0;
		}

		int leftDist = collectKNodes(root->left, target, K, result);

		if (leftDist != -1) {
			if (leftDist + 1 == K) {
				result.push_back(root->val);
			} else {
				collectKDown(root->right, result, K - 2 - leftDist);
				//whye K-2-leftDist ?
				//Ans: root->right is 2 edges away from root->left. So, K-2
				//Also, target may be leftDist away from root->left, so that distance needs to be subtracted as well from K
			}
			return leftDist + 1;
			//why leftDist+1
			//Ans: Because, when it recurses back, the distance of node (where recursion goes back)  from target node is +1 more
		}

		int rightDist = collectKNodes(root->right, target, K, result);

		if (rightDist != -1) {
			if (rightDist + 1 == K) {
				result.push_back(root->val);
			} else {
				collectKDown(root->left, result, K - 2 - rightDist);
			}
			return rightDist + 1;
		}

		return -1;
	}
	////////////////Approach-1 ends here////////////////

	////////////////Approach-2 starts here////////////////
	unordered_map<TreeNode*, TreeNode*> parent;
	void collectKDistanceNodes(TreeNode *root, int K, vector<int> &result) {
		set<int> st;
		queue<TreeNode*> que;
		que.push(root);
		st.insert(root->val);
		while (!que.empty()) {
			int n = que.size();
			if (K == 0)
				break;
			while (n--) {
				TreeNode *curr = que.front();
				que.pop();

				if (curr->left && !st.count(curr->left->val)) {
					que.push(curr->left);
					st.insert(curr->left->val);
				}
				if (curr->right && !st.count(curr->right->val)) {
					que.push(curr->right);
					st.insert(curr->right->val);
				}

				if (parent.count(curr) && !st.count(parent[curr]->val)) {
					que.push(parent[curr]);
					st.insert(parent[curr]->val);
				}
			}
			K--;
		}
		while (!que.empty()) {
			result.push_back(que.front()->val);
			que.pop();
		}
	}
	void collectParent(TreeNode *root) {
		if (!root)
			return;

		if (root->left)
			parent[root->left] = root;
		collectParent(root->left);

		if (root->right)
			parent[root->right] = root;
		collectParent(root->right);
	}

	////////////////Approach-2 ends here////////////////
	vector<int> distanceK(TreeNode *root, TreeNode *target, int K) {
		vector<int> result;
		if (!root)
			return {};
		if (K == 0)
			return {target->val};

		//Approach-1
		collectKNodes(root, target, K, result);

		//Approach-2
		//collectParent(root);
		//collectKDistanceNodes(target, K, result);
		return result;
	}
};
