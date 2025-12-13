package advancedgraphs;

import java.util.*;

public class SwimInRisingWater {

    // initialize visited boolean 2D array
    // min heap on int[] sorted on 0th index
    // each element of min heap will be {grid value, row index, col index}
    // add 0th grid element in heap, mark it visited
    // loop on heap till its empty
    // pop one element from heap
    // if its row & col reaches end of grid,
    // return grid value (0th index of heap element)
    // else iterate in all 4 directions
    // add in queue if valid conditions
    // mark each new node visited and add in heap
    // heap 0th index - value is max of curr value or new node value
    // if loop ends, return N*N

    // Min-Heap + BFS (Dijkstra style)


    //Aapko ek N x N grid diya gaya hai.
    //Grid ka har cell ek height batata hai.
    //
    //Aap start karte ho top-left (0,0) se
    //
    //Aapko pahunchna hai bottom-right (N-1, N-1) tak
    //
    //Aap 4 directions me move kar sakte ho: up, down, left, right
    //
    //Yaha ek twist hai:
    //
    //Water level time t ke saath badhta hai
    //
    //Aap tabhi kisi cell me ja sakte ho jab
    //t ? us cell ki height

    //Jab aap move karte ho, aapka raasta tabhi valid hai jab aap jinke upar step kar rahe ho unki max height ? t ho.
    //
    //Iska matlab yeh problem basically bolti hai:
    //
    //“Woh path dhoondo jisme max height sabse kam ho.”

    /***
     *
     *
     * Code ka short view (jo solution karta hai)
     *
     * Hum min-heap (PriorityQueue) use karte hain jisme nodes store hote hain: {x, y, time}.
     *
     * time ka matlab: is path pe ab tak jo maximum height aya, uska value (yehi eventual answer ko decide karega).
     *
     * Hum BFS-type expansion karte hain lekin priority ke hisaab se —
     * hume hamesha us node se expand karna hai jiska time sabse chhota ho (Dijkstra-style).
     *
     * Jab hum bottom-right cell (n-1,n-1) pop karte hain, uska time hi minimum required seconds (answer) hota hai.
     *
     *
     *
     * */

    public int swimInWater(int[][] grid) {
        int n = grid.length;

        boolean[][] visited = new boolean[n][n];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        pq.add(new Node(0, 0, grid[0][0]));

        visited[0][0] = true;

        while (!pq.isEmpty()) {

            Node node = pq.poll();

            int x = node.x;
            int y = node.y;

            int time = node.time;

            if (x == n - 1 && y == n - 1) {
                return time;
            }

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;

                //Yaha Math.max(time, grid[nx][ny]) kyun?
                //
                //time represent karta hai path pe ab tak ka maximum height.
                // Agar next cell ki height usse zyada hai,
                // toh total path ke liye new max woh hi ho jayega. Isi max ko hum propagate karte hain.
                pq.offer(new Node(nx, ny, Math.max(time, grid[nx][ny])));
            }
        }
        return n * n;
    }

    public static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }


}
