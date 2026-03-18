package sort_and_search;

public class HIndex {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] papers = new int[len + 1];
        for (int c : citations) {
            papers[Math.min(len, c)] += 1;
        }
        int k = len;
        int s = papers[len];
        while (k > s) {
            k--;
            s += papers[k];
        }
        return k;
    }
}
