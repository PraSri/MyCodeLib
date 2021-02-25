package GoldmanSachs;

public class Implement_CircularQueue_using_array {

	public static void main(String[] args) {

	}

	// https://leetcode.com/problems/design-circular-queue/

	private final int[] ary;
	private int front;
	private int rear;
	private int num;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public Implement_CircularQueue_using_array(int k) {
		ary = new int[k];
		front = 0;
		rear = 0;
		num = 0;
	}

	/**
	 * Insert an element into the circular queue. Return true if the operation is
	 * successful.
	 */
	public boolean enQueue(int value) {
		if (isFull())
			return false;
		ary[rear] = value;
		rear = (rear + 1) % ary.length;
		num++;
		return true;
	}

	/**
	 * Delete an element from the circular queue. Return true if the operation is
	 * successful.
	 */
	public boolean deQueue() {
		if (isEmpty())
			return false;
		front = (front + 1) % ary.length;
		num--;
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		if (isEmpty())
			return -1;
		return ary[front];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		if (isEmpty())
			return -1;
		return ary[rear == 0 ? ary.length - 1 : rear - 1];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return num == 0;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return num == ary.length;
	}
}
