package pl.first.firstjava;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    private int value;

    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

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
        return new EqualsBuilder().append(this.value,((SudokuField)obj).value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("value", value).toString();
    }

    @Override
    public Object clone() throws CloneException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {

            throw new CloneException(SudokuField.class.getSimpleName());

        }
    }

    @Override
    public int compareTo(SudokuField field) {
        return Integer.compare(this.getFieldValue(), field.getFieldValue());
    }
}
