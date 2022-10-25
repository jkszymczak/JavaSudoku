package pl.first.firstjava;

public class BacktrackingSudokuSolver implements SudokuSolver {
    private static int size = 9;
    @Override
    public static void solve(SudokuBoard board) {
        int [][] grid = board.getBoard();
        solveSudoku(grid, 0, 0, board);
    }
    static boolean solveSudoku(int[][] grid, int row, int column, SudokuBoard board) {

        //jesli zostanie osiagniety koniec planszy sudoku to zwracamy true
        if (row == size - 1 && column == size) {
            return true;
        }


        //jesli column osiagnie wartosc 9 to zmieniamy rzad row na kolejny, i zmieniamy kolumne na 0
        if (column == size) {
            row++;
            column = 0;
        }


        //jesli obecna komorka zawiera juz wartosc rozna od 0, to przeskakujemy do nastepnej kolumny
        if (grid[row][column] != 0) {
            return solveSudoku(grid, row, column + 1, board);
        }


        for (int number = 1; number < 10; number++) {

            //sprawdzamy, czy mozna w dana komorke wstawic liczbe
            if (board.checkBoard(grid, row, column, number)) {

                //jesli mozna wstawic liczbe, to to robimy
                grid[row][column] = number;

                //rekurencja
                if (solveSudoku(grid, row, column + 1, board)) {
                    return true;
                }

            }
            //jesli nie mozna wtawic liczby number, zerujemy komorke;
            grid[row][column] = 0;
        }
        return false;
    }
}
