package lld;

/**
 * PUB-SUB SYSTEM LLD — INTERVIEW NOTES
 * =====================================
 *
 * CORE DATA STRUCTURES & COMPLEXITIES
 * ------------------------------------
 * | Operation              | Data Structure               | Time       |
 * |------------------------|------------------------------|------------|
 * | Publish                | ArrayList (append)           | O(1) amortized |
 * | Route (key-based)      | hashCode % numPartitions     | O(1)       |
 * | Route (round-robin)    | AtomicInteger counter        | O(1)       |
 * | Poll (read from offset)| ArrayList.subList            | O(K) where K = new msgs |
 * | Commit offset          | AtomicInteger.set            | O(1)       |
 * | Register consumer      | Rebalance (modulo assign)    | O(P) where P = partitions |
 *
 *
 * CONCURRENCY MODEL
 * -----------------
 * 1. ReadWriteLock PER PARTITION:
 *    - Multiple consumers (from different groups) read simultaneously via readLock().
 *    - Only publish takes writeLock(). Maximizes read throughput.
 *    - Why not synchronized? It's a mutex — blocks all readers even when no writes.
 *
 * 2. AtomicInteger for offsets:
 *    - Each partition in a consumer group is consumed by exactly ONE consumer (assignment rule).
 *    - So no write contention on a single offset → AtomicInteger suffices.
 *
 * 3. ConcurrentHashMap for topics/groups:
 *    - Topic and group registries are accessed by multiple threads.
 *    - ConcurrentHashMap gives thread-safe reads without locking.
 *
 *
 * KEY DESIGN DECISIONS TO EXPLAIN
 * --------------------------------
 * 1. "Partition as append-only log"
 *    → Mirrors Kafka's architecture. Enables offset-based replay.
 *
 * 2. "Manual offset commit = at-least-once delivery"
 *    → poll() returns messages but does NOT move offset.
 *    → Consumer commits after processing. If crash before commit, re-reads on restart.
 *    → For exactly-once: need idempotent processing or transactional commits.
 *
 * 3. "Static partition assignment with rebalance on register"
 *    → Uses round-robin: partitionId % numConsumers == consumerIndex
 *    → Simple but effective. Mention Kafka's Range/RoundRobin/Sticky assignors.
 *
 * 4. "Consumer groups are independent"
 *    → Each group has its own offset map. Group A reading doesn't affect Group B.
 *    → This is the fundamental differentiator from a simple message queue.
 *
 *
 * FOLLOW-UP QUESTIONS & ANSWERS
 * ------------------------------
 *
 * Q: "How would you do dynamic rebalancing?"
 * A: Implement a RebalanceStrategy interface. On consumer join/leave,
 *    pause all consumers, reassign partitions, resume. Similar to Kafka's
 *    cooperative sticky assignor to minimize partition movement.
 *
 * Q: "Fixed Rate vs Fixed Delay for polling?"
 * A: Fixed Rate: poll every N ms regardless of processing time.
 *    Fixed Delay: wait N ms AFTER processing completes.
 *    Fixed Delay is safer (prevents pile-up if processing is slow).
 *
 * Q: "How to handle slow consumers / backpressure?"
 * A: Options:
 *    1. Bounded partition log + block on publish (like BlockingQueue)
 *    2. Drop oldest messages (retention policy)
 *    3. Alert/monitor lag (offset gap between latest and committed)
 *
 * Q: "How to make this persistent?"
 * A: Replace ArrayList with write-ahead log (WAL) or memory-mapped files.
 *    Store offsets in a compacted internal topic (like Kafka's __consumer_offsets).
 *
 * Q: "How to handle poison messages?"
 * A: Track retry count per message. After N failures, move to Dead Letter Queue (DLQ).
 *
 *
 * PATTERNS USED
 * -------------
 * | Pattern         | Where                     |
 * |-----------------|---------------------------|
 * | Pub-Sub         | Core architecture          |
 * | Strategy        | Routing (hash vs round-robin) |
 * | ReadWriteLock   | Per-partition concurrency   |
 * | Offset Log      | Enables replay, independent progress |
 * | Observer        | Consumer groups observe topics |
 *
 *
 * COMPARISON: PUB-SUB vs MESSAGE QUEUE
 * -------------------------------------
 * | Aspect          | Message Queue       | Pub-Sub (our design)    |
 * |-----------------|---------------------|-------------------------|
 * | Delivery        | One consumer gets msg | All groups get all msgs |
 * | After read      | Message deleted     | Message persisted (log) |
 * | Replay          | Not possible        | Possible via offset     |
 * | Use case        | Work distribution   | Event broadcasting      |
 */
public class PubSubNotes {
    // This file is for reference only. See PubSubSystemLLD.java for the code.
}
