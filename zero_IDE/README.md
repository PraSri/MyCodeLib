# zero_IDE — Cold-Coding & Active Recall DSA Engine

> **"If you can't write it on a whiteboard or a plain text editor without auto-complete, you don't truly own the pattern."**

`zero_IDE` is a disciplined, interview-ready training structure designed for Google L5 / FAANG high-bar technical loops. It eliminates reliance on IDE code completion, inline diagnostics, and passive solution browsing, enforcing cold first-pass execution and structured pattern debriefs.

---

## 1. Core Architecture

```text
zero_IDE/
├── .gitignore
├── README.md                      # Architecture, philosophy & cold-coding workflow
├── PROGRESS.md                    # Cold-run tracking & first-pass pass-rates
│
├── templates/                     # Standalone, memorized Java templates (< 3 min recall)
│   ├── BinarySearchTemplates.java # Integer vs float BS, boundary patterns
│   ├── MonotonicDeque.java        # Sliding window maximum idiom
│   ├── MonotonicStack.java        # Next greater/histogram idiom
│   ├── UnionFind.java             # Path compression + rank template
│   ├── KahnTopologicalSort.java   # In-degree BFS skeleton
│   └── DijkstraShortestPath.java  # Stale-node skip PriorityQueue template
│
├── 01_google_gaps/                # Google high-frequency blind spots
│   ├── topological_sort/          # Course Schedule II, Alien Dictionary
│   ├── dijkstra/                  # Network Delay Time, Cheapest Flights K Stops
│   ├── intervals/                 # Merge Intervals, Insert Interval, Meeting Rooms II
│   ├── bit_manipulation/          # Counting Bits, Reverse Bits
│   └── advanced_dp/               # Coin Change II, Partition Equal Subset Sum
│
├── 02_core_patterns/              # NeetCode 150 grouped by algorithmic invariant
│   ├── 01_sliding_window/         # Sliding Window Max, Min Window Substring, Longest Repeating Char
│   ├── 02_two_pointers/           # 3Sum, Trapping Rain Water
│   ├── 03_monotonic_stack/        # Largest Rectangle in Histogram, Daily Temperatures
│   ├── 04_binary_search_answer/   # Koko Eating Bananas, Split Array Largest Sum, Min Time Repair Cars
│   ├── 05_trees_and_tries/        # Path Sum II, Most Profitable Path, Word Search II
│   ├── 06_graphs_and_bfs/         # Bus Routes, Number of Islands II, Distinct Islands
│   ├── 07_dynamic_programming/    # Regex Matching, Longest Increasing Subseq, Edit Distance
│   └── 08_backtracking/           # Generate Parentheses, N-Queens
│
├── 03_low_level_design/           # Machine coding & concurrency rounds
│   ├── lru_cache/                 # Bitgo problem (Node + Map + DLL, thread-safe)
│   ├── rate_limiter/              # Grey Orange (Token Bucket & Sliding Log)
│   ├── meeting_scheduler/         # Microsoft LLD (Interval conflict & room allocation)
│   ├── uno_game/                  # Tekion R2 (Card/Deck/Turn engine)
│   └── ad_server/                 # PhonePe R1 (Targeting, Budget capping & Auction engine)
│
└── 04_scratchpad/                 # Git-ignored local sandbox for cold testing
    └── Scratch.java               # Blank file for unassisted 20-min timed coding
```

---

## 2. The Standardized Problem Structure

Every problem directory contains exactly two files:
1. **`Solution.java`**: Fully implemented, self-contained, production-grade reference implementation in modern Java. Zero external boilerplate; fully compilable.
2. **`Notes.md`**: A concise, 3-part card adhering to the standardized layout:
   - **1. Invariant Trigger:** Signal trigger + Core mathematical/algorithmic invariant.
   - **2. Failure Traps & Edge Cases:** Common bugs, comparator overflows, boundary checks.
   - **3. Complexity & Variations:** Time & Space bounds + Google L5/Staff follow-up questions.

---

## 3. The 20-Minute Cold-Coding Routine

1. **Pick Target:** Select a problem from [`PROGRESS.md`](PROGRESS.md).
2. **Setup Sandbox:** Open `04_scratchpad/Scratch.java`. Do **NOT** open `Solution.java` or `Notes.md`.
3. **Timed Run (20 Mins):**
   - Write out the solution cold.
   - State invariants, edge cases, and run dry tests against corner cases.
4. **Debrief:**
   - Compare with `Solution.java`.
   - Read `Notes.md` to review missed traps or follow-ups.
5. **Log Metrics:** Record Target Time, Actual Time, First Run OK, and Core Mistake in [`PROGRESS.md`](PROGRESS.md).
