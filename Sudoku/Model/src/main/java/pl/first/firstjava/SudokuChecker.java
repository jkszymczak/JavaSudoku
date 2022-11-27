package pl.first.firstjava;

//import java.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return new EqualsBuilder().append(this.field,((SudokuChecker)obj).field).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.field).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("field", field).toString();
    }
}
