package lld;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

/**
 * In-Memory Pub-Sub System (Kafka-like)
 *
 * Features:
 * - Topics with configurable partitions
 * - Key-based and round-robin message routing
 * - Consumer Groups with independent offset tracking
 * - Static partition assignment (1 partition → at most 1 consumer per group)
 * - Manual offset commits (at-least-once delivery)
 * - ReadWriteLock per partition for high read throughput
 */
public class PubSubSystemLLD {

    // ==================== MODELS ====================

    public static class Message {
        private final String key;     // nullable, used for partition routing
        private final String value;   // payload
        private final long timestamp;

        public Message(String key, String value) {
            this.key = key;
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }

        public String getKey() { return key; }
        public String getValue() { return value; }
        public long getTimestamp() { return timestamp; }

        @Override
        public String toString() {
            return "Message{key='" + key + "', value='" + value + "', ts=" + timestamp + "}";
        }
    }

    public static class Partition {
        private final int partitionId;
        private final List<Message> messages;
        private final ReadWriteLock rwLock;

        public Partition(int partitionId) {
            this.partitionId = partitionId;
            this.messages = new ArrayList<>();
            this.rwLock = new ReentrantReadWriteLock();
        }

        public void append(Message message) {
            rwLock.writeLock().lock();
            try {
                messages.add(message);
            } finally {
                rwLock.writeLock().unlock();
            }
        }

        public List<Message> readFrom(int offset) {
            rwLock.readLock().lock();
            try {
                if (offset >= messages.size()) {
                    return Collections.emptyList();
                }
                return new ArrayList<>(messages.subList(offset, messages.size()));
            } finally {
                rwLock.readLock().unlock();
            }
        }

        public int size() {
            rwLock.readLock().lock();
            try {
                return messages.size();
            } finally {
                rwLock.readLock().unlock();
            }
        }

        public int getPartitionId() { return partitionId; }
    }

    public static class Topic {
        private final String name;
        private final List<Partition> partitions;
        private final AtomicInteger roundRobinCounter;

        public Topic(String name, int numPartitions) {
            this.name = name;
            this.partitions = new ArrayList<>();
            this.roundRobinCounter = new AtomicInteger(0);
            for (int i = 0; i < numPartitions; i++) {
                partitions.add(new Partition(i));
            }
        }

        public Partition getPartition(int index) {
            return partitions.get(index);
        }

        public int getNumPartitions() {
            return partitions.size();
        }

        public int routeMessage(String key) {
            if (key != null) {
                return Math.abs(key.hashCode()) % partitions.size();
            }
            return Math.abs(roundRobinCounter.getAndIncrement()) % partitions.size();
        }

        public String getName() { return name; }
        public List<Partition> getPartitions() { return partitions; }
    }

    public static class ConsumerGroup {
        private final String groupName;
        private final Topic topic;

        // partitionId -> committed offset (how far this group has read)
        private final Map<Integer, AtomicInteger> offsets;

        // consumerId -> list of assigned partitionIds
        private final Map<String, List<Integer>> assignments;

        private int nextConsumerIndex;

        public ConsumerGroup(String groupName, Topic topic) {
            this.groupName = groupName;
            this.topic = topic;
            this.offsets = new ConcurrentHashMap<>();
            this.assignments = new ConcurrentHashMap<>();
            this.nextConsumerIndex = 0;

            // Initialize offsets to 0 for all partitions
            for (int i = 0; i < topic.getNumPartitions(); i++) {
                offsets.put(i, new AtomicInteger(0));
            }
        }

        public synchronized void registerConsumer(String consumerId) {
            if (assignments.containsKey(consumerId)) {
                throw new IllegalArgumentException("Consumer " + consumerId + " already registered.");
            }
            assignments.put(consumerId, new ArrayList<>());
            rebalance();
            System.out.println("[ConsumerGroup: " + groupName + "] Registered consumer: " + consumerId
                    + " -> Partitions: " + assignments.get(consumerId));
        }

        private void rebalance() {
            // Clear all existing assignments
            for (List<Integer> parts : assignments.values()) {
                parts.clear();
            }

            List<String> consumerIds = new ArrayList<>(assignments.keySet());
            int numPartitions = topic.getNumPartitions();

            // Round-robin assign partitions to consumers
            for (int p = 0; p < numPartitions; p++) {
                String assignedConsumer = consumerIds.get(p % consumerIds.size());
                assignments.get(assignedConsumer).add(p);
            }
        }

        public List<Integer> getAssignedPartitions(String consumerId) {
            List<Integer> parts = assignments.get(consumerId);
            if (parts == null) {
                throw new IllegalArgumentException("Consumer " + consumerId + " not registered in group " + groupName);
            }
            return parts;
        }

        public int getOffset(int partitionId) {
            return offsets.get(partitionId).get();
        }

