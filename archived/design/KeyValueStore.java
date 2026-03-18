import java.util.*;

/**
 * KeyValueStore implements a simple key–value cache with transaction support.
 *
 * It supports the following operations:
 *  - SET key value
 *  - GET key
 *  - DELETE key
 *  - BEGIN (starts a new transaction)
 *  - COMMIT (commits the latest transaction)
 *  - ROLLBACK (aborts the latest transaction)
 */
public class KeyValueStore {

    // Base data store where key–value pairs are permanently stored
    private Map<String, String> store;

    // A stack of transaction maps. Each map at a transaction level records operations.
    // A "null" value in a transaction map means that the key was deleted.
    private Deque<Map<String, String>> transactionStack;

    /**
     * Constructor initializes the base store and the transaction stack.
     */
    public KeyValueStore() {
        this.store = new HashMap<>();
        this.transactionStack = new ArrayDeque<>();
    }

    /**
     * Sets the value for a given key.
     * If in a transaction, the change is recorded in the transaction map,
     * otherwise it is directly updated in the base store.
     *
     * @param key The key to set.
     * @param value The value to associate with the key.
     */
    public void set(String key, String value) {
        if (inTransaction()) {
            // Set value in current transaction
            transactionStack.peek().put(key, value);
        } else {
            // Direct update to base store if no transaction is active
            store.put(key, value);
        }
    }

    /**
     * Retrieves the value for a given key.
     * It first checks the transaction stack (from top-most to bottom) for any changes,
     * and if none is found, returns the value from the base store.
     *
     * @param key The key to be retrieved.
     * @return The associated value, or null if the key does not exist.
     */
    public String get(String key) {
        // Check from the newest transaction block to the oldest
        for (Map<String, String> txn : transactionStack) {
            if (txn.containsKey(key)) {
                // Found a change (which could be a deletion if the value is null)
                return txn.get(key);
            }
        }
        // If no transaction has modified the key, return from the base store
        return store.get(key);
    }

    /**
     * Deletes a key from the store.
     * If in a transaction, a deletion is recorded by setting the key’s value to null.
     * Otherwise, the key is removed from the base store.
     *
     * @param key The key to delete.
     */
    public void delete(String key) {
        if (inTransaction()) {
            // Null represents deletion in a transaction
            transactionStack.peek().put(key, null);
        } else {
            store.remove(key);
        }
    }

    /**
     * Begins a new transaction by pushing a new empty transaction map on the stack.
     */
    public void begin() {
        transactionStack.push(new HashMap<>());
    }

    /**
     * Commits the latest transaction.
     *
     * If another transaction is active below the current one, merge the changes with it.
     * Otherwise, update the base store with the changes.
     *
     * @return True if a transaction was committed, false if no transaction was active.
     */
    public boolean commit() {
        if (!inTransaction()) {
            System.out.println("NO TRANSACTION");
            return false;
        }
        // Pop the latest transaction and get its changes
        Map<String, String> changes = transactionStack.pop();
        if (inTransaction()) {
            // Merge changes into the transaction below if a nested transaction exists
            Map<String, String> previousTxn = transactionStack.peek();
            for (Map.Entry<String, String> entry : changes.entrySet()) {
                previousTxn.put(entry.getKey(), entry.getValue());
            }
        } else {
            // No more active transactions; apply changes to the base store directly
            for (Map.Entry<String, String> entry : changes.entrySet()) {
                if (entry.getValue() == null) {
                    store.remove(entry.getKey());
                } else {
                    store.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return true;
    }

    /**
     * Rolls back the most recent transaction.
     * All operations made since the last BEGIN are discarded.
     *
     * @return True if the rollback was successful, false if no transaction was active.
     */
    public boolean rollback() {
        if (!inTransaction()) {
            System.out.println("NO TRANSACTION");
            return false;
        }
        // Simply discard the top transaction, losing all changes
        transactionStack.pop();
        return true;
    }

    /**
     * Helper method to check if a transaction is active.
     *
     * @return True if there is at least one active transaction.
     */
    private boolean inTransaction() {
        return !transactionStack.isEmpty();
    }

    /**
     * Processes a command string (e.g., "SET key value") by parsing it and calling the
     * corresponding method. This method also handles transaction commands.
     *
     * @param command The command string to process.
     */
    public void processCommand(String command) {
        // Split command into words based on whitespace
        String[] tokens = command.trim().split("\\s+");
        if (tokens.length == 0) {
            return;
        }

        String action = tokens[0].toUpperCase();

        switch (action) {
            case "SET":
                if (tokens.length != 3) {
                    System.out.println("Invalid SET command. Usage: SET <key> <value>");
                } else {
                    set(tokens[1], tokens[2]);
                }
                break;
            case "GET":
                if (tokens.length != 2) {
                    System.out.println("Invalid GET command. Usage: GET <key>");
                } else {
                    String value = get(tokens[1]);
                    // Print "NULL" if no value exists
                    System.out.println(value != null ? value : "NULL");
                }
                break;
            case "DELETE":
                if (tokens.length != 2) {
                    System.out.println("Invalid DELETE command. Usage: DELETE <key>");
                } else {
                    delete(tokens[1]);
                }
                break;
            case "BEGIN":
                begin();
                break;
            case "COMMIT":
                if (!commit()) {
                    // "NO TRANSACTION" printed inside commit
                }
                break;
            case "ROLLBACK":
                if (!rollback()) {
                    // "NO TRANSACTION" printed inside rollback
                }
                break;
            default:
                System.out.println("Unknown command: " + action);
        }
    }

    /**
     * Main method to run a demo for this key–value cache system.
     * It processes a list of command strings.
     */
    public static void main(String[] args) {
        KeyValueStore kvStore = new KeyValueStore();

        // Sample list of commands to test the key value cache and transactions.
        // You can add more commands to see how transactions behave.
        List<String> commands = Arrays.asList(
            "SET key1 value1",
            "GET key1",       // Expected output: value1
            "BEGIN",          // Start a new transaction
            "SET key1 value2",
            "GET key1",       // Expected output: value2 (inside transaction)
            "BEGIN",          // Start nested transaction
            "DELETE key1",
            "GET key1",       // Expected output: NULL (deleted in nested transaction)
            "ROLLBACK",       // Rollback nested transaction, key1 should recover value2
            "GET key1",       // Expected output: value2
            "COMMIT",         // Commit the changes from first transaction
            "GET key1",       // Expected output: value2 persisted to the base store
            "ROLLBACK"        // Expected output: NO TRANSACTION (no active transaction)
        );

        // Process each command from the list
        for (String command : commands) {
            System.out.println("Command: " + command);
            kvStore.processCommand(command);
        }
    }
}