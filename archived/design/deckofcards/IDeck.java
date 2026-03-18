package deckofcards;

public interface IDeck {

	public void shuffle();

	public Card dealsOneCard();

	public boolean hasCards();

	public void printDeck();

}
