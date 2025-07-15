package custom_data_structures;

import java.util.*;

public class LFUCache {

    /* This will store the minimum frequency which will be needed to evict if capacity exceeds */
    private int min;

    /* Capacity of the cache */
    private final int capacity;

    /* Map from key to value */
    private final HashMap<Integer, Integer> keyToVal;

    /* Map from key to frequency of key */
    private final HashMap<Integer, Integer> keyToCount;

    /* Map from count as key to LinkedHashSet of keys with corresponding count
    LinkedHashSet maintains the insertion order. Elements gets sorted in the same sequence in which they have         been added to the Set */
    private final HashMap<Integer, LinkedHashSet<Integer>> countToLRUKeys;

    public LFUCache(int capacity) {
        this.min = -1;
        this.capacity = capacity;
        this.keyToVal = new HashMap<>();
        this.keyToCount = new HashMap<>();
        this.countToLRUKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) /* If there is no such key in the keyToVal map */
            return -1;

        int count = keyToCount.get(key); /* If it exists, we get the count of that key */
        countToLRUKeys.get(count).remove(key); /* remove key from current count (since we will inc count) */
        if (count == min && countToLRUKeys.get(count).size() == 0)
            min++; /* nothing in the current min bucket */

        putCount(key, count + 1); /* add the key to keyToCount map with incremented count and add the key to                                        corresponding LinkedHashSet in the countToLRUKeys map. */
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) /* If capacity is alrady 0 or negative, we cannot put */
            return;

        /* If key is already present in keyToVal map and just the value is needed to be changed */
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value); // update key's value
            int count = keyToCount.get(key); // we get the old count of that key
            countToLRUKeys.get(count).remove(key); // remove key from current count (since we will inc count)
            if (count == min && countToLRUKeys.get(count).size() == 0)
                min++; // nothing in the current min bucket

            putCount(key, count + 1); // add the key to keyToCount map with incremented count and add the key                                           to corresponding LinkedHashSet in the countToLRUKeys map.
            return;
        }

        /* If the map exceeds the capacity, remove the first key with minimum count */
        if (keyToVal.size() >= capacity)
            evict(countToLRUKeys.get(min).iterator().next());
/* evict LRU from this min count bucket
            This evict function does two things :
            1.) Remove the key from LinkedHashSet correspondinf to minCount
            2.) Remove key from keyToVal map

            Also, we used iterator.next() since the least recently+frequently used value to be removed is the first element in LinkedHashSet with the lowest count/frequency.
            */

        min = 1;
        putCount(key, min); // adding new key and count
        keyToVal.put(key, value); // adding new key and value
    }

    private void evict(int key) {
        countToLRUKeys.get(min).remove(key);
        keyToVal.remove(key);
    }

    private void putCount(int key, int count) {
        keyToCount.put(key, count);
        countToLRUKeys.computeIfAbsent(count, ignore -> new LinkedHashSet<>());
        countToLRUKeys.get(count).add(key);
    }

}
