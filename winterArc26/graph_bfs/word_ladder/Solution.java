package graph_bfs.word_ladder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> unvisited = new HashSet<>(wordList);
        if (!unvisited.contains(endWord)) {
            return 0;
        }
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        unvisited.remove(beginWord);
        for (int steps = 1; !queue.isEmpty(); steps++) {
            for (int size = queue.size(); size > 0; size--) {
                char[] word = queue.poll().toCharArray();
                for (int i = 0; i < word.length; i++) {
                    char original = word[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) {
                            continue;
                        }
                        word[i] = c;
                        String next = new String(word);
                        if (unvisited.remove(next)) {
                            if (next.equals(endWord)) {
                                return steps + 1;
                            }
                            queue.offer(next);
                        }
                    }
                    word[i] = original;
                }
            }
        }
        return 0;
    }
}
