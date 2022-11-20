package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuCheckerTest {

    @Test
    void verifyValidTest() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertTrue(sudokuRow.verify());
    }
    @Test
    public void verifyInvalidTest() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)});
        assertFalse(sudokuRow.verify());
    }
    @Test
    public void zeros() {
        SudokuRow sudokuRow = new SudokuRow(new SudokuField[]{
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)});
        assertTrue(sudokuRow.verify());
    }

    @Test
    void testHashCode() {
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
        SudokuRow test1 = new SudokuRow(new SudokuField[] {
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)});
        assertFalse(test.hashCode() == test1.hashCode());
    }
    @Test
    void testEquals(){
        //test null
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
        assertFalse(test.equals(null));
        //test ten sam obiekt
        assertTrue(test.equals(test));
        //test inna klasa
        SudokuBoard abc = new SudokuBoard();
        assertFalse(test.equals(abc));
        //test porownanie identycznych obiektow klas Sudokuboard
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
        assertTrue(test.equals(test1));
        //test porownanie roznych obiektow klas Sudokuboard
        SudokuRow test2 = new SudokuRow(new SudokuField[] {
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)});
        assertFalse(test.equals(test2));
    }
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
}
