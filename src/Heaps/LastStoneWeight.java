// https://leetcode.com/problems/last-stone-weight/

 public int lastStoneWeight(int[] stones) {
        
        int n = stones.length;
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i<n; i++) {
            maxHeap.add(stones[i]);
        }
        
        while(maxHeap.size()>1) {
            int s1 = maxHeap.poll();
            int s2 = maxHeap.poll();
            maxHeap.add(Math.abs(s1-s2));
        }
        
        return maxHeap.poll();
        
    }
