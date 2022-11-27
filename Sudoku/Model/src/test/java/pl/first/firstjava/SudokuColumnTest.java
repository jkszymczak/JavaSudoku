
package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SudokuColumnTest
 */
public class SudokuColumnTest {

    @Test
    void testToString(){
        SudokuColumn test = new SudokuColumn(new SudokuField[]{
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)});
        SudokuColumn test1 = new SudokuColumn(new SudokuField[]{
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
}
