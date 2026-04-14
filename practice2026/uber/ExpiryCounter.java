package uber;

// a counter that automatically expires elements after a given number of seconds
// each call to putElement(key) creates a separate entry that expires after
// T seconds. getElementCount(key) returns the number of non-expired entries for that
// key. getTotalElements() returns the total number of non-expired entries across all keys.

import java.util.*;

public class ExpiryCounter {

    private final long expiryDurationInMillis;
    private final PriorityQueue<ExpiryEntry> heap; // min heap sorted by expiry time
    private final Map<String, Integer> keyCounts;
    private final List<ExpiryListener> listeners = new ArrayList<>();
    private int totalCount;
    public ExpiryCounter(long expiryDurationInMillis) {
        this.expiryDurationInMillis = expiryDurationInMillis;
        this.heap = new PriorityQueue<>(Comparator.comparingLong(e -> e.expiryTime));
        this.keyCounts = new HashMap<>();
        this.totalCount = 0;
    }

    public static void main(String[] args) {
        ExpiryCounter counter = new ExpiryCounter(5000); // 5 seconds expiry

        // Add a listener to print when elements expire
        counter.addExpiryListener(key -> System.out.println("Expired: " + key));

        // Add some elements
        counter.putElement("a");
        counter.putElement("a");
        counter.putElement("b");

        System.out.println("Initial Total: " + counter.getTotalElements()); // 3
        System.out.println("Count of 'a': " + counter.getElementCount("a")); // 2
        System.out.println("Count of 'b': " + counter.getElementCount("b")); // 1

        // Wait for expiry (6 seconds > 5 seconds)
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check counts after expiry
        System.out.println("After expiry - Total: " + counter.getTotalElements()); // 0
        System.out.println("Count of 'a': " + counter.getElementCount("a")); // 0
        System.out.println("Count of 'b': " + counter.getElementCount("b")); // 0
    }

    // remove all expired entries from heap & update the counts.
    private void cleanUp() {
        long now = System.currentTimeMillis();
        while (!heap.isEmpty() && heap.peek().expiryTime <= now) {
            ExpiryEntry entry = heap.poll();
            String key = entry.key;
            // decrement count for this key
            Integer current = keyCounts.get(key);
            if (current != null) {
                if (current == 1) {
                    keyCounts.remove(key);
                } else {
                    keyCounts.put(key, current - 1);
                }
                totalCount--;
                // Notify listeners about the expired entry
                notifyListeners(key);
            }
        }
    }

    public void putElement(String key) {
        cleanUp();
        long expiry = System.currentTimeMillis() + expiryDurationInMillis;
        heap.offer(new ExpiryEntry(expiry, key));
        keyCounts.merge(key, 1, Integer::sum);
        totalCount++;
    }

    public int getElementCount(String key) {
        cleanUp();
        return keyCounts.getOrDefault(key, 0);
    }

    public int getTotalElements() {
        cleanUp();
        return totalCount;
    }

    public void addExpiryListener(ExpiryListener listener) {
        listeners.add(listener);
    }

    public void removeExpiryListener(ExpiryListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(String key) {
        for (ExpiryListener listener : listeners) {
            listener.onExpiry(key);
        }
    }

    public interface ExpiryListener {
        void onExpiry(String key);
    }

    public static class ExpiryEntry {
        private final long expiryTime; // timestamp in ms when this entry expires
        private final String key;

        public ExpiryEntry(long expiryTime, String key) {
            this.expiryTime = expiryTime;
            this.key = key;
        }
    }

}
