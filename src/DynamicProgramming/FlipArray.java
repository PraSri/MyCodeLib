package DynamicProgramming;

public class FlipArray {

	public static void main(String[] args) {

	}

	/***
	 * Initially all the elements are positive in the arrayv and we need to flip
	 * some elements such that the total sum is as close to 0 as possible and
	 * atleast shoud be positive in every case.
	 * 
	 * Let the sum of all the given elements be S. This problem can be reduced to a
	 * Knapsack problem where we have to fill a Knapsack of capacity (S/2) as fully
	 * as possible and using the minimum no. of elements. We will fill the Knapsack
	 * with the given elements. Sign of all the elements which come into the
	 * knapsack will be flipped.
	 * 
	 * As sum of all the elements in the Knapsack will be as close to S/2 as
	 * possible, we are indirectly calculating minimum non-negative sum of all the
	 * elements after flipping the sign.
	 * 
	 * Coming to implementation of this idea,
	 * 
	 * knapsack should be filled upto the capacity using least number of elements
	 * 
	 * We can have a 2D DP matrix of a structure containing int items and int
	 * weight.
	 * 
	 * For construction of this DP we will run two nested for loops,
	 * 
	 * i -> 0 to N
	 * 
	 * j -> 0 to S/2
	 * 
	 * Base case is when i = 0 and j = 0 then dp[i][j] = {0,0}
	 * 
	 * Else we need to construct construct our DP similar to Knapsack problem.
	 * 
	 * And finally return dp[n][temp].items , which is the minimum number of elemnts
	 * required to be fliped.
	 * 
	 **/

	public class Node {
		public int items;
		public int weight;

		public Node(int items, int weight) {
			this.items = items;
			this.weight = weight;
		}

	}

	public int solve(final int[] A) {
		int n = A.length;

		Node[][] dp = new Node[105][10005];
		for (int i = 0; i < 105; i++) {
			for (int j = 0; j < 10005; j++) {
				dp[i][j] = new Node(0, 0);
			}
		}
		int sum = 0;
		for (int i : A) {
			sum += i;
		}

		int capacity = sum / 2;

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= capacity; j++) {

				if (i == 0 || j == 0) {
					dp[0][0].items = 0;
					dp[0][0].weight = 0;
				} else {

					int prevItems = dp[i - 1][j].items;
					int prevWeight = dp[i - 1][j].weight;

					if (j - A[i - 1] >= 0) {

						int currItems = dp[i - 1][j - A[i - 1]].items + 1;
						int currWeight = dp[i - 1][j - A[i - 1]].weight + A[i - 1];

						if (currWeight > prevWeight || (currWeight == prevWeight && currItems < prevItems)) {

							dp[i][j].items = currItems;
							dp[i][j].weight = currWeight;
						} else {
							dp[i][j].items = dp[i - 1][j].items;
							dp[i][j].weight = dp[i - 1][j].weight;
						}

					} else {
						dp[i][j].items = dp[i - 1][j].items;
						dp[i][j].weight = dp[i - 1][j].weight;
					}

				}

			}
		}

		return dp[n][capacity].items;

	}

}
