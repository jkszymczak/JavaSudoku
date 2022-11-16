package pl.first.firstjava;
import java.util.*;

public abstract class SudokuChecker {
    protected SudokuField[] field2;
    protected List<SudokuField> field = new ArrayList<SudokuField>(Arrays.asList(new SudokuField[9]));
    public SudokuChecker(SudokuField[] fields) {
        this.field.addAll(Arrays.asList(fields));
    }

    public boolean verify() {
        boolean[] numbersCheck = new boolean[9];
        for (int i = 0; i < field.size(); i++) {
            if (field.get(i).getFieldValue() != 0) {
                if (numbersCheck[field.get(i).getFieldValue() - 1]) {
                    return false;
                }
                numbersCheck[field.get(i).getFieldValue() - 1] = true;
            }
        }
        return true;
    }
}
