# Uno Game — Medium (Tekion R2)

### 1. Invariant Trigger
* **Signal:** State machine for turn-based card game with special effects, reverse directions, and penalty cards (Tekion R2 round) -> Object-Oriented Domain Model with Strategy Pattern.
* **Core Invariant:** Next player is computed circularly modulo $N$: $(current + direction + N) \pmod N$. Action cards (SKIP, REVERSE, DRAW) mutate game progression before turn handoff.

### 2. Failure Traps & Edge Cases
* Reshuffling draw pile: When draw pile empties, all cards EXCEPT top discard card must be collected, reshuffled, and restored to draw pile.
* Direction reversal with 2 players: In 2-player Uno, REVERSE acts identically to a SKIP card.
* Modular negative numbers: In Java, `-1 % 4 == -1`. Always add $+N$ before modulo: `(idx + dir + N) % N`.

### 3. Complexity & Variations
* **Time:** $O(1)$ per turn evaluation and action card application.
* **Space:** $O(C)$ fixed 108 cards across players, draw pile, and discard stack.
* **Google Follow-up:** "How to extend this to support network multiplayer with reconnection and state sync?" -> Event-driven architecture with Command Pattern and event-sourced state ledger.
