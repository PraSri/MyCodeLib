package topological_sort.alien_dictionary;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public String foreignDictionary(String[] words) {
        boolean[][] edge = new boolean[26][26];
        int[] indegree = new int[26];
        boolean[] present = new boolean[26];
        for (String w : words) {
            for (char c : w.toCharArray()) {
                present[c - 'a'] = true;
            }
        }
        for (int i = 0; i + 1 < words.length; i++) {
            String a = words[i];
            String b = words[i + 1];
            int len = Math.min(a.length(), b.length());
            int j = 0;
            while (j < len && a.charAt(j) == b.charAt(j)) {
                j++;
            }
            if (j == len) {
                if (a.length() > b.length()) {
                    return "";
                }
                continue;
            }
            int u = a.charAt(j) - 'a';
            int v = b.charAt(j) - 'a';
            if (!edge[u][v]) {
                edge[u][v] = true;
                indegree[v]++;
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        int letters = 0;
        for (int c = 0; c < 26; c++) {
            if (present[c]) {
                letters++;
                if (indegree[c] == 0) {
                    queue.offer(c);
                }
            }
        }
        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.append((char) ('a' + u));
            for (int v = 0; v < 26; v++) {
                if (edge[u][v] && --indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return order.length() == letters ? order.toString() : "";
    }
}
