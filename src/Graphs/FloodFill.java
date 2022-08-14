package Graphs;

public class FloodFill {

    /*
    * https://leetcode.com/problems/flood-fill/
    *
    * */


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int n = image.length;
        int m = image[0].length;
        int c = image[sr][sc];
        if(c != newColor) {
            dfs(image, sr, sc , n, m, c, newColor);
        }
        return image;
    }

    void dfs(int[][] a, int i, int j, int n, int m, int old, int c) {

        // check valid conditions
        if(i>=n || j>=m || i<0 || j<0 || a[i][j]==c) {
            return;
        }

        // only change color if cell is of old given color
        if(a[i][j] == old) {
            // assign new color to current cell
            a[i][j] = c;
            // fill color in 4 directions
            dfs(a,i+1,j,n,m,old,c); // bottom
            dfs(a,i-1,j,n,m,old,c); // top
            dfs(a,i,j+1,n,m,old,c); // right
            dfs(a,i,j-1,n,m,old,c); // left
        }

    }

}
