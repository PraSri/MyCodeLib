package greedy.task_scheduler;

public class Solution {

    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        for (char t : tasks) {
            maxFreq = Math.max(maxFreq, ++freq[t - 'A']);
        }
        int tiedAtMax = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                tiedAtMax++;
            }
        }
        int framed = (maxFreq - 1) * (n + 1) + tiedAtMax;
        return Math.max(tasks.length, framed);
    }
}
