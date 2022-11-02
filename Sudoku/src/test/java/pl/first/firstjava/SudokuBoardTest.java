package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void get_set() {
        SudokuBoard gettest = new SudokuBoard();
        gettest.set(0, 0, 2);
        assertTrue(gettest.get(0 , 0) == 2);
    }

    @Test
    void getRow() {
        SudokuBoard sudoku = new SudokuBoard();
        assertNotNull(sudoku.getRow(2));

    }

    @Test
    void getColumn() {
        SudokuBoard sudoku = new SudokuBoard();
        assertNotNull(sudoku.getColumn(2));
    }

    @Test
    void getBox() {
        SudokuBoard sudoku = new SudokuBoard();
        assertNotNull(sudoku.getBox(1, 1));    }
    @Test
    void testSudokuBoard() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sukodu = new SudokuBoard(solver, true);
        assertTrue(sukodu.checkBoard(0,0,1));
    }

    @Test
    void solveGame() {
        SudokuBoard sudoku = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        sudoku.solveGame(solver);
        assertTrue(sudoku.checkBoard(0,0,1));
    }

    @Test
    void checkBoard() {
        SudokuBoard blad_rzad = new SudokuBoard();
        blad_rzad.set(0, 0, 2);
        assertFalse(blad_rzad.checkBoard(0, 0, 2));
        SudokuBoard blad_kolumna = new SudokuBoard();
        blad_kolumna.set(0, 0, 2);
        assertFalse(blad_kolumna.checkBoard(1, 0, 2));
        SudokuBoard blad_kwadrat = new SudokuBoard();
        for(int i =0; i < 3; i++){
            for(int j =0; j < 2; j++)
                blad_kwadrat.set(i, j, 2);
        }
        assertFalse(blad_kwadrat.checkBoard(2, 1, 2));
    }

    @Test
    void printBoard() {
        SudokuBoard sudoku = new SudokuBoard();
        assertFalse(sudoku.printBoard().isEmpty());
    }
}