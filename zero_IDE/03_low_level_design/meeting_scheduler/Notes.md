# Meeting Scheduler — Medium (Microsoft LLD)

### 1. Invariant Trigger
* **Signal:** Room booking system with capacity constraints and interval conflict detection (Microsoft LLD round) -> Interval overlap condition + Thread-Safe Room Locking.
* **Core Invariant:** Two half-open intervals $[s_1, e_1)$ and $[s_2, e_2)$ overlap if and only if $s_1 < e_2 \land s_2 < e_1$. Consecutive meetings `[9, 10)` and `[10, 11)` do NOT overlap.

### 2. Failure Traps & Edge Cases
* Race condition between check and book: Calling `room.isAvailable()` followed separately by `room.addMeeting()` creates a classic Check-Then-Act race. `addMeeting()` must atomically re-verify under the room's lock.
* Room capacity matching: Choosing arbitrary available rooms wastes large conference rooms on 2-person meetings. Sort candidates by capacity difference.
* Invalid intervals: $start \ge end$ must throw `IllegalArgumentException`.

### 3. Complexity & Variations
* **Time:** $O(R \cdot M)$ where $R$ is total rooms and $M$ is meetings per room. Can be optimized to $O(R \log M)$ using Interval Trees / Segment Trees.
* **Space:** $O(R \cdot M)$ stored meetings in memory.
* **Google Follow-up:** "How to notify attendees across timezones and handle recurring meetings?" -> Store timestamps in UTC epoch millis and expand recurrence rules (RRULE) lazily.
