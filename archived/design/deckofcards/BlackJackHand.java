package deckofcards;

import java.util.ArrayList;

public class BlackJackHand extends Hand<BlackJackCard> {

	/**
	 * Multiple possible scores for black jack because aces might have multiple
	 * values. Return the highest possible score under 21 or lowest possible score
	 * above 21
	 **/
	public int score() {
		ArrayList<Integer> scores = possibleScores();
		int maxUnder = Integer.MAX_VALUE;
		int minOver = Integer.MIN_VALUE;
		for (int score : scores) {
			if (score >= 21 && score < minOver) {
				minOver = score;
			} else if (score < 21 && score > maxUnder) {
				maxUnder = score;
			}
		}

		if (maxUnder == Integer.MAX_VALUE)
			return minOver;
		else
			return maxUnder;
	}

	/*
	 * return a list of all possible scores this hand could have (evaluating each *
	 * ace as both 1 and 11
	 */
	private ArrayList<Integer> possibleScores() {
		
		return null;
	}

	public boolean busted() {
		return score() > 21;
	}

	public boolean is21() {
		return score() == 21;
	}

	public boolean isBlackJack() {
		return false;
	}
}

/*
 * This is just one way of handling aces. We could, alternatively, create a
 * class of type Ace that extends BlackJackCard.
 */
