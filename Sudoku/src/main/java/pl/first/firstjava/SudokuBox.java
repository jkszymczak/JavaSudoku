package pl.first.firstjava;

/**
 * SudokuBox
 */
public class SudokuBox implements SudokuChecker {
    SudokuField[] box = new SudokuField[9];

    @Override
    public boolean verify() {

        return true;
    }

}
