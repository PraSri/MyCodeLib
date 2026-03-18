package deckofcards;

public class BlackJackCard extends Card {

	private static final int faceValue = 10;

	public BlackJackCard(Suit suit, Rank rank) {
		super(suit, rank);
	}

	public int value() {
		if (isAce())
			return 1;
		else if (faceValue >= 11 && faceValue <= 13)
			return 10;
		else
			return faceValue;
	}

	public int minValue() {
		if (isAce())
			return 1;
		else
			return value();
	}

	public int maxValue() {
		if (isAce())
			return 11;
		else
			return value();
	}

	public boolean isAce() {
		return faceValue == 1;
	}

	public boolean isFaceCard() {
		return faceValue >= 11 && faceValue <= 13;
	}

}
