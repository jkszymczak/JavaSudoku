package pl.first.firstjava;

import java.util.Random;

public class SudokuBoard {
    // atributes
    private static int size = 9;

    private int[][] board = new int[size][size];

    // metodes
    // getters
    public int[][] getBoard() {
        return board;
    }

    public int get(int x, int y) {
        return board[x][y];
    }
    //end of getters

    // setters
    public void set(int x, int y, int value) {
        board[x][y] = value;
    }
    //end of setters

    public void solveGame(SudokuSolver solver) {

        setFirstRow();
        solver.solve(this);

    }

    boolean checkBoard(int row, int column, int num) {

        // sprawdzamy, czy jest powtorzenie w rzedzie row, jesli jest, zwracamy false
        for (int y = 0; y <= 8; y++) {
            if (board[row][y] == num) {
                return false;
            }
        }

        // sprawdzamy, czy jest powtorzenie w kolumnie col, jesli jest, zwracamy false
        for (int x = 0; x <= 8; x++) {
            if (board[x][column] == num) {
                return false;
            }
        }

        // sprawdzamy, czy jest powtorzenie w odpowiednim kwadracie 3x3, jesli jest,
        // zwracamy false
        int startRow = row - row % 3;
        int startCol = column - column % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
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

    public void setFirstRow() {
        Random los = new Random();
        for (int i = 0; i < size; i++) {
            do {
                int var = los.nextInt(size) + 1;
                board[0][i] = var;
            } while (checkRepetetive(board[0][i], i));
        }
    }

    private boolean checkRepetetive(int var, int place) {
        for (int i = 0; i < place; i++) {
            if (var == board[0][i]) {
                return true;
            }
        }
        return false;
    }
}
