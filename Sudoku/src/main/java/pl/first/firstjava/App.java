package pl.first.firstjava;

/**
 * Klasa główna z metodą main.
 *
 * @author Marcin Kwapisz
 */
public class App {

    // final int a_Pole = 1;

    public static void main(final String[] args) {
        SudokuBoard sudoku = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        sudoku.solveGame(solver);
        sudoku.printBoard();
        if (SudokuSolver.class.isAssignableFrom(BacktrackingSudokuSolver.class)) {
            System.out.println("dziala");
        }
    }
}
