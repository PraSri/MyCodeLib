package greedy;

import java.util.*;

public class HandOfStraights {

    //Time & Space Complexity
    //
    //Time: O(n * groupSize) (worst case)
    //
    //Space: O(n) (map ke liye)


    //“Hum frequency map banate hain, phir har number ko smallest
    // possible start maan ke continuous groupSize ka group banate hain.
    // Agar kahin bhi required card missing hua, toh answer false.”

    public boolean isNStraightHand(int[] hand, int groupSize) {

        int n = hand.length;

        if (n % groupSize != 0) {
            return false;
        }

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

}
