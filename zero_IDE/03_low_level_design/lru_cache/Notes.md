# LRU Cache — Medium (Bitgo)

### 1. Invariant Trigger
* **Signal:** Fixed-capacity cache requiring $O(1)$ access and $O(1)$ eviction of the least recently used item (Bitgo round) -> Doubly Linked List + HashMap.
* **Core Invariant:** Sentinel nodes (`head` and `tail`) eliminate null-checks on boundary insertions/deletions. `head.next` is strictly Most Recently Used (MRU); `tail.prev` is strictly Least Recently Used (LRU).

### 2. Failure Traps & Edge Cases
* ReadLock vs WriteLock in `get()`: Because `get()` promotes the accessed node to the head of the DLL, it mutates pointers; using a ReadLock causes race conditions and pointer corruption. WriteLock or Segmented Locking is required.
* Storing key in `Node`: When evicting `tail.prev`, the DLL node must contain its `key` so that the entry can be removed from `map` in $O(1)$ without an expensive inverse search.
* Capacity update: Existing key update must update value and promote to MRU WITHOUT evicting another item.

### 3. Complexity & Variations
* **Time:** $O(1)$ for both `get` and `put`.
* **Space:** $O(\text{capacity})$ for map and DLL nodes.
* **Google Follow-up:** "How to scale this across 100+ concurrent worker threads?" -> Striped / Sharded LRU Cache (partition keys across $N$ cache segments based on hash code) or ConcurrentLinkedHashMap.
