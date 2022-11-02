package pl.first.firstjava;

import java.util.HashSet;
import java.util.Set;

/**
 * SudokuField
 */
public class SudokuField {

    private int value;
    private Set<SudokuChecker> checkers = new HashSet<>();

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    public void addChecker(SudokuChecker checker) {
        checkers.add(checker);
    }


}
