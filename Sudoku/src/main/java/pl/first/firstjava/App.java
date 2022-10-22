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
    sudoku.fillBoard();
    sudoku.printBoard();
  }
}
