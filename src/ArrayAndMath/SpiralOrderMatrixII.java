package ArrayAndMath;

public class SpiralOrderMatrixII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Given an integer A, generate a square matrix filled with elements from 1 to
	 * A2 in spiral order.
	 */

	public int[][] generateMatrix(int n) {

		int[][] a = new int[n][n];

		int startRow = 0, endRow = n - 1;
		int startCol = 0, endCol = n - 1;

		int v = 1;

		while (startRow <= endRow && startCol <= endCol) {

			// fill start row

			for (int i = startCol; i <= endCol; i++) {
				a[startRow][i] = v;
				v++;
			}

			// increment startRow

			startRow++;

			// fill end col

			for (int i = startRow; i <= endRow; i++) {
				a[i][endCol] = v;
				v++;
			}

			// decrease endcol

			endCol--;

			if (endRow > startRow) {

				// fill end Row

				for (int i = endCol; i >= startCol; i--) {
					a[endRow][i] = v;
					v++;
				}

				// decrease endrow
				endRow--;

				// fill start col

				for (int i = endRow; i >= startRow; i--) {
					a[i][startCol] = v;
					v++;
				}

				// increase start col
				startCol++;

			}

		}
		return a;

	}

}
