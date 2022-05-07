package String;

public class PartitionLabels {

 // https://leetcode.com/problems/partition-labels/discuss/1868842/JavaC%2B%2B-VISUALLY-EXPLAINEDDDDD!!
    
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] lastFound = new int[26];
        for(int i = 0;i<n;i++){
            char curr = s.charAt(i);
            lastFound[curr-'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int maxPartitionLen = 0, prevPartitionLen = -1;
        
        for(int i = 0;i<n;i++){
            char curr = s.charAt(i);
            int lastfoundIndex = lastFound[curr-'a'];
            maxPartitionLen = Math.max(maxPartitionLen, lastfoundIndex);
            if(maxPartitionLen==i) {
                int partitionLen = maxPartitionLen - prevPartitionLen;
                res.add(partitionLen);
                prevPartitionLen = maxPartitionLen;
            }
        }
        
        return res;
    }

}
