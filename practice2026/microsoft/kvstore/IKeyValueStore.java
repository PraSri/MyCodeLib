package microsoft.kvstore;

// Define an interface for our Key-Value Store including transaction methods.
interface IKeyValueStore<K, V> extends BaseKVStore<K, V> {
    void beginTransaction();

    void commit();

    void rollback();
}
