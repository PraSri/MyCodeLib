import java.util.*;

class KthSmallest {

    public static int kthSmallestElement(int[][] matrix, int k) {
        // storing the number of rows in the matrix to use it in later
        int rowCount = matrix.length;
      
        // declaring a min-heap to keep track of smallest elements
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (int i = 0; i < Math.min(rowCount, k); i++) {
          
            // pushing the first element of each row in the min-heap
          
            // The offer() method pushes an element into an existing heap
            // in such a way that the heap property is maintained.
          // heap takes tuple - value, listIndex, valueIndex
            minHeap.offer(new int[]{matrix[i][0], i, 0});
        }
        
        int numbersChecked = 0;
        int smallestElement = 0;
      
        // iterating over the elements pushed in our minHeap
        while (!minHeap.isEmpty()) {
          
            // get the smallest number from top of heap and its corresponding row and column
            int[] curr = minHeap.poll();
            smallestElement = curr[0];
            int rowIndex = curr[1];
            int colIndex = curr[2];
          
            numbersChecked++;
          
            // when numbersChecked equals k, we'll return smallestElement
            if (numbersChecked == k) {
                break;
            }
          
            // if the current element has a next element in its row,
            // add the next element of that row to the minHeap
          
            if (colIndex + 1 < matrix[rowIndex].length) {
                minHeap.offer(new int[]{matrix[rowIndex][colIndex + 1], rowIndex, colIndex + 1});
            }
        }
      
        // return the Kth smallest number found in the matrix
        return smallestElement;
    }
    
    // Driver code
    public static void main(String[] args) {
      int[][][] matrix = {{{2, 6, 8}, 
                {3, 7, 10}, 
                {5, 8, 11}}, 
            
                {{1, 2, 3}, 
                {4, 5, 6}, 
                {7, 8, 9}},
              
                {{5}},

                {{2, 5, 7, 9, 10},
                {4, 6, 8, 12, 14},
                {11, 13, 16, 18, 20},
                {15, 17, 21, 24, 26},
                {19, 22, 23, 25, 28}},

                {{3, 5, 7, 9, 11, 13},
                {6, 8, 10, 12, 14, 16},
                {15, 17, 19, 21, 23, 25},
                {18, 20, 22, 24, 26, 28},
                {27, 29, 31, 33, 35, 37},
                {30, 32, 34, 36, 38, 40}}};
      
      int [] k = {3, 4, 1, 10, 15};
      for(int i=0; i<k.length; i++){
        System.out.print(i+1);
        System.out.println(".\tInput matrix: "+ Arrays.deepToString(matrix[i]));
        System.out.println("\tK =  "+k[i]);
        System.out.println("\tKth smallest number in the matrix is: "+kthSmallestElement(matrix[i], k[i]));
        System.out.println(new String(new char[100]).replace('\0', '-'));
      }
      
    }
}
