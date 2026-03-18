package graphs;

import java.util.*;
import java.util.List;

public class LuckyNumberInAMatrix {

    public List<Integer> luckyNumbers(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int rLargestMin = Integer.MIN_VALUE;

        for(int i = 0;i<n;i++) {
            int rMin = Arrays.stream(matrix[i]).min().getAsInt();
            rLargestMin = Math.max(rMin, rLargestMin);
        }

        int cSmallestMax = Integer.MAX_VALUE;

        for(int i =0; i<n;i++) {
           int cMax = Integer.MIN_VALUE;
           for(int j =0;j<m;j++) {
               cMax = Math.max(cMax, matrix[i][j]);
           }
           cSmallestMax = Math.min(cMax, cSmallestMax);
        }

        if(rLargestMin == cSmallestMax)
            return Collections.singletonList(rLargestMin);

        return new ArrayList<>();
    }

}
