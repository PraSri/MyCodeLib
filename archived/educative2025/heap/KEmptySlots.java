package heap;

import java.util.Deque;
import java.util.LinkedList;

public class KEmptySlots {

    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] days = new int[n];
        // build days array
        for (int i = 0; i < n; i++) {
            days[bulbs[i] - 1] = 1 + 1;
        }
        MinHeap pq = new MinHeap();
        int result = n;
        for (int i = 0; i < n; i++) {
            pq.append(days[i]);
            if (i >= k && i < n - 1) {
                pq.popLeft();
                if (k == 0 || (days[i - k] < pq.min() && pq.min() > days[i + 1])) {
                    int ans = Math.max(days[i - k], days[i + 1]);
                    result = Math.min(result, ans);
                }
            }
        }
        return result < n ? result : -1;
    }

    public int kEmptySlots2(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] days = new int[n];

        // Step 1: Build the days array
        for (int day = 0; day < n; day++) {
            days[bulbs[day] - 1] = day + 1;
        }

        // Step 2: Initialize MinHeap and result
        MinHeap pq = new MinHeap();
        int result = n;

        // Step 3: Iterate over days
        for (int i = 0; i < n; i++) {
            pq.append(days[i]);

            if (i >= k && i < n - 1) {
                pq.popLeft();

                if (k == 0 || (days[i - k] < pq.min() && pq.min() > days[i + 1])) {
                    int ans = Math.max(days[i - k], days[i + 1]);
                    result = Math.min(result, ans);
                }
            }
        }

        return result < n ? result : -1;
    }


    static class MinHeap2 {
        Deque<Integer> dq;
        Deque<Integer> mins;

        public MinHeap2() {
            dq = new LinkedList<>();
            mins = new LinkedList<>();
        }

        public void append(Integer x) {
            dq.addLast(x);
            while (!mins.isEmpty() && x < mins.peekLast()) {
                mins.pollLast();
            }
            mins.addLast(x);
        }

        public void popLeft() {
            int x = dq.pollFirst();
            if (!mins.isEmpty() && mins.peekFirst() == x) {
                mins.pollFirst();
            }
        }

        public int min() {
            return mins.peekFirst();
        }

        public int size() {
            return dq.size();
        }
    }

    static class MinHeap {
        Deque<Integer> dq;
        Deque<Integer> mins;

        public MinHeap() {
            dq = new LinkedList<>();
            mins = new LinkedList<>();
        }

        public void append(int x) {
            dq.addLast(x);
            while (!mins.isEmpty() && x < mins.peekLast()) {
                mins.pollLast();
            }
            mins.addLast(x);
        }

        public void popLeft() {
            int x = dq.pollFirst();
            if (!mins.isEmpty() && mins.peekFirst() == x) {
                mins.pollFirst();
            }
        }

        public int min() {
            return mins.peekFirst();
        }

        public int size() {
            return dq.size();
        }
    }


    public static void main(String[] args) {
        int[][] testBulbs = {{1, 3, 2}, {1, 2, 3}, {2, 5, 1, 4, 3}, {3, 1, 5, 4, 2}, {2, 4, 1, 3}};

        int[] ks = {1, 1, 1, 1, 0};

        for (int i = 0; i < testBulbs.length; i++) {
            int[] bulbs = testBulbs[i];
            int k = ks[i];
            System.out.print((i + 1) + ".\tbulbs: [ ");
            for (int b : bulbs) System.out.print(b + " ");
            System.out.println("], k: " + k);
            KEmptySlots obj = new KEmptySlots();
            int result = obj.kEmptySlots2(bulbs, k);
            System.out.println("\tEarliest Day: " + result);
            System.out.println("-".repeat(100));
        }
    }
}
