package microsoft.kvstore;

import java.util.Map;

// Eviction strategy interface for handling eviction when the store exceeds max size.
interface EvictionStrategy<K, V> {
    void onPut(String K, V entry);

    void onGet(String K, V entry);

    String evict(Map<K, V> store, int maxSize);

    default void evictIfNeeded(Map<K, V> store, int maxSize) {
        while (store.size() > maxSize) {
            String keyToEvict = evict(store, maxSize);
            if (keyToEvict == null) break;
            store.remove(keyToEvict);
            System.out.println("Evicted key: " + keyToEvict);
        }
    }
}