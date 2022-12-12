package pl.first.firstjava;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBox extends SudokuChecker {

    public SudokuBox(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).toString();
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println();
        SudokuBox clone = (SudokuBox) super.clone();
        SudokuField[] forClone = new SudokuField[size];
        for (int i = 0;i<field.size();i++) {
            forClone[i] = new SudokuField(this.getFieldValue(i));
        }
        clone.setField(forClone);
        return clone;
    }

}
