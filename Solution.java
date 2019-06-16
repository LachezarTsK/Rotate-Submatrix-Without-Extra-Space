import java.util.Scanner;

public class Solution {
	private static int boardSide = 8;
	private static int[][] board = new int[boardSide][boardSide];
	private static boolean rotated;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int startRow = scanner.nextInt();
		int startColumn = scanner.nextInt();
		int length = scanner.nextInt();
		scanner.close();

		initializeBoard();
		printBoard();
		rotateSubarray_90DegreesClockwise(startRow, startColumn, length);
		printBoard();
	}

	/**
	 * The method takes as input the row, the column (actual numbers, not indexes)
	 * and the length (including start position) of a submatrix, and rotates it at
	 * 90 degrees, clockwise.
	 * 
	 * The method processes the values of the submatrix in a series of concentric
	 * squares, starting from the outermost one.
	 */
	public static void rotateSubarray_90DegreesClockwise(int startRow, int startColumn, int length) {

		/**
		 * Variable "rotated" is implemented just for anesthetic purposes and is applied
		 * when printing the results.
		 */
		rotated = true;
		/**
		 * The submatrix could start at any point, thus it is imperative to keep track
		 * of the difference between the start row and column.
		 */
		int difference = startColumn - startRow;
		/**
		 * Converts the start row and column into indexes.
		 */
		startRow--;
		startColumn--;

		int endRow = startRow + length - 1;
		int endColumn = startColumn + length - 1;

		for (int row = startRow; row <= endRow; row++) {
			for (int column = row + difference; column < endColumn; column++) {

				int store_lastCopy = board[row][column];

				int row_firstCopy = endRow - (column - startColumn);
				int column_firstCopy = row + difference;
				board[row][column] = board[row_firstCopy][column_firstCopy];

				int row_secondCopy = endRow - (column_firstCopy - startColumn);
				int column_secondCopy = row_firstCopy + difference;
				board[row_firstCopy][column_firstCopy] = board[row_secondCopy][column_secondCopy];

				int row_thirdCopy = endRow - (column_secondCopy - startColumn);
				int column_thirdCopy = row_secondCopy + difference;
				board[row_secondCopy][column_secondCopy] = board[row_thirdCopy][column_thirdCopy];

				board[row_thirdCopy][column_thirdCopy] = store_lastCopy;
			}
			/**
			 * Transition to the next inner square.
			 */
			startRow++;
			startColumn++;
			endRow--;
			endColumn--;
		}

	}

	public static void initializeBoard() {
		board = new int[boardSide][boardSide];
		int value = 0;
		for (int i = 0; i < boardSide; i++) {
			for (int j = 0; j < boardSide; j++) {
				board[i][j] = value;
				value++;
			}
		}
	}

	public static void printBoard() {
		String title = rotated ? "board after rotation" : "original board";
		System.out.println(title);

		for (int i = 0; i < boardSide; i++) {
			for (int j = 0; j < boardSide; j++) {
				String value = board[i][j] + " ";
				if (board[i][j] < 10) {
					value = "0" + board[i][j] + " ";
				}
				System.out.print(value);
			}
			System.out.println();
		}
		System.out.println();
	}
}
