package mathandgeometry;

public class RotateImage {

    public void rotate(int[][] matrix) {
        reverse(matrix);
        for(int i =0; i<matrix.length; i++) {
            for(int j =i;j<matrix[i].length;j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    private void reverse(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0;i<n/2;i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[n-i-1];
            matrix[n-i-1] = temp;
        }
    }

}