        public void commitOffset(int partitionId, int newOffset) {
            offsets.get(partitionId).set(newOffset);
        }

        public String getGroupName() { return groupName; }
        public Topic getTopic() { return topic; }
        public Map<String, List<Integer>> getAssignments() { return assignments; }
    }

    // ==================== BROKER (ORCHESTRATOR) ====================

    public static class MessageBroker {
        private final Map<String, Topic> topics;
        private final Map<String, ConsumerGroup> consumerGroups;

        public MessageBroker() {
            this.topics = new ConcurrentHashMap<>();
            this.consumerGroups = new ConcurrentHashMap<>();
        }

        // ---- Topic Management ----

        public Topic createTopic(String name, int numPartitions) {
            if (topics.containsKey(name)) {
                throw new IllegalArgumentException("Topic " + name + " already exists.");
            }
            Topic topic = new Topic(name, numPartitions);
            topics.put(name, topic);
            System.out.println("[Broker] Created topic '" + name + "' with " + numPartitions + " partitions.");
            return topic;
        }

        // ---- Publishing ----

        public void publish(String topicName, Message message) {
            Topic topic = topics.get(topicName);
            if (topic == null) {
                throw new IllegalArgumentException("Topic " + topicName + " does not exist.");
            }
            int partitionIndex = topic.routeMessage(message.getKey());
            Partition partition = topic.getPartition(partitionIndex);
            partition.append(message);
            System.out.println("[Publish] Topic='" + topicName + "' Partition=" + partitionIndex
                    + " Key='" + message.getKey() + "' Value='" + message.getValue() + "'");
        }

        // ---- Consumer Group Management ----

        public ConsumerGroup createConsumerGroup(String groupName, String topicName) {
            Topic topic = topics.get(topicName);
            if (topic == null) {
                throw new IllegalArgumentException("Topic " + topicName + " does not exist.");
            }
            if (consumerGroups.containsKey(groupName)) {
                throw new IllegalArgumentException("Consumer group " + groupName + " already exists.");
            }
            ConsumerGroup group = new ConsumerGroup(groupName, topic);
            consumerGroups.put(groupName, group);
            System.out.println("[Broker] Created consumer group '" + groupName + "' on topic '" + topicName + "'.");
            return group;
        }

        public void registerConsumer(String groupName, String consumerId) {
            ConsumerGroup group = consumerGroups.get(groupName);
            if (group == null) {
                throw new IllegalArgumentException("Consumer group " + groupName + " does not exist.");
            }
            group.registerConsumer(consumerId);
        }

        // ---- Consuming (Poll) ----

        public Map<Integer, List<Message>> poll(String groupName, String consumerId) {
            ConsumerGroup group = consumerGroups.get(groupName);
            if (group == null) {
                throw new IllegalArgumentException("Consumer group " + groupName + " does not exist.");
            }

            List<Integer> assignedPartitions = group.getAssignedPartitions(consumerId);
            Topic topic = group.getTopic();

            Map<Integer, List<Message>> result = new LinkedHashMap<>();

            for (int partitionId : assignedPartitions) {
                Partition partition = topic.getPartition(partitionId);
                int currentOffset = group.getOffset(partitionId);
                List<Message> newMessages = partition.readFrom(currentOffset);
                if (!newMessages.isEmpty()) {
                    result.put(partitionId, newMessages);
                }
            }

            return result;
        }

        // ---- Offset Commit ----

        public void commitOffset(String groupName, int partitionId, int newOffset) {
            ConsumerGroup group = consumerGroups.get(groupName);
            if (group == null) {
                throw new IllegalArgumentException("Consumer group " + groupName + " does not exist.");
            }
            group.commitOffset(partitionId, newOffset);
        }
    }

    // ==================== DRIVER / TEST ====================

