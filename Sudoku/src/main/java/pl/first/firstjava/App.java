package pl.first.firstjava;

public class App {

    // final int a_Pole = 1;

    public static void main(final String[] args) {
        SudokuBoard sudoku = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        sudoku.solveGame(solver);
        String printed = sudoku.printBoard();
        System.out.println(printed);
    }
}
