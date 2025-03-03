import java.util.PriorityQueue;

class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) 
    {
        // Max heap to store character counts
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);

        // Add characters to heap if their count is greater than 0
        if (a > 0) pq.add(new Pair(a, 'a'));
        if (b > 0) pq.add(new Pair(b, 'b'));
        if (c > 0) pq.add(new Pair(c, 'c'));

        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty()) {
            Pair top = pq.poll();

            // Check if adding this character violates the "no three consecutive" rule
            int len = result.length();
            if (len >= 2 && result.charAt(len - 1) == top.ch && result.charAt(len - 2) == top.ch) {
                // If no other characters are available, break
                if (pq.isEmpty()) break;

                // Use the next most frequent character instead
                Pair second = pq.poll();
                result.append(second.ch);

                // Reduce its count and add it back if more are available
                if (--second.count > 0) pq.add(second);

                // Push the original character back for future use
                pq.add(top);
            } else {
                // If no violation, add the current character to result
                result.append(top.ch);
                if (--top.count > 0) pq.add(top);
            }
        }

        return result.toString();
    }

    // Helper class for storing character counts
    static class Pair {
        int count;
        char ch;

        Pair(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }
    }

    // Driver code
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[][] testCases = {
            {1, 1, 7},
            {2, 2, 1},
            {7, 2, 0},
            {0, 0, 0},
            {10, 5, 3},
            {3, 3, 3}
        };

        int testCaseNum = 1;
        for (int[] testCase : testCases) {
            int a = testCase[0], b = testCase[1], c = testCase[2];
            System.out.println(testCaseNum++ + ".\t a: " + a + ", b: " + b + ", c: " + c);
            String result = solution.longestDiverseString(a, b, c);
            System.out.println("\n\t Longest Happy String: " + result);
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}
