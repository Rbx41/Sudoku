package sudoku;

public class CheckSudokuBoardConfiguration {

	 // Checks for duplicates in the current row
    static boolean validRow(int[][] mat, int row) {

        // Visited array to track occurrences
        int[] vis = new int[10];
        
        for (int i = 0; i < 9; i++) {

            // If the cell is not empty
            if (mat[row][i] != 0) {
                int val = mat[row][i];

                // If duplicate is found
                if (vis[val] != 0)
                    return false;

                // Mark the number as visited
                vis[val]++;
            }
        }
        return true;
    }

    // Checks for duplicates in the current column
    static boolean colValid(int[][] mat, int col) {

        // Visited array to track occurrences
        int[] vis = new int[10];
        
        for (int i = 0; i < 9; i++) {

            // If the cell is not empty
            if (mat[i][col] != 0) {
                int val = mat[i][col];

                // If duplicate is found
                if (vis[val] != 0)
                    return false;

                // Mark the number as visited
                vis[val]++;
            }
        }
        return true;
    }

    // Checks for duplicates in the current 3x3 submatrix
    static boolean subMatValid(int[][] mat, int startRow,
                                         int startCol) {

        // Visited array to track occurrences
        int[] vis = new int[10];
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                // Current element in the submatrix
                int curr = mat[row + startRow][col + startCol];
                
                // If the cell is not empty
                if (curr != 0) {

                    // If duplicate is found
                    if (vis[curr] != 0)
                        return false;

                    // Mark the number as visited
                    vis[curr]++;
                }
            }
        }
        return true;
    }

    // Validates the Sudoku board
    public static boolean isValid(int[][] mat) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                // Check if the row, column,
                // or submatrix is invalid
                if (!validRow(mat, i) || !colValid(mat, j)
                    || !subMatValid(mat, i - i % 3, j - j % 3))
                    return false;
            }
        }
        return true; 
    }
	
	
	
	
	
}
