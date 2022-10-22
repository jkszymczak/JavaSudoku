
package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SudokuBoardTest {
    @Test
    public void testRandom() {
        SudokuBoard firstGame = new SudokuBoard();
        SudokuBoard secondGame = new SudokuBoard();
        firstGame.fillBoard();
        secondGame.fillBoard();
        assertFalse(areEqual(firstGame.getBoard(), secondGame.getBoard()));
    }

    @Test
    public void testArrangement() {

        SudokuBoard sudoku = new SudokuBoard();
        sudoku.fillBoard();
        assertTrue(checkLines(sudoku.getBoard()));
        assertTrue(checkColumns(sudoku.getBoard()));
        assertTrue(checkSquares(sudoku.getBoard()));

    }

    private boolean areEqual(int[][] first, int[][] second) {
        for (int i = 0; i < second.length; i++) {
            for (int j = 0; j < second.length; j++) {
                System.out.println("first ->" + first[i][j] + " second ->" + second[i][j]);
                if (first[i][j] != second[i][j])
                    return false;
            }
        }
        return true;
    }

    private boolean checkLines(int[][] lines) {
        boolean[] numbersCheck = new boolean[9];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < numbersCheck.length; j++) {
                if (numbersCheck[lines[i][j] - 1]) {
                    return false;
                }
                numbersCheck[lines[i][j] - 1] = true;
            }
            Arrays.fill(numbersCheck, false);
        }
        return true;
    }

    private boolean checkColumns(int[][] columns) {
        boolean[] numbersCheck = new boolean[9];
        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < numbersCheck.length; j++) {
                if (numbersCheck[columns[j][i] - 1]) {
                    return false;
                }
                numbersCheck[columns[j][i] - 1] = true;
            }
            Arrays.fill(numbersCheck, false);
        }
        return true;
    }

    private boolean checkSquares(int[][] squares) {

        boolean[] numbersCheck = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int x = 0; x < 3; x++) {
                    for (int k = 0; k < 3; k++) {
                        if (numbersCheck[squares[x + (i * 3)][k + (j * 3)]-1]) {
                            return false;
                        }
                        numbersCheck[squares[x + i * 3][k + j * 3]-1] = true;


                    }
                    Arrays.fill(numbersCheck, false);
                }

            }

        }
        return true;
    }

}
