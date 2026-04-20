package microsoft.kvstore;

import java.util.Map;

public class LRUEviction<K, V> implements EvictionStrategy<K, V> {
    @Override
    public void onPut(String K, V entry) {
    }

    @Override
    public void onGet(String K, V entry) {
    }

    @Override
    public String evict(Map<K, V> store, int maxSize) {
        return "";
    }
}
