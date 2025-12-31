package linkedlist;

import java.util.*;

/**LFU Cache (Hard)
https://leetcode.com/problems/lfu-cache/

Design In-Memory File System (Hard)
https://leetcode.com/problems/design-in-memory-file-system/

Design Compressed String Iterator (Easy)
https://leetcode.com/problems/design-compressed-string-iterator/

Design Most Recently Used Queue (Medium)
https://leetcode.com/problems/design-most-recently-used-queue/*/

public class LruCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    Node tail = new Node(0, 0);
    Node head = new Node(0, 0);
    Map<Integer, Node> map = new HashMap<>();

    int capacity;

    public LruCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void insert(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key, value));

    }

    /**
     * <a href="https://leetcode.com/problems/lfu-cache/">LeetCode - LFU Cache</a>
     */
    public static class LfuCache {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/design-in-memory-file-system/">LeetCode - Design In-Memory File System</a>
     */
    public static class DesignInMemoryFileSystem {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/design-compressed-string-iterator/">LeetCode - Design Compressed String Iterator</a>
     */
    public static class DesignCompressedStringIterator {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/design-most-recently-used-queue/">LeetCode - Design Most Recently Used Queue</a>
     */
    public static class DesignMostRecentlyUsedQueue {
        // placeholder
    }

}
