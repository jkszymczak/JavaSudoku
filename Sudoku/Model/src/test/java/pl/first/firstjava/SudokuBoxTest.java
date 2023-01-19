

package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SudokuBoxTest
 */
public class SudokuBoxTest {

    @Test
    void testToString(){
        SudokuBox test = new SudokuBox(new SudokuField[]{
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)});
        SudokuBox test1 = new SudokuBox(new SudokuField[]{
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
        SudokuBox test1 = new SudokuBox(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        SudokuBox test3 =  (SudokuBox) test1.clone();
        test1.changeField(0,2);
        assertTrue(test1.getFieldValue(0)!=test3.getFieldValue(0));
    }
}
