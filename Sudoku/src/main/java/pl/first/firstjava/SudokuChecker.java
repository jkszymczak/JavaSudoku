package pl.first.firstjava;

public abstract class SudokuChecker {
    protected SudokuField[] field;

    public SudokuChecker(SudokuField[] fields) {
        this.field = fields;
    }

    public boolean verify() {
        boolean[] numbersCheck = new boolean[9];
        for (int i = 0; i < field.length; i++) {
            if (field[i].getFieldValue() != 0) {
                if (numbersCheck[field[i].getFieldValue() - 1]) {
                    return false;
                }
                numbersCheck[field[i].getFieldValue() - 1] = true;
            }
        }
        return true;
    }
}
