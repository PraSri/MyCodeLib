package Rippling.kvstore;

// Define an interface for our Key-Value Store including transaction methods.
interface IKeyValueStore extends BaseKVStore {
    void beginTransaction();

    void commit();

    void rollback();
}
