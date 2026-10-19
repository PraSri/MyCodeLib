import java.util.*;

/**
 * Low-Level Design: Uno Game Engine (Tekion R2 Problem)
 *
 * Requirements:
 * 1. Card hierarchy (Number cards, Action cards: SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR).
 * 2. Color and value validation rules.
 * 3. Direction management (Clockwise, Counter-Clockwise).
 * 4. Extensible turn-based game loop.
 */
public class Solution {

    public enum Color { RED, BLUE, GREEN, YELLOW, WILD }
    public enum CardType { NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR }

    public static class Card {
        public final Color color;
        public final CardType type;
        public final int value; // 0-9 for NUMBER, -1 for action cards

        public Card(Color color, CardType type, int value) {
            this.color = color;
            this.type = type;
            this.value = value;
        }

        public boolean canPlayOn(Card topCard, Color activeColor) {
            if (this.color == Color.WILD || this.type == CardType.WILD || this.type == CardType.WILD_DRAW_FOUR) {
                return true;
            }
            if (this.color == activeColor) {
                return true;
            }
            return (this.type == CardType.NUMBER && topCard.type == CardType.NUMBER && this.value == topCard.value)
                    || (this.type != CardType.NUMBER && this.type == topCard.type);
        }

        @Override
        public String toString() {
            return (color == Color.WILD ? "" : color + " ") + type + (value >= 0 ? " " + value : "");
        }
    }

    public static class Player {
        public final String name;
        public final List<Card> hand = new ArrayList<>();

        public Player(String name) {
            this.name = name;
        }

        public Card chooseCard(Card topCard, Color activeColor) {
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).canPlayOn(topCard, activeColor)) {
                    return hand.remove(i);
                }
            }
            return null; // Must draw
        }
    }

    public static class UnoGame {
        private final List<Player> players;
        private final Deque<Card> drawPile = new ArrayDeque<>();
        private final Deque<Card> discardPile = new ArrayDeque<>();
        private int currentPlayerIndex = 0;
        private int direction = 1; // 1 = clockwise, -1 = counter-clockwise
        private Color activeColor;

        public UnoGame(List<String> playerNames) {
            this.players = new ArrayList<>();
            for (String name : playerNames) players.add(new Player(name));
            initializeDeck();
            dealInitialHands();
        }

        private void initializeDeck() {
            List<Card> cards = new ArrayList<>();
            for (Color c : new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW}) {
                cards.add(new Card(c, CardType.NUMBER, 0));
                for (int i = 1; i <= 9; i++) {
                    cards.add(new Card(c, CardType.NUMBER, i));
                    cards.add(new Card(c, CardType.NUMBER, i));
                }
                cards.add(new Card(c, CardType.SKIP, -1));
                cards.add(new Card(c, CardType.REVERSE, -1));
                cards.add(new Card(c, CardType.DRAW_TWO, -1));
            }
            for (int i = 0; i < 4; i++) {
                cards.add(new Card(Color.WILD, CardType.WILD, -1));
                cards.add(new Card(Color.WILD, CardType.WILD_DRAW_FOUR, -1));
            }
            Collections.shuffle(cards);
            drawPile.addAll(cards);
        }

        private void dealInitialHands() {
            for (Player p : players) {
                for (int i = 0; i < 7; i++) {
                    p.hand.add(drawCard());
                }
            }
            Card first = drawCard();
            while (first.color == Color.WILD) {
                drawPile.offerLast(first);
                first = drawCard();
            }
            discardPile.push(first);
            activeColor = first.color;
        }

        private Card drawCard() {
            if (drawPile.isEmpty()) {
                Card top = discardPile.pop();
                List<Card> reshuffle = new ArrayList<>(discardPile);
                discardPile.clear();
                discardPile.push(top);
                Collections.shuffle(reshuffle);
                drawPile.addAll(reshuffle);
            }
            return drawPile.pollFirst();
        }

        public void playTurn() {
            Player current = players.get(currentPlayerIndex);
            Card top = discardPile.peek();
            Card played = current.chooseCard(top, activeColor);

            if (played != null) {
                discardPile.push(played);
                activeColor = (played.color == Color.WILD) ? Color.RED : played.color; // Default wild choice
                System.out.println(current.name + " played " + played + " (Hand remaining: " + current.hand.size() + ")");

                if (current.hand.isEmpty()) {
                    System.out.println("Winner: " + current.name);
                    return;
                }
                applyCardAction(played);
            } else {
                Card drawn = drawCard();
                current.hand.add(drawn);
                System.out.println(current.name + " drew a card.");
            }

            advanceTurn();
        }

        private void applyCardAction(Card card) {
            int numPlayers = players.size();
            switch (card.type) {
                case SKIP -> advanceTurn();
                case REVERSE -> direction *= -1;
                case DRAW_TWO -> {
                    int nextPlayer = getNextPlayerIndex();
                    players.get(nextPlayer).hand.add(drawCard());
                    players.get(nextPlayer).hand.add(drawCard());
                    advanceTurn();
                }
                case WILD_DRAW_FOUR -> {
                    int nextPlayer = getNextPlayerIndex();
                    for (int i = 0; i < 4; i++) players.get(nextPlayer).hand.add(drawCard());
                    advanceTurn();
                }
                default -> {}
            }
        }

        private int getNextPlayerIndex() {
            int n = players.size();
            return (currentPlayerIndex + direction + n) % n;
        }

        private void advanceTurn() {
            currentPlayerIndex = getNextPlayerIndex();
        }
    }

    public static void main(String[] args) {
        UnoGame game = new UnoGame(Arrays.asList("Alice", "Bob", "Charlie"));
        for (int i = 0; i < 5; i++) {
            game.playTurn();
        }
    }
}
