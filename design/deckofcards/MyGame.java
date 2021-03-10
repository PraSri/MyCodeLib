package deckofcards;

public class MyGame {

	public static void main(String[] args) {

		IDeck deck = new Deck();

//		deck.printDeck();
		deck.shuffle();
//		deck.printDeck();

		System.out.println(deck.dealsOneCard());
		
		
		deck = SingletonDeck.getInstance();
		deck.shuffle();
//		deck.printDeck();
		System.out.println(deck.dealsOneCard());

	}

}
