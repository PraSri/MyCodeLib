package deckofcards;

public class Card implements ICard {

	private final Suit suit;
	private final Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Card other = (Card) obj;

		if (rank != other.rank) {
			return false;
		}

		if (suit != other.suit) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return rank.getDisplay() + " of " + suit;
	}

	public int getValue() {
		String value = rank.getDisplay();
		if (value.equals("A")) {
			return 1;
		} else if (value.equals("J")) {
			return 11;
		} else if (value.equals("Q")) {
			return 12;
		} else if (value.equals("K")) {
			return 13;
		}
		return Integer.parseInt(value);
	}

}
