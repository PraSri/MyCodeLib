package greedy.hand_of_straights;

import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int card : hand) {
            count.merge(card, 1, Integer::sum);
        }
        while (!count.isEmpty()) {
            Map.Entry<Integer, Integer> first = count.firstEntry();
            int start = first.getKey();
            int copies = first.getValue();
            for (int card = start; card < start + groupSize; card++) {
                Integer have = count.get(card);
                if (have == null || have < copies) {
                    return false;
                }
                if (have == copies) {
                    count.remove(card);
                } else {
                    count.put(card, have - copies);
                }
            }
        }
        return true;
    }
}
