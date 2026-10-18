package serialization.encode_and_decode_strings;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int hash = str.indexOf('#', i);
            int length = Integer.parseInt(str.substring(i, hash));
            int start = hash + 1;
            result.add(str.substring(start, start + length));
            i = start + length;
        }
        return result;
    }
}
