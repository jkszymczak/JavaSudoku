
package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuRowTest {

    @Test
    void testToString(){
        SudokuRow test = new SudokuRow(new SudokuField[]{
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)});
        SudokuRow test1 = new SudokuRow(new SudokuField[]{
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)});
        assertFalse(test.toString() == test1.toString());
    }

    @Test
    void testIfCloneIsDeep() throws CloneNotSupportedException {
        SudokuRow test1 = new SudokuRow(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        SudokuRow test3 =  (SudokuRow) test1.clone();
        test1.changeField(0,2);
        assertTrue(test1.getFieldValue(0)!=test3.getFieldValue(0));
    }
    
}
