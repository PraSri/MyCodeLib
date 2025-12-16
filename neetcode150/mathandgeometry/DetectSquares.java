package mathandgeometry;

import java.util.*;

public class DetectSquares {

    /**
     * Question Description :
     *
     * Hume ek data structure banana hai jisme:
     *
     * add(point) ? point add karo
     *
     * count(point) ? diye gaye point ke saath kitne axis-aligned squares ban sakte hain
     *
     * Square ke sides x & y axis ke parallel hone chahiye.
     *
     *
     * High-Level Approach :
     *
     * Sab points store karo
     *
     * Jab count() aaye: Current point ko square ka bottom-left / top-right maan ke
     *
     * Baaki points ke saath square form ho raha hai ya nahi, check karo
     *
     * */


    private Map<List<Integer>, Integer> ptsCount;
    private List<List<Integer>> pts;

    public DetectSquares() {
        ptsCount = new HashMap<>();
        pts = new ArrayList<>();
    }

    public void add(int[] point) {
        List<Integer> p = Arrays.asList(point[0], point[1]);
        ptsCount.put(p, ptsCount.getOrDefault(p, 0) + 1);
        pts.add(p);
    }

    public int count(int[] point) {
        int res = 0;
        int px = point[0], py = point[1];
        for(List<Integer> pt : pts) {
            int x = pt.get(0), y = pt.get(1);
            if(Math.abs(py - y) != Math.abs(px - x) || x == px || y == py) {
                continue;
            }
            res += ptsCount.getOrDefault(Arrays.asList(x, py), 0) *
                    ptsCount.getOrDefault(Arrays.asList(px, y), 0);
        }
        return res;
    }
}
