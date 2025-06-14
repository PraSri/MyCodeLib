package bitwise_manipulations;

public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] image) {

        int n = image.length;
        int mid = (n + 1) / 2;

        for (int[] row : image) {
            for (int i = 0; i < mid; i++) {
                int temp = row[i] ^ 1;
                row[i] = row[row.length - i - 1] ^ 1;
                row[row.length - i - 1] = temp;
            }
        }
        return image;
    }
}
