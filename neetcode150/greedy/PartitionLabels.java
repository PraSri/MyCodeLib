package greedy;

import java.util.*;

public class PartitionLabels {

    //Tumhe ek string di hai.
    //Tumhe isko aise parts me todna hai ki:
    //
    //? Har character sirf ek hi part me aaye
    //? Aur tumhe maximum possible parts chahiye
    //? Output me har part ki length deni hai

    //Har character ka last occurrence pata kar lo
    //Phir left se scan karo aur jab tak current
    // part ke sab characters ka last index cover na ho jaaye — tab tak partition band mat karo.

    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }
        List<Integer> res = new ArrayList<>();
        int size = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            size++;
            end = Math.max(end, lastIndex.get(s.charAt(i)));
            //Jab tak current window ke sab characters future me repeat ho rahe hain, tab tak partition nahi tod sakte.
            if (i == end) {
                res.add(size);
                size = 0;
            }
        }
        return res;
    }

//     Input: s = "xyxxyzbzbb i   s  l"
//                 0123456789(10)(11)(12)

// lastIndex
// x = 3, y = 4, z = 7, b = 9, i = 10, s = 11, l = 12

// Output: [5, 5, 1, 1, 1]
// Explanation: The string can be split into ["xyxxy", "zbzbb", "i", "s", "l"].
}
