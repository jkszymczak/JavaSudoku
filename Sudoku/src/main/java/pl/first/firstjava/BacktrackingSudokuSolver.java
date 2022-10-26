package pl.first.firstjava;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private static int size = 9;

    @Override
    public void solve(SudokuBoard board) {

        solveSudoku(0, 0, board);

    }

    static boolean solveSudoku(int row, int column, SudokuBoard board) {

        // jesli zostanie osiagniety koniec planszy sudoku to zwracamy true
        if (row == size - 1 && column == size) {
            return true;
        }

        // jesli column osiagnie wartosc 9 to zmieniamy rzad row na kolejny, i zmieniamy
        // kolumne na 0
        if (column == size) {
            row++;
            column = 0;
        }

        // jesli obecna komorka zawiera juz wartosc rozna od 0, to przeskakujemy do
        // nastepnej kolumny
        if (board.get(row, column) != 0) {
            return solveSudoku(row, column + 1, board);
        }

        for (int number = 1; number < 10; number++) {

            // losujemy liczbe 1-9 i sprawdzamy czy możemy ją wstawić
            Random random = new Random();
            int shoot = random.nextInt(9) + 1;
            if (board.checkBoard(row, column, shoot)) {

                // jesli mozna wstawic liczbe, to to robimy
                board.set(row, column, shoot);

                // rekurencja
                if (solveSudoku(row, column + 1, board)) {
                    return true;
                }

            }
            // jesli nie mozna wtawic liczby number, zerujemy komorke;
            board.set(row, column, 0);
        }
        return false;
    }
}
