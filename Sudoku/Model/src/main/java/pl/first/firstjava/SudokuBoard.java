package pl.first.firstjava;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard implements Serializable, Cloneable {
    // atributes
    private static int size = 9;
    SudokuSolver solver;
    boolean checkOnChange;

    private SudokuField[][] board = new SudokuField[size][size];
    // metodes
    // constructors

    public SudokuBoard(SudokuSolver sudokuSolver, boolean checkOnChange) {

        this.solver = sudokuSolver;
        this.checkOnChange = checkOnChange;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
        solveGame(this.solver);
    }

    public SudokuBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
        this.checkOnChange = true;
    }

    // getters

    public int get(int x, int y) {
        return this.board[x][y].getFieldValue();
    }

    public SudokuRow getRow(int y) {

        SudokuField[] row = new SudokuField[size];
        for (int i = 0; i < size; i++) {
            row[i] = this.board[i][y];
        }
        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] column = new SudokuField[size];
        for (int i = 0; i < size; i++) {
            column[i] = this.board[x][i];
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int x, int y) {
        int startRow = x - x % 3;
        int startCol = y - y % 3;
        SudokuField[] box = new SudokuField[size];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[i + startRow + j + startCol] = this.board[i + startRow][j + startCol];
            }
        }
        return new SudokuBox(box);

    }
    // end of getters

    // setters
    public boolean set(int x, int y, int value) {
        if (!checkOnChange) {
            this.board[x][y].setFieldValue(value);
            return true;
        }
        if (checkBoard(x, y, value) || value == 0) {
            this.board[x][y].setFieldValue(value);
            return true;
        }
        return false;
    }
    // end of setters

    public void solveGame(SudokuSolver solver) {

        boolean save = this.checkOnChange;
        this.checkOnChange = true;
        solver.solve(this);
        this.checkOnChange = save;

    }

    boolean checkBoard(int row, int column, int num) {

        // sprawdzamy, czy jest powtorzenie w rzedzie row, jesli jest, zwracamy false
        for (int y = 0; y <= 8; y++) {
            if (board[row][y].getFieldValue() == num) {
                return false;
            }
        }

        // sprawdzamy, czy jest powtorzenie w kolumnie col, jesli jest, zwracamy false
        for (int x = 0; x <= 8; x++) {
            if (board[x][column].getFieldValue() == num) {
                return false;
            }
        }

        // sprawdzamy, czy jest powtorzenie w odpowiednim kwadracie 3x3, jesli jest,
        // zwracamy false
        int startRow = row - row % 3;
        int startCol = column - column % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol].getFieldValue() == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public String printBoard() {
        String printed = new String();
        for (int i = 0; i < size; i++) {
            if (i % 3 == 0) {
                // System.out.print("*-------*-------*-------* \n");
                printed += "*-------*-------*-------* \n";

            }
            for (int j = 0; j < size; j++) {
                if (j == 0) {
                    // System.out.print("| ");
                    printed += "| ";
                }
                // System.out.print(board[i][j] + " ");
                printed += board[i][j].getFieldValue() + " ";
                if (j % 3 == 2) {
                    // System.out.print("| ");
                    printed += "| ";
                }
            }
            // System.out.println("");
            printed += "\n";
            if (i == size - 1) {
                // System.out.print("*-------*-------*-------* \n");
                printed += "*-------*-------*-------* \n";
            }
        }
        return printed;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return new EqualsBuilder().append(this.board, ((SudokuBoard)obj).board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.board).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("board", this.board).toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        /*SudokuBoard clonedSudokuBoard = new SudokuBoard();
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++){
                clonedSudokuBoard.set(i, j, this.get(i, j).clone());
            }
        }
        return clonedSudokuBoard;*/
        return super.clone();
    }
}
