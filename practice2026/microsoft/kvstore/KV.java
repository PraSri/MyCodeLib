package microsoft.kvstore;

import java.util.*;

public class KV {


    // ---------- DataType Interface & Implementations ----------
    interface DataType {
        DataType copy();                // for transaction rollback

        String getType();

        Object getValue();
    }

    // ---------- Eviction Strategy ----------
    interface EvictionStrategy {
        void onPut(String key, StoreEntry entry);

        void onGet(String key, StoreEntry entry);

        String evict(Map<String, StoreEntry> store, int maxSize);

        default void evictIfNeeded(Map<String, StoreEntry> store, int maxSize) {
            while (store.size() > maxSize) {
                String keyToEvict = evict(store, maxSize);
                if (keyToEvict == null) break;
                store.remove(keyToEvict);
                System.out.println("Evicted key: " + keyToEvict);
            }
        }
    }

    // ---------- Transaction Support (Command Pattern) ----------
    interface TransactionCommand {
        void execute();

        void undo();
    }

    class StringValue implements DataType {
        private String value;

        public StringValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public DataType copy() {
            return new StringValue(value);
        }

        @Override
        public String getType() {
            return "string";
        }

        @Override
        public String toString() {
            return value;
        }
    }

    class ListValue implements DataType {
        private final List<Object> list;

        public ListValue() {
            this.list = new ArrayList<>();
        }

        public ListValue(List<Object> list) {
            this.list = new ArrayList<>(list);
        }

        public void add(Object element) {
            list.add(element);
        }

        public Object remove(int index) {
            return list.remove(index);
        }

        public List<Object> getList() {
            return Collections.unmodifiableList(list);
        }

        @Override
        public DataType copy() {
            return new ListValue(list);
        }

        @Override
        public String getType() {
            return "list";
        }

        @Override
        public Object getValue() {
            return getList();
        }
    }

    class SortedSetValue implements DataType {
        // Use TreeMap to keep elements sorted by score
        private final TreeMap<Double, Set<String>> scoreToElements = new TreeMap<>();
        private final Map<String, Double> elementToScore = new HashMap<>();

        public void add(double score, String element) {
            Double oldScore = elementToScore.get(element);
            if (oldScore != null) {
                removeElementFromScore(oldScore, element);
            }
            elementToScore.put(element, score);
            scoreToElements.computeIfAbsent(score, k -> new LinkedHashSet<>()).add(element);
        }

        public boolean remove(String element) {
            Double score = elementToScore.remove(element);
            if (score != null) {
                removeElementFromScore(score, element);
                return true;
            }
            return false;
        }

        private void removeElementFromScore(double score, String element) {
            Set<String> set = scoreToElements.get(score);
            if (set != null) {
                set.remove(element);
                if (set.isEmpty()) scoreToElements.remove(score);
            }
        }

        public List<Map.Entry<String, Double>> rangeByScore(double min, double max) {
            List<Map.Entry<String, Double>> result = new ArrayList<>();
            for (Map.Entry<Double, Set<String>> entry : scoreToElements.subMap(min, true, max, true).entrySet()) {
                double score = entry.getKey();
                for (String elem : entry.getValue()) {
                    result.add(new AbstractMap.SimpleEntry<>(elem, score));
                }
            }
            return result;
        }

        @Override
        public DataType copy() {
            SortedSetValue copy = new SortedSetValue();
            for (Map.Entry<String, Double> e : elementToScore.entrySet()) {
                copy.add(e.getValue(), e.getKey());
            }
            return copy;
        }

        @Override
        public String getType() {
            return "sortedset";
        }

        @Override
        public Object getValue() {
            return elementToScore;
        }
    }

    // Factory for creating DataType instances
    class DataTypeFactory {
        public static StringValue createString(String value) {
            return new StringValue(value);
        }

        public static ListValue createList() {
            return new ListValue();
        }

        public static SortedSetValue createSortedSet() {
            return new SortedSetValue();
        }
    }

    // ---------- Store Entry with Metadata ----------
    class StoreEntry {
        private final long creationTime;
        private DataType value;
        private long lastAccessTime;
        private long accessCount;      // for LFU
        private Long ttlMillis;        // null means no TTL

        public StoreEntry(DataType value) {
            this.value = value;
            this.creationTime = System.currentTimeMillis();
            this.lastAccessTime = this.creationTime;
            this.accessCount = 1;
            this.ttlMillis = null;
        }

        public void setTtl(long ttlMillis) {
            this.ttlMillis = ttlMillis;
        }

        public boolean isExpired() {
            if (ttlMillis == null) return false;
            return System.currentTimeMillis() - creationTime > ttlMillis;
        }

        public void recordAccess() {
            lastAccessTime = System.currentTimeMillis();
            accessCount++;
        }

