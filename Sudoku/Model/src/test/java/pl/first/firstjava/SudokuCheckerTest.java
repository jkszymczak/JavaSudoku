package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuCheckerTest {

    @Test
    void verifyValidTest() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[] {
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9) });
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[] {
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9) });
        assertFalse(sudokuRow.verify());
    }

    @Test
    public void zeros() {
        SudokuChecker sudokuRow = new SudokuRow(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertTrue(sudokuRow.verify());
    }

    @Test
    void testHashCode() {
        SudokuChecker test = new SudokuRow(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        SudokuChecker test1 = new SudokuRow(new SudokuField[] {
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertFalse(test.hashCode() == test1.hashCode());
    }

    @Test
    void testEquals() {
        // test null
        SudokuChecker test = new SudokuRow(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertFalse(test.equals(null));
        // test ten sam obiekt
        assertTrue(test.equals(test));
        // test inna klasa
        SudokuBoard abc = new SudokuBoard();
        assertFalse(test.equals(abc));
        // test porownanie identycznych obiektow klas Sudokuboard
        SudokuChecker test1 = new SudokuRow(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertTrue(test.equals(test1));
        assertTrue(test1.equals(test));
        // test porownanie roznych obiektow klas Sudokuboard
        SudokuChecker test2 = new SudokuRow(new SudokuField[] {
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertFalse(test.equals(test2));
        assertFalse(test2.equals(test));
        // test porównanie elementów z różnych podklas
        SudokuChecker test3 = new SudokuBox(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertFalse(test3.equals(test));
        assertFalse(test.equals(test3));

        SudokuChecker test4 = new SudokuColumn(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertFalse(test4.equals(test));
        assertFalse(test.equals(test4));
        assertFalse(test3.equals(test4));
        assertFalse(test4.equals(test3));
    }

    @Test
    void testIntegrity() {

        SudokuChecker test1 = new SudokuRow(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        // test porownanie roznych obiektow klas Sudokuboard
        SudokuChecker test2 = new SudokuRow(new SudokuField[] {
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0) });
        assertTrue(test1.equals(test2) && test1.hashCode() == test2.hashCode());
    }

    //@Test
    //void testIfWorks() throws CloneNotSupportedException {
    //    SudokuChecker test1 = new SudokuRow(new SudokuField[] {
    //            new SudokuField(0),
    //            new SudokuField(0),
    //            new SudokuField(0),
    //            new SudokuField(0),
    //            new SudokuField(0),
    //            new SudokuField(0),
    //            new SudokuField(0),
    //            new SudokuField(0),
    //            new SudokuField(0) });
    //    SudokuChecker test3 = (SudokuRow) test1.clone();
    //    assertTrue(test1.getClass() == test3.getClass());
    //    assertTrue(test1.getFieldValue(0)==test3.getFieldValue(0));

    //}
    @Test
    void testIfReallyWorks() throws CloneNotSupportedException {
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
