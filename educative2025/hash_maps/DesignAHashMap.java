package hash_maps;

import java.util.*;

public class DesignAHashMap {

    // Define DesignHashMap class to implement the HashMap functionality
    static class DesignHashMap {
        public int keySpace;
        public Bucket[] buckets;

        // Constructor to initialize the HashMap
        public DesignHashMap() {
            keySpace = 2069;
            buckets = new Bucket[keySpace];
            for (int i = 0; i < keySpace; i++) {
                buckets[i] = new Bucket();
            }
        }

        // Function to add a key-value pair to the hash map
        public void put(int key, int value) {
            int hashKey = key % keySpace;
            buckets[hashKey].update(key, value);
        }

        // Function to retrieve the value associated with a given key from the hash map
        public int get(int key) {
            int hashKey = key % keySpace;
            return buckets[hashKey].get(key);
        }

        // Function to remove a key-value pair from the hash map given a key
        public void remove(int key) {
            int hashKey = key % keySpace;
            buckets[hashKey].remove(key);
        }


        // Main method to demonstrate the usage of the HashMap
        public static void main(String args[]) {

            DesignHashMap inputHashMap = new DesignHashMap();
            List<Integer> keys = Arrays.asList(5, 2069, 2070, 2073, 4138, 2068);
            List<Integer> keysList = new ArrayList<>(Arrays.asList(5, 2069, 2070, 2073, 4138, 2068));
            List<Integer> values = Arrays.asList(100, 200, 400, 500, 1000, 5000);
            List<String> funcs = Arrays.asList("Get", "Get", "Put", "Get", "Put", "Get", "Get", "Remove", "Get", "Get", "Remove", "Get");
            List<List<Integer>> funcKeys = Arrays.asList(
                    Arrays.asList(5),
                    Arrays.asList(2073),
                    Arrays.asList(2073, 50),
                    Arrays.asList(2073),
                    Arrays.asList(121, 110),
                    Arrays.asList(121),
                    Arrays.asList(2068),
                    Arrays.asList(2069),
                    Arrays.asList(2069),
                    Arrays.asList(2071),
                    Arrays.asList(2071),
                    Arrays.asList(2071)
            );

            for (int i = 0; i < keys.size(); i++) {
                inputHashMap.put(keys.get(i), values.get(i));
            }

            for (int i = 0; i < funcs.size(); i++) {
                if (funcs.get(i) == "Put") {
                    System.out.println(i + 1 + ".\t put(" + funcKeys.get(i).get(0) + ", " + funcKeys.get(i).get(1) + ")");
                    if (!(keysList.contains(funcKeys.get(i).get(0)))) {
                        keysList.add(funcKeys.get(i).get(0));
                    }
                    inputHashMap.put(funcKeys.get(i).get(0), funcKeys.get(i).get(1));
                } else if (funcs.get(i) == "Get") {
                    System.out.println(i + 1 + ".\t get(" + funcKeys.get(i).get(0) + ")");
                    System.out.println("\t Value returned: " + inputHashMap.get(funcKeys.get(i).get(0)));
                } else if (funcs.get(i) == "Remove") {
                    System.out.println(i + 1 + ". \t remove(" + funcKeys.get(i).get(0) + ")");
                    inputHashMap.remove(funcKeys.get(i).get(0));
                }

                System.out.println(new String(new char[100]).replace('\0', '-'));
            }
        }
    }

    // Define Bucket class
    static class Bucket {
        private ArrayList<Pair<Integer, Integer>> bucket;

        public Bucket() {
            // Constructor to initialize an empty list to store key-value pairs
            bucket = new ArrayList<>();
        }

        public int get(int key) {
            // Iterate through each key-value pair in the bucket
            for (Pair<Integer, Integer> kv : bucket) {
                // If the key matches the provided key, return the corresponding value
                if (kv.getKey() == key) {
                    return kv.getValue();
                }
            }
            // If the key is not found, return -1
            return -1;
        }

        public void update(int key, int value) {
            // Flag to indicate whether the key is found in the bucket
            boolean found = false;
            // Iterate through each key-value pair in the bucket
            for (int i = 0; i < bucket.size(); i++) {
                Pair<Integer, Integer> kv = bucket.get(i);
                // If the key matches the key of the current key-value pair
                if (key == kv.getKey()) {
                    // Update the value of the key-value pair
                    bucket.set(i, new Pair<>(key, value));
                    // Set the flag to true, indicating that the key is found
                    found = true;
                    break;
                }
            }
            // If the key is not found in the bucket, add it along with its value
            if (!found) {
                bucket.add(new Pair<>(key, value));
            }
        }

        public void remove(int key) {
            // Iterate through each key-value pair in the bucket
            for (int i = 0; i < bucket.size(); i++) {
                Pair<Integer, Integer> kv = bucket.get(i);
                // If the key matches the key of the current key-value pair
                if (key == kv.getKey()) {
                    // Delete the key-value pair from the bucket
                    bucket.remove(i);
                    // Exit the loop as the key has been removed
                    break;
                }
            }
        }
    }

    // Define Pair class to store key-value pairs
    static class Pair<K, V> {
        private K key;
        private V value;

        // Constructor to initialize key-value pair
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // Getter methods for key and value
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
