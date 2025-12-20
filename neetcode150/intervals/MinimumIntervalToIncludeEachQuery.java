package intervals;

import java.util.*;

public class MinimumIntervalToIncludeEachQuery {

    //Har query q ke liye batao smallest length ka interval jo q ko cover karta ho.
    //Agar koi interval cover nahi karta ? -1.

    //Queries ko left-to-right process karo,
    // intervals ko heap me daalte jao,
    // aur jo useless ho gaye unko nikaalte jao.

    public int[] minInterval(int[][] intervals, int[] queries) {
        //Jo interval pehle start hota hai, pehle process hoga
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // [size, rightEnd]
        //Sabse chhota interval size upar
        //Matlab top pe hamesha best answer
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Map<Integer, Integer> res = new HashMap<>();
        int i = 0;
        //Jaise jaise query badhegi, aur zyada intervals eligible honge
        int[] sortedQueries = Arrays.stream(queries).sorted().toArray();
        for (int q : sortedQueries) {
            //Agar interval ka start ? query
            //To wo potential candidate hai
            //Heap me daal do
            while (i < intervals.length && intervals[i][0] <= q) {
                int l = intervals[i][0];
                int r = intervals[i][1];
                int size = r - l + 1;
                minHeap.offer(new int[]{size, r});
                i++;
            }
            //Agar right < q ? query ke pehle hi khatam
            //Future me kabhi kaam ka nahi ? delete
            while (!minHeap.isEmpty() && minHeap.peek()[1] < q) {
                minHeap.poll();
            }
            //Heap empty ? -1
            //Warna ? smallest interval size
            res.put(q, minHeap.isEmpty() ? -1 : minHeap.peek()[0]);
        }
        int[] result = new int[queries.length];
        for (int j = 0; j < queries.length; j++) {
            result[j] = res.get(queries[j]);
        }
        return result;
    }

    //Sorting: O(n log n + q log q)
    //
    //Heap operations: O((n + q) log n)
    //
    //Overall: O((n + q) log n)
    //
    //Space: O(n)

    //“I sort queries and intervals. As queries increase,
    // I push all valid intervals into a min-heap by size,
    // remove expired intervals, and the heap top always gives the minimum interval covering the query.”
}
