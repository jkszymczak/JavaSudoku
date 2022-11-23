package pl.first.firstjava;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuRow extends SudokuChecker {

    public SudokuRow(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).toString();
    }
}