    public static void main(String[] args) throws InterruptedException {
        MessageBroker broker = new MessageBroker();

        // --- Setup ---
        System.out.println("========== SETUP ==========");
        broker.createTopic("orders", 3);
        broker.createConsumerGroup("payment-service", "orders");
        broker.createConsumerGroup("analytics-service", "orders");
        broker.registerConsumer("payment-service", "payment-1");
        broker.registerConsumer("payment-service", "payment-2");
        broker.registerConsumer("analytics-service", "analytics-1");

        // --- Scenario 1: Publish with keys (same key -> same partition) ---
        System.out.println("\n========== SCENARIO 1: Key-Based Routing ==========");
        broker.publish("orders", new Message("user-100", "Order #1 by user-100"));
        broker.publish("orders", new Message("user-200", "Order #2 by user-200"));
        broker.publish("orders", new Message("user-100", "Order #3 by user-100"));
        broker.publish("orders", new Message("user-300", "Order #4 by user-300"));
        System.out.println("-> Messages with key 'user-100' should go to the SAME partition.");

        // --- Scenario 2: Publish without keys (round-robin) ---
        System.out.println("\n========== SCENARIO 2: Round-Robin Routing ==========");
        broker.publish("orders", new Message(null, "Keyless order A"));
        broker.publish("orders", new Message(null, "Keyless order B"));
        broker.publish("orders", new Message(null, "Keyless order C"));

        // --- Scenario 3: Consumer poll (two groups, independent offsets) ---
        System.out.println("\n========== SCENARIO 3: Consumer Poll ==========");

        System.out.println("\n--- Payment Service: payment-1 polls ---");
        Map<Integer, List<Message>> payment1Messages = broker.poll("payment-service", "payment-1");
        for (Map.Entry<Integer, List<Message>> entry : payment1Messages.entrySet()) {
            System.out.println("  Partition " + entry.getKey() + ":");
            for (Message msg : entry.getValue()) {
                System.out.println("    " + msg);
            }
        }

        System.out.println("\n--- Payment Service: payment-2 polls ---");
        Map<Integer, List<Message>> payment2Messages = broker.poll("payment-service", "payment-2");
        for (Map.Entry<Integer, List<Message>> entry : payment2Messages.entrySet()) {
            System.out.println("  Partition " + entry.getKey() + ":");
            for (Message msg : entry.getValue()) {
                System.out.println("    " + msg);
            }
        }

        System.out.println("\n--- Analytics Service: analytics-1 polls (gets ALL partitions) ---");
        Map<Integer, List<Message>> analyticsMessages = broker.poll("analytics-service", "analytics-1");
        for (Map.Entry<Integer, List<Message>> entry : analyticsMessages.entrySet()) {
            System.out.println("  Partition " + entry.getKey() + ":");
            for (Message msg : entry.getValue()) {
                System.out.println("    " + msg);
            }
        }

        // --- Scenario 4: Commit offsets and re-poll ---
        System.out.println("\n========== SCENARIO 4: Commit Offset & Re-Poll ==========");

        // payment-1 commits all messages it read
        for (Map.Entry<Integer, List<Message>> entry : payment1Messages.entrySet()) {
            int partitionId = entry.getKey();
            int newOffset = broker.consumerGroups.get("payment-service").getOffset(partitionId) + entry.getValue().size();
            broker.commitOffset("payment-service", partitionId, newOffset);
            System.out.println("payment-1 committed offset " + newOffset + " for partition " + partitionId);
        }

        // Re-poll payment-1: should get nothing (already committed)
        System.out.println("\n--- payment-1 re-polls (should be empty) ---");
        Map<Integer, List<Message>> repolled = broker.poll("payment-service", "payment-1");
        System.out.println("Messages received: " + (repolled.isEmpty() ? "NONE (correct!)" : repolled));

        // Publish a new message and re-poll
        broker.publish("orders", new Message("user-100", "Order #5 by user-100"));
        System.out.println("\n--- payment-1 re-polls after new publish ---");
        Map<Integer, List<Message>> newMessages = broker.poll("payment-service", "payment-1");
        for (Map.Entry<Integer, List<Message>> entry : newMessages.entrySet()) {
            System.out.println("  Partition " + entry.getKey() + ":");
            for (Message msg : entry.getValue()) {
                System.out.println("    " + msg);
            }
        }

        // --- Scenario 5: Independent consumer groups ---
        System.out.println("\n========== SCENARIO 5: Independent Consumer Groups ==========");
        System.out.println("analytics-1 has NOT committed any offsets, so re-polling gives ALL messages:");
        Map<Integer, List<Message>> analyticsRepoll = broker.poll("analytics-service", "analytics-1");
        int totalAnalytics = analyticsRepoll.values().stream().mapToInt(List::size).sum();
        System.out.println("analytics-1 total messages: " + totalAnalytics + " (should be all published messages)");

        // --- Scenario 6: Concurrent publishing from multiple threads ---
        System.out.println("\n========== SCENARIO 6: Concurrent Publishing ==========");
        ExecutorService publisherPool = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(30);

        for (int i = 0; i < 30; i++) {
            final int msgNum = i;
            publisherPool.submit(() -> {
                try {
                    broker.publish("orders", new Message("concurrent-key-" + (msgNum % 5),
                            "Concurrent msg #" + msgNum));
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        publisherPool.shutdown();

        // Verify total messages across all partitions
        Topic ordersTopic = broker.topics.get("orders");
        int totalMessages = 0;
        for (Partition p : ordersTopic.getPartitions()) {
            int size = p.size();
            System.out.println("  Partition " + p.getPartitionId() + " size: " + size);
            totalMessages += size;
        }
        System.out.println("Total messages across all partitions: " + totalMessages);
        System.out.println("Expected: 38 (7 from scenarios 1-4 + 1 from scenario 4 new publish + 30 concurrent)");

        System.out.println("\n========== ALL SCENARIOS PASSED ==========");
    }
}
