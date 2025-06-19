package Rippling.kvstore;

public class Main {
    /**
     * A Main method which tests the key value store through various scenarios.
     */
    public static void main(String[] args) {
        TransactionalKeyValueStore kvStore = new TransactionalKeyValueStore();

        System.out.println("===== Test 1: Basic operations outside transactions =====");
        // Basic operations (set, get, delete) when no transactions are active.
        kvStore.set("a", "1");
        System.out.println("Set a = 1");
        System.out.println("Get a: " + kvStore.get("a"));   // Expected output: 1
        kvStore.deleteKey("a");
        System.out.println("Deleted key a");
        System.out.println("Get a after deletion: " + kvStore.get("a"));  // Expected output: null

        System.out.println("\n===== Test 2: Single Transaction with commit =====");
        // Start a transaction and commit the changes.
        kvStore.set("x", "100");
        System.out.println("Set x = 100");
        kvStore.beginTransaction(); // Begin transaction block 1.
        kvStore.set("x", "200");    // Update x within the transaction.
        System.out.println("Within transaction, get x: " + kvStore.get("x")); // Expected: 200
        kvStore.commit();           // Commit transaction block 1.
        System.out.println("After commit, get x: " + kvStore.get("x"));       // Expected: 200

        System.out.println("\n===== Test 3: Single Transaction with rollback =====");
        // Start a transaction then rollback to discard changes.
        kvStore.beginTransaction();
        kvStore.set("y", "300");
        System.out.println("Within transaction, set y = 300");
        System.out.println("Within transaction, get y: " + kvStore.get("y")); // Expected: 300
        kvStore.rollback();
        System.out.println("After rollback, get y: " + kvStore.get("y"));     // Expected: null

        System.out.println("\n===== Test 4: Nested Transactions =====");
        // Demonstrate nested transactions.
        kvStore.set("z", "alpha");
        System.out.println("Set z = alpha in base store");
        kvStore.beginTransaction(); // Outer transaction (T1).
        System.out.println("T1: get z: " + kvStore.get("z")); // Expected: alpha
        kvStore.set("z", "beta");    // Update z in T1.
        System.out.println("T1: updated z to beta, get z: " + kvStore.get("z")); // Expected: beta
        kvStore.beginTransaction();  // Nested inner transaction (T2).
        kvStore.deleteKey("z");      // Delete z in T2.
        System.out.println("T2: deleted z, get z: " + kvStore.get("z")); // Expected: null (delete overrides parent)
        kvStore.rollback();          // Rollback T2.
        System.out.println("After rollback T2, in T1, get z: " + kvStore.get("z")); // Expected: beta
        kvStore.commit();            // Commit T1 into the base store.
        System.out.println("After commit T1, get z from base store: " + kvStore.get("z")); // Expected: beta

        System.out.println("\n===== Test 5: Multiple Nested Transactions =====");
        // Demonstrate multiple nested transactions with commit and rollback.
        kvStore.beginTransaction(); // T1 begins.
        kvStore.set("k", "v1");
        System.out.println("T1: set k = v1, get k: " + kvStore.get("k")); // Expected: v1
        kvStore.beginTransaction(); // Nested T2 begins.
        kvStore.set("k", "v2");
        System.out.println("T2: updated k = v2, get k: " + kvStore.get("k")); // Expected: v2
        kvStore.commit();           // Commit T2 -> this merges changes into T1.
        System.out.println("After T2 commit, in T1, get k: " + kvStore.get("k")); // Expected: v2
        kvStore.rollback();         // Rollback T1 so none of the changes are applied.
        System.out.println("After rolling back T1, get k: " + kvStore.get("k"));  // Expected: null

        System.out.println("\n===== Final Store State =====");
        kvStore.printStore();

        SimpleKeyValueStore simpleKeyValueStore = new SimpleKeyValueStore();

        // Test 1: Basic set and get
        System.out.println("Test 1: Setting key 'a' to 'apple'.");
        simpleKeyValueStore.set("a", "apple");
        System.out.println("Expected: apple, Got: " + kvStore.get("a")); // Expected output: apple

        // Test 2: Update a key's value
        System.out.println("\nTest 2: Updating key 'a' to 'apricot'.");
        simpleKeyValueStore.set("a", "apricot");
        System.out.println("Expected: apricot, Got: " + kvStore.get("a")); // Expected output: apricot

        // Test 3: Delete key and then retrieve
        System.out.println("\nTest 3: Deleting key 'a'.");
        simpleKeyValueStore.deleteKey("a");
        System.out.println("Expected: null, Got: " + kvStore.get("a")); // Expected output: null

        // Additional tests can be added here.
        System.out.println("\nSimpleKeyValueStore tests completed.");

        concurrentTestCases();
    }

    /**
     * A main method that demonstrates concurrent operations on the store.
     * Two threads are created: one for writing (making changes) and one for reading concurrently.
     */
    public static void concurrentTestCases() {
        final ConcurrentTransactionKVStore kvStore = new ConcurrentTransactionKVStore();

        // Create a writer thread that begins a transaction, modifies a key, and commits.
        Thread writerThread = new Thread(() -> {
            kvStore.beginTransaction();
            kvStore.set("concurrent", "yes");
            // Simulate processing delay
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            kvStore.commit();
        });

        // Create a reader thread that tries to get the value during an ongoing transaction.
        Thread readerThread = new Thread(() -> {
            try {
                // Wait a bit so that writerThread has begun the transaction.
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String value = kvStore.get("concurrent");
            System.out.println("Reader thread got value: " + value);
        });

        writerThread.start();
        readerThread.start();

        try {
            writerThread.join();
            readerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Additional tests: nested transactions with concurrent access.
        kvStore.set("key", "initial");
        Thread t1 = new Thread(() -> {
            kvStore.beginTransaction();
            kvStore.set("key", "transaction1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            kvStore.commit();
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Thread2 before nested transaction, key = " + kvStore.get("key"));
            kvStore.beginTransaction();
            kvStore.set("key", "transaction2");
            System.out.println("Thread2 inside nested transaction, key = " + kvStore.get("key"));
            kvStore.rollback(); // Discard changes from t2's transaction
            System.out.println("Thread2 after rollback, key = " + kvStore.get("key"));
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final value of key: " + kvStore.get("key"));
    }
}
