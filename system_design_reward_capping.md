# System Design Interview: Reward Capping & Data Integrity

## Problem Statement
**Scenario:** You have onboarded multiple merchants where customers pay via a digital wallet and receive rewards. 
**Business Restriction:** A customer cannot receive more than **10 rewards per day**, regardless of the number of payments they make.

**Task:** Develop a narrative explaining why **Redis** is an unsuitable primary choice for this specific use case due to durability, eviction policies, and future auditing requirements. Defend the decision to use **Amazon DynamoDB** as the source of truth.

---

## 1. The Critique: Why Redis Fails the "Audit & Integrity" Test

In a high-stakes financial or rewards environment, Redis introduces three critical risks that can lead to business rule violations and data loss.

### A. The "Window of Vulnerability" (Durability)
Redis is primarily an in-memory data store. While it offers persistence mechanisms like **RDB (Point-in-time snapshots)** and **AOF (Append Only File)**, they come with trade-offs:
* **Data Loss Gap:** Even with `appendfsync everysec`, a crash can result in up to 1 second of data loss. In a high-concurrency payment environment, hundreds of "reward counts" could be lost.
* **Inconsistency:** If the counter rolls back from 10 to 8 due to a crash, a user could maliciously or accidentally claim 12 rewards, breaking the business constraint.

### B. The "Silent Failure" of Eviction Policies
Redis manages memory by evicting keys when it reaches its limit (e.g., `allkeys-lru` or `volatile-lru`).
* **The Risk:** If the reward counter for a specific user is evicted to make room for new data, the next payment request will result in a **cache miss**.
* **The Bug:** The application logic will see a null value, initialize a new counter at 0, and allow the user to start their reward journey over, easily bypassing the "10 per day" limit. This is a "silent failure" that leaves no trace.

### C. Lack of Historical Context for Auditing
Redis stores the **current state** (e.g., `user_123_rewards: 9`), but it does not natively store the **history of changes**.
* **Audit Requirement:** For financial compliance, we often need to prove *when* each of the 10 rewards was granted and for *which* transaction. 
* **The Limitation:** Once the day resets or the key expires, that evidence is gone unless an expensive sidecar logging process is implemented.

---

## 2. The Defense: Why DynamoDB is the Strategic Choice

As a Senior Backend Engineer, the priority shifts from raw speed to **data correctness** and **auditability**. DynamoDB provides the necessary guarantees.

### A. ACID Transactions & Conditional Writes
DynamoDB allows us to perform **Conditional Updates**, ensuring the "10 reward limit" is enforced atomically at the database level.
* **Implementation:** `UpdateItem` with a condition: `SET reward_count = reward_count + 1 WHERE reward_count < 10`
* **Benefit:** This prevents race conditions where two simultaneous payments might both read "9" and both increment to "10," effectively giving the user 11 rewards.

### B. Multi-AZ Durability
Unlike Redis's asynchronous persistence, DynamoDB writes are synchronously replicated across three Availability Zones (AZs) before a "Success" is returned to the application.
* **Reliability:** This ensures that once a reward is logged, it is physically impossible to lose that record due to a single node or even a data center failure.

### C. Native Auditing via DynamoDB Streams
DynamoDB Streams capture a time-ordered sequence of every item-level modification.
* **The Narrative:** We can pipe this stream into **Amazon S3** or **Kinesis Data Firehose**. 
* **The Result:** We get an immutable, permanent ledger of every reward event for future auditing without adding latency to the main payment flow.

---

## 3. Proposed Data Model (Senior Level Approach)

To ensure high performance and scalability, we utilize a **Composite Primary Key** strategy:

| Attribute | Value Example | Description |
| :--- | :--- | :--- |
| **Partition Key (PK)** | `USER#<UserId>` | Groups all user data together. |
| **Sort Key (SK)** | `DATE#2026-04-15` | Allows for daily resets without manual deletion. |
| **RewardCount** | `9` | The atomic counter attribute. |
| **LastUpdated** | `ISO-8601 Timestamp` | For tracking the latest transaction. |

### Conclusion
While Redis is excellent for transient caching, the **Reward Capping** problem is a matter of **Financial Integrity**. By choosing DynamoDB, we sacrifice sub-millisecond latency (moving to ~5-10ms) in exchange for **guaranteed enforcement of business rules** and a **built-in audit trail**, which are the primary requirements for any senior-led system architecture.
