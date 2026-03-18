package DynamicProgramming;

public class DungeonPrincess {

	public static void main(String[] args) {

	}

	private static final int biggest = Integer.MAX_VALUE;

	public int calculateMinimumHP(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		int[][] memo = new int[n][m];
		return helper(A, memo, 0, 0);
	}

	private int helper(int[][] a, int[][] memo, int i, int j) {

		if (i >= a.length || j >= a[0].length)
			return biggest;

		if (memo[i][j] > 0)
			return memo[i][j];

		int health = biggest;
		health = Math.min(health, helper(a, memo, i + 1, j));// downward
		health = Math.min(health, helper(a, memo, i, j + 1));// rightward
		health = health == biggest ? 1 : health;
		int need = health > a[i][j] ? (health - a[i][j]) : 1;
		memo[i][j] = need;
		return memo[i][j];
	}

}
