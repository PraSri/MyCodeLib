package mathandgeometry;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean rowZero = false;
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    if(i>0) {
                        matrix[i][0] = 0;
                    } else {
                        rowZero = true;
                    }
                }
            }
        }

        for(int i = 1;i<n;i++) {
            for(int j = 1;j<m;j++) {
                if(matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(matrix[0][0] == 0) {
            for(int i = 0;i<n;i++) {
                matrix[i][0] = 0;
            }
        }

        if(rowZero) {
            for(int j = 0;j<m;j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