        // getters & setters
        public DataType getValue() {
            return value;
        }

        public void setValue(DataType value) {
            this.value = value;
        }

        public long getLastAccessTime() {
            return lastAccessTime;
        }

        public long getAccessCount() {
            return accessCount;
        }

        public Long getTtlMillis() {
            return ttlMillis;
        }
    }

    class LRUEviction implements EvictionStrategy {
        @Override
        public void onPut(String key, StoreEntry entry) {
        }

        @Override
        public void onGet(String key, StoreEntry entry) {
        }

        @Override
        public String evict(Map<String, StoreEntry> store, int maxSize) {
            return store.entrySet().stream()
                    .min(Comparator.comparingLong(e -> e.getValue().getLastAccessTime()))
                    .map(Map.Entry::getKey)
                    .orElse(null);
        }
    }

    class LFUEviction implements EvictionStrategy {
        @Override
        public void onPut(String key, StoreEntry entry) {
        }

        @Override
        public void onGet(String key, StoreEntry entry) {
        }

        @Override
        public String evict(Map<String, StoreEntry> store, int maxSize) {
            return store.entrySet().stream()
                    .min(Comparator.comparingLong(e -> e.getValue().getAccessCount()))
                    .map(Map.Entry::getKey)
                    .orElse(null);
        }
    }

    class TTLEviction implements EvictionStrategy {
        @Override
        public void onPut(String key, StoreEntry entry) {
        }

        @Override
        public void onGet(String key, StoreEntry entry) {
        }

