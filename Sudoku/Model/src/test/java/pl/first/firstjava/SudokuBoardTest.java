package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SudokuBoardTest {

    @Test
    void get_set() {
        SudokuBoard gettest = new SudokuBoard();
        gettest.set(0, 0, 2);
        assertTrue(gettest.get(0, 0) == 2);
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
        assertNotNull(sudoku.getBox(1, 1));
    }

    @Test
    void testSudokuBoard() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sukodu = new SudokuBoard(solver, true);
        assertTrue(checkIfOk(sukodu));
    }

    @Test
    void solveGame() {
        SudokuBoard sudoku = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        sudoku.solveGame(solver);
        assertTrue(checkIfOk(sudoku));
    }

    @Test
    void checkBoard() {
        SudokuBoard blad_rzad = new SudokuBoard();
        blad_rzad.set(0, 0, 2);
        assertFalse(blad_rzad.checkBoard(0, 1, 2));
        SudokuBoard blad_kolumna = new SudokuBoard();
        blad_kolumna.set(0, 0, 2);
        assertFalse(blad_kolumna.checkBoard(1, 0, 2));
        SudokuBoard blad_kwadrat = new SudokuBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++)
                blad_kwadrat.set(i, j, 2);
        }
        assertFalse(blad_kwadrat.checkBoard(2, 1, 2));
    }

    @Test
    void printBoard() {
        SudokuBoard sudoku = new SudokuBoard();
        assertFalse(sudoku.printBoard().isEmpty());
    }
    @Test
    void testWithoutCheck(){
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(solver, false);
        sudoku.set(0, 0, 1);
        assertTrue(sudoku.set(1, 0, 1));

    }

    private boolean checkIfOk(SudokuBoard board){
        SudokuRow[] rowCheck = new SudokuRow[9];
        SudokuColumn[] columnCheck = new SudokuColumn[9];
        for (int i=0;i<9;i++){
            rowCheck[i] = board.getRow(i);
            columnCheck[i] = board.getColumn(i);

        }
        for (int i=0;i<9;i++){
            if(!(rowCheck[i].verify()||columnCheck[i].verify()))
            {
                return false;
            }
        }
        return true;
    }
    @Test
    void testEquals(){
        //test null
        SudokuBoard test = new SudokuBoard();
        test.set(0, 0, 1);
        assertFalse(test.equals(null));
        //test ten sam obiekt
        assertTrue(test.equals(test));
        //test inna klasa
        SudokuField abc = new SudokuField(2);
        assertFalse(test.equals(abc));
        //test porownanie identycznych obiektow klas Sudokuboard
        SudokuBoard test1 = new SudokuBoard();
        test1.set(0, 0, 1);
        assertTrue(test.equals(test1));
        //test porownanie roznych obiektow klas Sudokuboard
        SudokuBoard test2 = new SudokuBoard();
        assertFalse(test.equals(test2));
    }

    @Test
    void testHashCode(){
        SudokuBoard test = new SudokuBoard();
        test.set(0, 0, 1);
        SudokuBoard test1 = new SudokuBoard();
        assertFalse(test.hashCode() == test1.hashCode());
    }
    @Test
    void testToString(){
        SudokuBoard test = new SudokuBoard();
        test.set(0, 0, 1);
        SudokuBoard test1 = new SudokuBoard();
        assertFalse(test.toString() == test1.toString());
    }
    @Test
    void testIntegrity(){
        SudokuBoard test = new SudokuBoard();
        test.set(0, 0, 1);
        SudokuBoard test1 = new SudokuBoard();
        test1.set(0,0,1);
        assertTrue(test.equals(test1)&&test.hashCode()==test1.hashCode());

    }
}
