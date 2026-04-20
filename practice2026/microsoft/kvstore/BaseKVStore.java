package microsoft.kvstore;

public interface BaseKVStore<K, V> {
    V get(K key);

    void set(K key, V value);

    void deleteKey(K key);
}
