package pl.first.firstjava;

import java.util.Random;

public class SudokuBoard {
    // atributes
    private static int size = 9;

    private int[][] board = new int[size][size];

    // metodes
    public int[][] getBoard() {
        return board;
    }

    private void zeroBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void setFirstRow() {
        zeroBoard();
        Random los = new Random();
        for (int i = 0; i < size; i++) {
            do {
                int var = los.nextInt(size) + 1;
                board[0][i] = var;
            } while (checkRepetetive(board[0][i], i));
        }
    }

    public void fillBoard() {
        setFirstRow();
        if (!solveSudoku(board, 0, 0)) {
         System.out.print("Nie mozliwe jest rozwiazanie sudoku!");
        }
    }

    static boolean solveSudoku(int[][] grid, int row, int column) {

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
            return solveSudoku(grid, row, column + 1);
        }


        for (int number = 1; number < 10; number++) {

            //sprawdzamy, czy mozna w dana komorke wstawic liczbe
            if (isSafe(grid, row, column, number)) {

                //jesli mozna wstawic liczbe, to to robimy
                grid[row][column] = number;

                //rekurencja
                if (solveSudoku(grid, row, column + 1)) {
                    return true;
                }

            }
            //jesli nie mozna wtawic liczby number, zerujemy komorke;
            grid[row][column] = 0;
        }
        return false;
    }

    static boolean isSafe(int[][] grid, int row, int column, int num) {

        //sprawdzamy, czy jest powtorzenie w rzedzie row, jesli jest, zwracamy false
        for (int y = 0; y <= 8; y++) {
            if (grid[row][y] == num) {
                return false;
            }
        }

        //sprawdzamy, czy jest powtorzenie w kolumnie col, jesli jest, zwracamy false
        for (int x = 0; x <= 8; x++) {
            if (grid[x][column] == num) {
                return false;
            }
        }

        //sprawdzamy, czy jest powtorzenie w odpowiednim kwadracie 3x3, jesli jest, zwracamy false
        int startRow = row - row % 3;
        int startCol = column - column % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRepetetive(int var, int place) {
        for (int i = 0; i < place; i++) {
            if (var == board[0][i]) {
                return true;
            }
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            if (i % 3 == 0) {
                System.out.print("*-------*-------*-------* \n");
            }
            for (int j = 0; j < size; j++) {
                if (j == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
                if (j % 3 == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println("");
            if (i == size - 1) {
                System.out.print("*-------*-------*-------* \n");
            }
        }
    }
}

