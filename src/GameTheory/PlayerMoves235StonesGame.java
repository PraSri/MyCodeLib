package GameTheory;

public class PlayerMoves235StonesGame {

	public static void main(String[] args) {

		for (String s : solve(new int[] { 1, 6, 4, 2, 3, 5, 17 })) {
			System.out.print(s + " , ");
		}

	}

	/***
	 * 
	 * 
	 * Two players called Alice and Bob are playing T independent games, each game
	 * starting with A[i] number of stones. In each game, Alice always plays first,
	 * and the two players move in alternating turns.
	 * 
	 * The game's rules are as follows:
	 * 
	 * In a single move, a player can remove either 2, 3, or 5 stones from the game
	 * board. If a player is unable to make a move, that player loses the game.
	 * 
	 * Given the starting number of stones, find and print the name of the winner.
	 * 
	 * Each player plays optimally, meaning they will not make a move that causes
	 * them to lose the game if a winning move exists.
	 * 
	 **/

	public static String[] solve(int[] A) {

		boolean optimizedSolutiom = true;

		int n = A.length;
		String[] res = new String[n];

		for (int i = 0; i < n; i++) {

			if (optimizedSolutiom) {
				res[i] = getWinnerOptSol(A[i]);
			} else
				res[i] = getWinner(A[i]);

		}

		return res;

	}

	private static String getWinnerOptSol(int n) {
		if (n % 7 == 0 || n % 7 == 1) {
			return "Bob";
		}
		return "Alice";
	}

	private static String getWinner(int n) {

		if (n == 0 || n == 1)
			return "Bob";
		if (n >= 2 && n <= 6) {
			return "Alice";
		}
		if (n == 7) {
			return "Bob";
		}
		boolean[] dp = new boolean[n + 1];

		dp[0] = false;
		dp[1] = false;
		dp[2] = true;
		dp[3] = true;
		dp[4] = true;
		dp[5] = true;
		dp[6] = true;
		dp[7] = false;

		for (int i = 8; i <= n; i++) {
			dp[i] = (dp[i - 2] & dp[i - 3] & dp[i - 5]) ^ true;
		}

		return dp[n] ? "Alice" : "Bob";
	}

}
