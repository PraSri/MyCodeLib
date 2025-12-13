package graphs;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();

        q.add(beginWord);

        int len = 0;

        while (!q.isEmpty()) {
            len++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                for (int j = 0; j < curr.length(); j++) {
                    String alpha = "abcdefghijklmnopqrstuvwxyz";
                    for (int k = 0; k < alpha.length(); k++) {
                        char[] temp = curr.toCharArray();
                        temp[j] = alpha.charAt(k);
                        String newWord = new String(temp);
                        if (endWord.equals(newWord)) {
                            return len + 1;
                        }
                        if (set.contains(newWord)) {
                            q.offer(newWord);
                            set.remove(newWord);
                        }
                    }
                }
            }
        }
        return 0;
    }

}
