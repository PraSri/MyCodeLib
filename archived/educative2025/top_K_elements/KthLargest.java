package top_K_elements;

import java.util.*;

class KthLargest {
    public PriorityQueue<Integer> topKHeap;
    public int k;

	// Constructor to initialize heap and add values in it
    public KthLargest(int k, int[] nums) {
        this.k = k;
        topKHeap = new PriorityQueue<>();

        for (int element : nums) {
            add(element);
        }
    }

	// Adds element in the heap and return the Kth largest
    public int add(int val) {
        if (topKHeap.size() < k) {
            topKHeap.offer(val);
        } else if (val > topKHeap.peek()) {
            topKHeap.poll();
            topKHeap.offer(val);
        }

        return topKHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 10};
        int[] temp = {3, 6, 9, 10};
        System.out.print("Initial stream: ");
        printArray(nums);
        System.out.println("\nk: " + 3);
        KthLargest kLargest = new KthLargest(3, nums);
        int[] val = {4, 7, 10, 8, 15};
        for (int i = 0; i < val.length; i++) {
            System.out.println("\tAdding a new number " + val[i] + " to the stream");
            temp = Arrays.copyOf(temp, temp.length + 1);
            temp[temp.length - 1] = val[i];
            System.out.print("\tNumber stream: ");
            printArray(temp);
            System.out.println("\n\tKth largest element in the stream: " + kLargest.add(val[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

	public static void printArray(int[] arr) {
		System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
		System.out.print("]");
    }
}
