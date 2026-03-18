package Rippling.kvstore;

import java.util.*;

/**
 * A simple implementation of the IKeyValueStore interface,
 * using an in-memory HashMap to maintain key–value pairs.
 * <p>
 * This design illustrates encapsulation in OOP by hiding the details
 * of the underlying storage (the HashMap) behind the interface.
 */
public class SimpleKeyValueStore implements BaseKVStore {

    // Underlying storage for the key-value mappings.
    private final Map<String, String> store = new HashMap<>();

    /**
     * Retrieves the value for the given key.
     *
     * @param key The key to retrieve.
     * @return The corresponding value if present, else null.
     */
    @Override
    public String get(String key) {
        return store.get(key);
    }

    /**
     * Associates the given key with the specified value.
     *
     * @param key   The key to set.
     * @param value The value to be associated with the key.
     */
    @Override
    public void set(String key, String value) {
        store.put(key, value);
    }

    /**
     * Removes the key and its associated value from the store.
     *
     * @param key The key to remove.
     */
    @Override
    public void deleteKey(String key) {
        store.remove(key);
    }
}
