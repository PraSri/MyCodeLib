package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddresses {

    private int n;
    private String s;
    private LinkedList<String> segments;
    private List<String> results;

    public List<String> restoreIpAddresses(String str) {

        n = str.length();
        s = str;
        // save the possible segments of IP, each separated by dots
        segments = new LinkedList<>();
        // contains valid segments concatenated by dots
        results = new ArrayList<>();
        // this is backtrack function which take previous dot position as -1 & number of dots
        backtrack(-1, 3);
        return results;
    }

    private void backtrack(int prevDot, int dots) {
        // current dot could be placed in range of prevDot + 1 to prevDot + 4
        // also dot couldn't be placed outside of string
        int maxPos = Math.min(n-1, prevDot+4);
        for(int currDot = prevDot+1; currDot<maxPos; currDot++) {
            String segment = s.substring(prevDot+1, currDot+1);
            if(valid(segment)) {
                segments.add(segment); // place dot
                // if all 3 dots are placed, see if its applicable for final results
                if(dots-1==0) {
                    updateSegment(currDot);
                }else {
                    // continue backtrack till dots are exhaust or curr dot position reaches to end
                    backtrack(currDot, dots-1);
                }
                // remove the last placed
                segments.removeLast();
            }
        }
    }

    private boolean valid(String segment) {

        int m = segment.length();
        if(m>3)
            return false;
        return segment.charAt(0)!='0' ? Integer.parseInt(segment)<=255 : (m==1);

    }

    private void updateSegment(int currDot) {
        String segment = s.substring(currDot + 1, n);
        if(valid(segment)) {
            segments.add(segment);
            String ip = String.join(".", segments);
            results.add(ip);
            segments.removeLast();
        }

    }

}
