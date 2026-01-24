package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Divide Array in Sets of K Consecutive Numbers -
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/
 *
 */

public class HandOfStraights {

    //Time & Space Complexity
    //
    //Time: O(n * groupSize) (worst case)
    //
    //Space: O(n) (map ke liye)


    //Hum frequency map banate hain, phir har number ko smallest
    // possible start maan ke continuous groupSize ka group banate hain.
    // Agar kahin bhi required card missing hua, toh answer false.

    public boolean isNStraightHand(int[] hand, int groupSize) {

        int n = hand.length;

        // agr number of hands, start me hi equal group sizes me divide nhi ho pa rhe, toh false do
        if (n % groupSize != 0) {
            return false;
        }

        // frequency map rkhna, har baar ek hand uthayege aur yaha se frequency decrease krege,
        // frequency map availability bhi bta rha hai
        Map<Integer, Integer> count = new HashMap<>();

        //Har card ka count rakho (frequency map)
        for (int num : hand) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        for (int num : hand) {

            //Har number ko possible starting point maan ke
            // kyuki agar possible hai, iska sequence (-1 karke) present hoga
            int start = num;

            //Usse continuous group banane ki try karo
            while (count.getOrDefault(start - 1, 0) > 0) {
                start--;
            }

            while (start <= num) {
                while (count.getOrDefault(start, 0) > 0) {
                    for (int i = start; i < start + groupSize; i++) {
                        if (count.getOrDefault(i, 0) == 0) {
                            return false;
                        }
                        count.put(i, count.get(i) - 1);
                    }
                }
                start++;
            }
        }

        return true;
    }

    // priority queue solution

    public boolean isNStraightHandPq(int[] hands, int groupSize) {
        int n = hands.length;
        if (n % groupSize != 0) {
            return false;
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int x : hands) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(count.keySet());
        while (!minHeap.isEmpty()) {
            int first = minHeap.peek();
            for (int i = first; i < first + groupSize; i++) {
                if (!count.containsKey(i)) {
                    return false;
                }
                count.put(i, count.getOrDefault(i, 0) - 1);
                if (count.get(i) == 0) {
                    if (i != minHeap.peek()) {
                        return false;
                    }
                    minHeap.poll();
                }
            }
        }

        return true;
    }


    /**
     * Divide Array in Sets of K Consecutive Numbers -
     * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description
     */
    public static class DivideArrayInSetsOfKConsecutiveNumbers {
    }
}
