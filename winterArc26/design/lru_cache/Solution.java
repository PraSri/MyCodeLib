package design.lru_cache;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static class LRUCache {

        private static final class Node {
            final int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> map = new HashMap<>();
        private final Node head = new Node(0, 0); // most recent side
        private final Node tail = new Node(0, 0); // least recent side

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            unlink(node);
            pushFront(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                unlink(node);
                pushFront(node);
                return;
            }
            if (map.size() == capacity) {
                Node lru = tail.prev;
                unlink(lru);
                map.remove(lru.key);
            }
            node = new Node(key, value);
            map.put(key, node);
            pushFront(node);
        }

        private void unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void pushFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }
}
