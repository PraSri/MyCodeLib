package deckofcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.EnumSet;
import java.util.List;

public class SingletonDeck implements IDeck {

	private static final int CARDS_IN_FULL_DECK = 52;

	private static volatile SingletonDeck singletonDeck = null;

	private List<Card> cards = Collections.emptyList();

	private SingletonDeck() {
		cards = createNewDeck();
	}

	public static SingletonDeck getInstance() {
		if (singletonDeck == null)
			synchronized (SingletonDeck.class) {
				if (singletonDeck == null)
					singletonDeck = new SingletonDeck();
			}
		return singletonDeck;
	}

	@Override
	public void shuffle() {

		cards = doShuffle(createNewDeck());
		cards = doShuffle(cards);

	}

	@Override
	public Card dealsOneCard() {
		if (hasCards())
			return cards.remove(cards.size() - 1);
		throw new EmptyStackException();
	}

	@Override
	public boolean hasCards() {
		return !cards.isEmpty();
	}

	@Override
	public void printDeck() {
		for (Card card : cards) {
			System.out.println(card);
		}
	}

	private List<Card> createNewDeck() {

		List<Card> freshDeck = new ArrayList<Card>(CARDS_IN_FULL_DECK);

		for (Rank rank : EnumSet.allOf(Rank.class)) {

			for (Suit suit : EnumSet.allOf(Suit.class)) {

				freshDeck.add(new Card(suit, rank));

			}
		}

		return freshDeck;
	}

	private List<Card> doShuffle(List<Card> createNewDeck) {
		Collections.shuffle(createNewDeck);
		return createNewDeck;
	}
}
