package Rippling.kvstore;

import java.util.*;

// Implementation of the key-value store with transaction support.
public class TransactionalKeyValueStore implements IKeyValueStore {
    // Base key-value store. This holds data when no transactions are active.
    private final Map<String, String> store = new HashMap<>();

    // A stack (implemented via Deque) holds transaction contexts.
    // Each transaction is represented as a Map holding all changes made inside that transaction.
    private final Deque<Map<String, String>> transactions = new ArrayDeque<>();

    /**
     * Retrieve the value for a given key.
     * It first checks the active transactions (from newest to oldest).
     * If a key is found with a value of null, it indicates deletion.
     * Otherwise, if no active transaction contains the key, the base store is checked.
     */
    @Override
    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        // Start with the latest transaction overlay.
        Iterator<Map<String, String>> it = transactions.descendingIterator();
        while (it.hasNext()) {
            Map<String, String> txn = it.next();
            if (txn.containsKey(key)) {
                // A null value represents that the key has been deleted in that transaction.
                return txn.get(key);
            }
        }
        // Return the key from the base store if not overridden.
        return store.get(key);
    }

    /**
     * Set a key to a given value.
     * If a transaction is active, record the change in the current transaction overlay.
     * Otherwise, update the base store directly.
     */
    @Override
    public void set(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }
        if (!transactions.isEmpty()) {
            // Record the change in the top-most transaction.
            transactions.peekLast().put(key, value);
        } else {
            store.put(key, value);
        }
    }

    /**
     * Delete the key.
     * In an active transaction, we record the deletion by setting the value to null.
     * Without a transaction, we remove the key from the base store.
     */
    @Override
    public void deleteKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (!transactions.isEmpty()) {
            transactions.peekLast().put(key, null);
        } else {
            store.remove(key);
        }
    }

    /**
     * Begin a new transaction.
     * This method pushes a new (empty) transaction context onto the stack.
     */
    @Override
    public void beginTransaction() {
        transactions.addLast(new HashMap<>());
        System.out.println("Transaction begun. Now " + transactions.size() + " active transaction(s).");
    }

    /**
     * Commit the current transaction.
     * If the current transaction is nested (i.e. there is a parent transaction),
     * merge the current changes into the parent.
     * Otherwise, merge the changes into the base store.
     */
    @Override
    public void commit() {
        if (transactions.isEmpty()) {
            System.out.println("No active transaction to commit.");
            return;
        }
        // Remove the current (top-most) transaction overlay.
        Map<String, String> currentTxn = transactions.removeLast();
        if (!transactions.isEmpty()) {
            // Merge into the parent transaction overlay.
            Map<String, String> parentTxn = transactions.peekLast();
            parentTxn.putAll(currentTxn);
            System.out.println("Committed nested transaction. Remaining transactions: " + transactions.size());
        } else {
            // Merge changes into the base store.
            for (Map.Entry<String, String> entry : currentTxn.entrySet()) {
                if (entry.getValue() == null) {
                    store.remove(entry.getKey());
                } else {
                    store.put(entry.getKey(), entry.getValue());
                }
            }
            System.out.println("Committed transaction to base store.");
        }
    }

    /**
     * Rollback the current transaction.
     * This simply discards the latest overlay from the transaction stack.
     */
    @Override
    public void rollback() {
        if (transactions.isEmpty()) {
            System.out.println("No active transaction to rollback.");
            return;
        }
        transactions.removeLast();
        System.out.println("Rolled back a transaction. Remaining transactions: " + transactions.size());
    }

    /**
     * Helper method to print the current state of the base store and active transactions.
     */
    public void printStore() {
        System.out.println("Base Store: " + store);
        System.out.println("Active Transactions (from earliest to latest): " + transactions);
    }


}
