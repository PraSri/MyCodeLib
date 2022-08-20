package leetcode75;

import java.util.*;

public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(topKFrequent(words, k));

        words = new String[]{"a", "a", "b", "b", "c"};
        k = 2;
        System.out.println(topKFrequent(words, k));
    }

    // return words sorted by frequency if same frequency then return in lexicographically sorted

    public static List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> frequency = new HashMap<>();

        for (String x : words) {
            frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        }

        // create a min heap on frequency & lexico to put all words

        // if frequency of two words are same then insert based on string compare of keys

        // string compare of keys is b.getKey().compareTo(a.getKey()) means it will put higher key on top
        // so first preference is freq, means lower freq will be on top then if same then higher string on top,
        // as we are building min heap, will pop if queue size crosses k then insert in resultant list in reverse order

        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
                (a, b) -> Objects.equals(a.getValue(), b.getValue())
                        ? b.getKey().compareTo(a.getKey())
                        : a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> me : frequency.entrySet()) {
            heap.offer(me);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<String> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            res.add(0, heap.poll().getKey());
        }

        return res;

    }
}
