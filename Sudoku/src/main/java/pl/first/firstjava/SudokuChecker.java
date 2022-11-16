package pl.first.firstjava;

//import java.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SudokuChecker {
    //protected SudokuField[] field2;
    protected final int size = 9;
    protected List<SudokuField> field = new ArrayList<>();

    public SudokuChecker(SudokuField[] fields) {
        //for(int i=0;i<size;i++){
        //    this.field.add(i,fields[i]);
        //}
        this.field.addAll(Arrays.asList(fields));
    }

    public boolean verify() {
        boolean[] numbersCheck = new boolean[9];
        for (SudokuField sudokuField : field) {
            if (sudokuField.getFieldValue() != 0) {
                if (numbersCheck[sudokuField.getFieldValue() - 1]) {
                    return false;
                }
                numbersCheck[sudokuField.getFieldValue() - 1] = true;
            }
        }
        return true;
    }
}
