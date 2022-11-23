
package pl.first.firstjava;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuColumn extends SudokuChecker {
    public SudokuColumn(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).toString();
    }

}
