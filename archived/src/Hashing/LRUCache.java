package Hashing;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	public static void main(String[] args) {

		LRUCache lRUCache = new LRUCache(1);
		lRUCache.put(2, 1); // cache is 2,1 size =1
		int x = lRUCache.get(2); // return 1
		lRUCache.put(3, 2);// cache = 3,2 size =1
		int y = lRUCache.get(2); // -1
		int z = lRUCache.get(3);// 2

		System.out.println(x + " " + y + " " + z);

	}

	public class DListNode {
		public int key, value;
		public DListNode prev, next;
	}

	private final int capacity;
	private int count;
	private final Map<Integer, DListNode> cache;
	private DListNode head, tail;

	public LRUCache(final int capacity) {
		this.capacity = capacity;
		this.count = 0;
		this.cache = new HashMap<Integer, LRUCache.DListNode>();
		this.head = new DListNode();
		this.tail = new DListNode();
		head.prev = null;
		tail.next = null;
		head.next = tail;
		tail.prev = head;
	}

	// add node after head
	private void addNode(DListNode node) {

		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;

	}

	private void removeNode(DListNode node) {

		DListNode prevNode = node.prev;
		DListNode nextNode = node.next;

		prevNode.next = nextNode;
		nextNode.prev = prevNode;

	}

	private void moveToHead(DListNode node) {
		this.removeNode(node);
		this.addNode(node);
	}

	private DListNode popTail() {
		DListNode lastNode = tail.prev;
		this.removeNode(lastNode);
		return lastNode;
	}

	public int get(int key) {

		// return value corresponding to key otherwise -1

		DListNode node = cache.get(key);

		if (node == null)
			return -1;
		// move the accessed node to head
		this.moveToHead(node);

		return node.value;

	}

	public void put(int key, int value) {

		// put it in cache if full then remove the least recently used node
		DListNode node = cache.get(key);

		if (node == null) {
			// if not present in cache then check its capacity is full if full then remove
			// the least recent used that is pop tail and insert and move to head

			DListNode newNode = new DListNode();
			newNode.key = key;
			newNode.value = value;

			this.cache.put(key, newNode);
			this.addNode(newNode);

			this.count++;

			if (count > capacity) {
				DListNode tailNode = this.popTail();
				this.cache.remove(tailNode.key);
				this.count--;
			}

		} else {

			// if this node is already present then update its value and move to head
			node.value = value;
			this.moveToHead(node);

		}

	}

}