        @Override
        public String evict(Map<String, StoreEntry> store, int maxSize) {
            // remove first expired key
            return store.entrySet().stream()
                    .filter(e -> e.getValue().isExpired())
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);
        }
    }

    // ---------- Builder for KeyValueStore ----------
    class KVStoreBuilder {
        private int maxSize = 100;
        private EvictionStrategy evictionStrategy = new LRUEviction();

        public KVStoreBuilder setMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public KVStoreBuilder setEvictionStrategy(EvictionStrategy strategy) {
            this.evictionStrategy = strategy;
            return this;
        }

        public KeyValueStore build() {
            return new KeyValueStore(maxSize, evictionStrategy);
        }
    }

    // ---------- Main Key-Value Store ----------
    class KeyValueStore {
        private final Map<String, StoreEntry> store = new HashMap<>();
        private final int maxSize;
        private final EvictionStrategy evictionStrategy;

        KeyValueStore(int maxSize, EvictionStrategy evictionStrategy) {
            this.maxSize = maxSize;
            this.evictionStrategy = evictionStrategy;
        }

        // Public API
        public synchronized void put(String key, DataType value) {
            removeExpiredKeys();
            StoreEntry entry = new StoreEntry(value);
            store.put(key, entry);
            evictionStrategy.onPut(key, entry);
            evictionStrategy.evictIfNeeded(store, maxSize);
        }

        public synchronized DataType get(String key) {
            removeExpiredKeys();
            StoreEntry entry = store.get(key);
            if (entry == null || entry.isExpired()) {
                if (entry != null) store.remove(key);
                return null;
            }
            entry.recordAccess();
            evictionStrategy.onGet(key, entry);
            return entry.getValue();
        }

        public synchronized void delete(String key) {
            store.remove(key);
        }

        public synchronized void setTtl(String key, long ttlMillis) {
            StoreEntry entry = store.get(key);
            if (entry != null) entry.setTtl(ttlMillis);
        }

        // Helper for list operations
        public synchronized void listAdd(String key, Object element) {
            DataType dt = get(key);
            ListValue list;
            if (dt instanceof ListValue) {
                list = (ListValue) dt;
            } else if (dt == null) {
                list = DataTypeFactory.createList();
                put(key, list);
            } else {
                throw new IllegalArgumentException("Key is not a list");
            }
            list.add(element);
            // Update entry (get already recorded access)
            store.get(key).setValue(list);
        }

        // SortedSet operations
        public synchronized void sortedSetAdd(String key, double score, String element) {
            DataType dt = get(key);
            SortedSetValue ss;
            if (dt instanceof SortedSetValue) {
                ss = (SortedSetValue) dt;
            } else if (dt == null) {
                ss = DataTypeFactory.createSortedSet();
                put(key, ss);
            } else {
                throw new IllegalArgumentException("Key is not a sorted set");
            }
            ss.add(score, element);
            store.get(key).setValue(ss);
        }

        public synchronized List<Map.Entry<String, Double>> sortedSetRangeByScore(String key, double min, double max) {
            DataType dt = get(key);
            if (dt instanceof SortedSetValue) {
                return ((SortedSetValue) dt).rangeByScore(min, max);
            }
            return Collections.emptyList();
        }

        private void removeExpiredKeys() {
            store.entrySet().removeIf(e -> e.getValue().isExpired());
        }

        // For transaction support: get a copy of entry (deep copy of value)
        synchronized StoreEntry getEntryCopy(String key) {
            StoreEntry orig = store.get(key);
            if (orig == null) return null;
            StoreEntry copy = new StoreEntry(orig.getValue().copy());
            copy.setTtl(orig.getTtlMillis());
            // metadata like access times not needed for rollback
            return copy;
        }

        synchronized void setEntry(String key, StoreEntry entry) {
            if (entry == null) store.remove(key);
            else store.put(key, entry);
        }
    }

    class PutCommand implements TransactionCommand {
        private final KeyValueStore store;
        private final String key;
        private final DataType newValue;
        private StoreEntry oldEntry;

        PutCommand(KeyValueStore store, String key, DataType newValue) {
            this.store = store;
            this.key = key;
            this.newValue = newValue;
        }

        @Override
        public void execute() {
            oldEntry = store.getEntryCopy(key);
            store.put(key, newValue);
        }

        @Override
        public void undo() {
            store.setEntry(key, oldEntry);
        }
    }

    class DeleteCommand implements TransactionCommand {
        private final KeyValueStore store;
        private final String key;
        private StoreEntry oldEntry;

        DeleteCommand(KeyValueStore store, String key) {
            this.store = store;
            this.key = key;
        }

        @Override
        public void execute() {
            oldEntry = store.getEntryCopy(key);
            store.delete(key);
        }

        @Override
        public void undo() {
            store.setEntry(key, oldEntry);
        }
    }

    // Transaction manager
    class Transaction {
        private final KeyValueStore store;
        private final List<TransactionCommand> commands = new ArrayList<>();
        private boolean active = false;

        public Transaction(KeyValueStore store) {
            this.store = store;
        }

        public void begin() {
            if (active) throw new IllegalStateException("Transaction already active");
            commands.clear();
            active = true;
        }

        public void put(String key, DataType value) {
            if (!active) throw new IllegalStateException("No active transaction");
            PutCommand cmd = new PutCommand(store, key, value);
            cmd.execute();
            commands.add(cmd);
        }

        public void delete(String key) {
            if (!active) throw new IllegalStateException("No active transaction");
            DeleteCommand cmd = new DeleteCommand(store, key);
            cmd.execute();
            commands.add(cmd);
        }

        public void commit() {
            if (!active) throw new IllegalStateException("No active transaction");
            active = false;
            commands.clear(); // changes are already applied, just discard log
        }

        public void rollback() {
            if (!active) throw new IllegalStateException("No active transaction");
            // undo in reverse order
            for (int i = commands.size() - 1; i >= 0; i--) {
                commands.get(i).undo();
            }
            commands.clear();
            active = false;
        }
    }

    // ---------- Demonstration ----------
    public class KeyValueStoreDemo {
        public static void main(String[] args) {
            // Build store with LRU and max size 3
            KeyValueStore store = new KVStoreBuilder()
                    .setMaxSize(3)
                    .setEvictionStrategy(new LRUEviction())
                    .build();

            store.put("a", DataTypeFactory.createString("apple"));
            store.put("b", DataTypeFactory.createString("banana"));
            store.put("c", DataTypeFactory.createString("cherry"));

            // Access "a" to make it recently used
            System.out.println("get a: " + store.get("a"));

            // This should evict "b" (LRU)
            store.put("d", DataTypeFactory.createString("date"));
            System.out.println("After putting d, get b: " + store.get("b")); // null

            // List operations
            store.listAdd("fruits", "apple");
            store.listAdd("fruits", "banana");
            System.out.println("fruits list: " + store.get("fruits").getValue());

            // Sorted set operations
            store.sortedSetAdd("scores", 95.5, "Alice");
            store.sortedSetAdd("scores", 87.0, "Bob");
            store.sortedSetAdd("scores", 92.3, "Charlie");
            System.out.println("Scores 90-100: " + store.sortedSetRangeByScore("scores", 90.0, 100.0));

            // TTL example
            store.setTtl("a", 2000); // expires after 2 seconds
            System.out.println("get a (before expiry): " + store.get("a"));
            try {
                Thread.sleep(2100);
            } catch (InterruptedException e) {
            }
            System.out.println("get a (after expiry): " + store.get("a"));

            // Transaction demo
            Transaction tx = new Transaction(store);
            tx.begin();
            tx.put("x", DataTypeFactory.createString("transactional"));
            tx.put("y", DataTypeFactory.createString("value"));
            System.out.println("Inside transaction - get x: " + store.get("x")); // visible
            tx.rollback();
            System.out.println("After rollback - get x: " + store.get("x")); // null
        }
    }
}
