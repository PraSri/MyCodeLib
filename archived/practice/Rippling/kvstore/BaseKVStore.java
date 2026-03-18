package Rippling.kvstore;

public interface BaseKVStore {
    String get(String key);

    void set(String key, String value);

    void deleteKey(String key);
}
