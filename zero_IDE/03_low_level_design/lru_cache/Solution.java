import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Low-Level Design: LRU Cache (Bitgo Problem)
 *
 * Requirements:
 * 1. O(1) get(K key) and put(K key, V value).
 * 2. Evict least-recently used item when capacity is exceeded.
 * 3. Thread-safe operations using ReentrantReadWriteLock.
 */
public class Solution {

    public static class LRUCache<K, V> {
        private static class Node<K, V> {
            K key;
            V val;
            Node<K, V> prev;
            Node<K, V> next;

            Node(K key, V val) {
                this.key = key;
                this.val = val;
            }
        }

        private final int capacity;
        private final Map<K, Node<K, V>> map;
        private final Node<K, V> head; // Dummy head (Most Recently Used)
        private final Node<K, V> tail; // Dummy tail (Least Recently Used)
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public LRUCache(int capacity) {
            if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.head = new Node<>(null, null);
            this.tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        public V get(K key) {
            lock.writeLock().lock(); // Read + move to head mutates DLL structure
            try {
                Node<K, V> node = map.get(key);
                if (node == null) return null;
                moveToHead(node);
                return node.val;
            } finally {
                lock.writeLock().unlock();
            }
        }

        public void put(K key, V value) {
            lock.writeLock().lock();
            try {
                Node<K, V> existing = map.get(key);
                if (existing != null) {
                    existing.val = value;
                    moveToHead(existing);
                } else {
                    if (map.size() >= capacity) {
                        Node<K, V> lru = removeTail();
                        map.remove(lru.key);
                    }
                    Node<K, V> newNode = new Node<>(key, value);
                    map.put(key, newNode);
                    addToHead(newNode);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }

        private void addToHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(Node<K, V> node) {
            removeNode(node);
            addToHead(node);
        }

        private Node<K, V> removeTail() {
            Node<K, V> lru = tail.prev;
            removeNode(lru);
            return lru;
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "one");
        cache.put(2, "two");
        System.out.println("Get 1: " + cache.get(1)); // returns "one" and moves 1 to MRU
        cache.put(3, "three"); // evicts key 2
        System.out.println("Get 2 (evicted): " + cache.get(2)); // null
        cache.put(4, "four");  // evicts key 1
        System.out.println("Get 1 (evicted): " + cache.get(1)); // null
        System.out.println("Get 3: " + cache.get(3)); // "three"
        System.out.println("Get 4: " + cache.get(4)); // "four"
    }
}
