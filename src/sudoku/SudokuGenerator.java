package sudoku;

import java.util.Random;

public class SudokuGenerator {
	
	private int [][] sudoku;
	
	public final int LATWY = 1;
	public final int SREDNI = 2;
	public final int TRUDNY = 3;
	    
	    

    static boolean unUsedInBox(int[][] grid, int rowStart, int colStart, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[rowStart + i][colStart + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    static void fillBox(int[][] grid, int row, int col) {
        Random rand = new Random();
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = rand.nextInt(9) + 1;
                } while (!unUsedInBox(grid, row, col, num));
                grid[row + i][col + j] = num;
            }
        }
    }

    static boolean unUsedInRow(int[][] grid, int i, int num) {
        for (int j = 0; j < 9; j++) {
            if (grid[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    static boolean unUsedInCol(int[][] grid, int j, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    static boolean checkIfSafe(int[][] grid, int i, int j, int num) {
        return (unUsedInRow(grid, i, num) && unUsedInCol(grid, j, num)
                && unUsedInBox(grid, i - i % 3, j - j % 3, num));
    }

    static void fillDiagonal(int[][] grid) {
        for (int i = 0; i < 9; i += 3) {
            fillBox(grid, i, i);
        }
    }


    static boolean fillRemaining(int[][] grid, int i, int j) {

        if (i == 9) {
            return true;
        }
        if (j == 9) {
            return fillRemaining(grid, i+1, 0);
        }
        if (grid[i][j] != 0) {
            return fillRemaining(grid, i, j+1);
        }

        for (int num = 1; num <= 9; num++) {
            if (checkIfSafe(grid, i, j, num)) {
                grid[i][j] = num;
                if (fillRemaining(grid, i, j+1)) {
                    return true;
                }
                grid[i][j] = 0;
            }
        }
        return false;
    }

    static void removeKDigits(int[][] grid, int k) {
        Random rand = new Random();
        while (k > 0) {
            int cellId = rand.nextInt(81);

            int i = cellId / 9;
            int j = cellId % 9;

            if (grid[i][j] != 0) {
                grid[i][j] = 0;
                k--;
            }
        }
    }


    static int[][] sudokuGenerator(int k) {
        int[][] grid = new int[9][9];

        fillDiagonal(grid);

        fillRemaining(grid, 0, 0);

        removeKDigits(grid, k);
        return grid;

    }
    
    
    public SudokuGenerator(int level){
    	int k;
    	
    	if (level == TRUDNY) {
    		k = 50;	
    	}
    	else if (level == SREDNI) {
    		k = 35;
    	}
    	else { // Latwy
    		k = 15;
    	}
    	
    	Random rand = new Random();
        sudoku = sudokuGenerator(k);

        for (int[] row : sudoku) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    	
    	
    }
    
    /*

    public static void main(String[] args) {
        Random rand = new Random();
        int k = 50;
        int [][] sudoku = sudokuGenerator(k);

        for (int[] row : sudoku) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

    }
    */
    
    public int[][] getTable() {
    	return sudoku;
    }
    
    



}




