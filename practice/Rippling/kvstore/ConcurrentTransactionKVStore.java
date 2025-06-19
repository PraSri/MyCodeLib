package Rippling.kvstore;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread-safe version of our transactional key-value store.
 * This class uses a ReentrantLock to guard all operations that read or modify
 * the internal base store and the stack of transactions.
 * This ensures that concurrent access by multiple threads does not lead to inconsistent data.
 */
public class ConcurrentTransactionKVStore implements IKeyValueStore {
    // Base store holds key–value mappings when no transaction is active.
    private final Map<String, String> store = new HashMap<>();

    // A stack of transaction overlays supporting nested transactions.
    // Each transaction overlay (a HashMap) holds changes made in that transaction.
    private final Deque<Map<String, String>> transactions = new ArrayDeque<>();

    // A ReentrantLock to ensure that every method that accesses/modifies shared data is thread-safe.
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Retrieves the value associated with the specified key.
     * The method first checks the most recent (innermost) transaction for an override.
     * If no transaction-specific change exists, it falls back to the base store.
     *
     * @param key The key to retrieve.
     * @return The current value of the key, which might be null if deleted or not set.
     */
    @Override
    public String get(String key) {
        lock.lock();
        try {
            // Check the current transactions from the most recent.
            Iterator<Map<String, String>> it = transactions.descendingIterator();
            while (it.hasNext()) {
                Map<String, String> txn = it.next();
                if (txn.containsKey(key)) {
                    // A value of null represents that the key was deleted in the transaction.
                    return txn.get(key);
                }
            }
            // Check the base store if no transaction has overridden the key.
            return store.get(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Sets or updates the value associated with the key.
     * If a transaction is active, the change is recorded in the current transaction overlay.
     * Otherwise, the base store is updated directly.
     *
     * @param key   The key to set.
     * @param value The value to associate with the key.
     */
    @Override
    public void set(String key, String value) {
        lock.lock();
        try {
            if (!transactions.isEmpty()) {
                // Record update in the active transaction (top of the stack).
                transactions.peekLast().put(key, value);
            } else {
                // Update the base store directly.
                store.put(key, value);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Deletes the key.
     * In an active transaction, a deletion is recorded by associating the key with a null value.
     * When no transaction is active, the key is removed from the base store.
     *
     * @param key The key to delete.
     */
    @Override
    public void deleteKey(String key) {
        lock.lock();
        try {
            if (!transactions.isEmpty()) {
                transactions.peekLast().put(key, null);
            } else {
                store.remove(key);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Begins a new transaction by pushing an empty overlay onto the transaction stack.
     */
    @Override
    public void beginTransaction() {
        lock.lock();
        try {
            transactions.addLast(new HashMap<>());
            System.out.println("Transaction begun. Active transactions: " + transactions.size());
        } finally {
            lock.unlock();
        }
    }

    /**
     * Commits the current transaction.
     * If there are nested transactions, the changes are merged into the parent transaction.
     * Otherwise, the changes are merged into the base store.
     */
    @Override
    public void commit() {
        lock.lock();
        try {
            if (transactions.isEmpty()) {
                System.out.println("No active transaction to commit.");
                return;
            }
            // Remove the current (top) transaction overlay.
            Map<String, String> currentTxn = transactions.removeLast();
            if (!transactions.isEmpty()) {
                // If nested, merge into the parent transaction overlay.
                Map<String, String> parentTxn = transactions.peekLast();
                parentTxn.putAll(currentTxn);
                System.out.println("Committed nested transaction. Active transactions: " + transactions.size());
            } else {
                // Merge to the base store.
                for (Map.Entry<String, String> entry : currentTxn.entrySet()) {
                    if (entry.getValue() == null) {
                        store.remove(entry.getKey());
                    } else {
                        store.put(entry.getKey(), entry.getValue());
                    }
                }
                System.out.println("Committed transaction to base store.");
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Rolls back the current transaction by discarding the latest overlay.
     */
    @Override
    public void rollback() {
        lock.lock();
        try {
            if (transactions.isEmpty()) {
                System.out.println("No active transaction to rollback.");
                return;
            }
            transactions.removeLast();
            System.out.println("Rolled back transaction. Active transactions: " + transactions.size());
        } finally {
            lock.unlock();
        }
    }

    /**
     * A helper method to print the current state of the base store and active transactions.
     */
    public void printStore() {
        lock.lock();
        try {
            System.out.println("Base Store: " + store);
            System.out.println("Active Transactions: " + transactions);
        } finally {
            lock.unlock();
        }
    }
}
