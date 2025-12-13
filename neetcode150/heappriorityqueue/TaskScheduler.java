package heappriorityqueue;

import java.util.*;

public class TaskScheduler {

    /**
     * You are given an array of CPU tasks ``tasks``, where tasks[i] is an uppercase english character from A to Z.
     * You are also given an integer n.
     *
     * Each CPU cycle allows the completion of a single task, and tasks may be completed in any order.
     *
     * The only constraint is that identical tasks must be separated by at least n CPU cycles, to cooldown the CPU.
     *
     * Return the minimum number of CPU cycles required to complete all tasks.
     *
     * Input: tasks = ["X","X","Y","Y"], n = 2
     *
     * Output: 5
     *
     * ek jaise task, dobara hone ke liye kam se kam n CPU cycles ka gap hona chahiye
     * minimum no. of cpu cycles batao jisme sab tasks complete ho jae
     *
     *
     * x y _ x y
     * total 5 cpu cycles
     *
     * */

    public int leastInterval(char[] tasks, int n) {
        //Input: tasks = ["X","X","Y","Y", "Z"], n = 3
        // x y z _ x y = 6

        // Input: tasks = ["A","A","A","B","C"], n = 3
        // a b c _ a _ _ _ a = 9
        // a = 3, b = 1, c = 1
        // a _ _ _ a _ _ _ a

        // store frequency of each task
        // find max fre
        // find max count of max fre
        // cal time
        // ans = max(tasks.len, time)
        // Sabse zyada baar aane wala task (max frequency task) hi overall schedule ko dominate ?????.
        int[] count = new int[26];

        for(char task: tasks) {
            count[task - 'A']++;
        }
        int maxf = Arrays.stream(count).max().getAsInt();
        int maxCount = 0;
        for(int i : count) {
            if(i == maxf) {
                maxCount++;
            }
        }
        // (maxf - 1) — Yeh represent karta hai gaps
        // A = 3
        // A _ A _ A => 2 gaps
        // (n + 1) — Har gap ka ideal size
        // [A][ _ _ _ ][A][ _ _ _ ][A] => Ek group = A + n other slots = (n + 1) size block
        // + maxCount — Last row me jitne max-frequency tasks honge
        int time = (maxf - 1) * (n+1) + maxCount;
        return Math.max(time, tasks.length);
    }

}
