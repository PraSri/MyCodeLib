package deckofcards;

import java.util.ArrayList;
import java.util.List;

public class Hand<T extends Card> {

	private ArrayList<T> cards = new ArrayList<T>();

	public int score() {
		int sc = 0;
		for (T card : cards) {
			sc += card.getValue();
		}
		return sc;
	}

	public void addCard(T card) {
		cards.add(card);
	}

	public void addCards(List<T> newCards) {
		cards.addAll(newCards);
	}
}
