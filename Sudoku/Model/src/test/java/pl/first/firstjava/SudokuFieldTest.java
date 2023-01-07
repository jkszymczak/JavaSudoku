package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    void addChecker() {
        SudokuField field = new SudokuField();
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
        field.addChecker(sudokuRow);
    }

    @Test
    void testEquals(){
        //test null
        SudokuField test = new SudokuField(1);
        assertFalse(test.equals(null));
        //test ten sam obiekt
        assertTrue(test.equals(test));
        //test inna klasa
        SudokuBoard abc = new SudokuBoard();
        assertFalse(test.equals(abc));
        //test porownanie identycznych obiektow klas Sudokuboard
        SudokuField test1 = new SudokuField(1);
        assertTrue(test.equals(test1));
        //test porownanie roznych obiektow klas Sudokuboard
        SudokuBoard test2 = new SudokuBoard();
        assertFalse(test.equals(test2));
    }

    @Test
    void testHashCode(){
        SudokuField test = new SudokuField(1);
        SudokuField test1 = new SudokuField();
        assertFalse(test.hashCode() == test1.hashCode());
    }

    @Test
    void testToString(){
        SudokuField test = new SudokuField(1);
        SudokuField test1 = new SudokuField();
        assertFalse(test.toString() == test1.toString());
    }
    @Test
    void testIntegrity(){
        SudokuField test = new SudokuField(1);
        SudokuField test1 = new SudokuField(1);
        assertTrue(test.equals(test1)&&test.hashCode()==test1.hashCode());
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        SudokuField test = new SudokuField(1);
        SudokuField test1 = (SudokuField) test.clone();
        assertFalse(test == test1);
        test1.setFieldValue(3);
        assertTrue(test1.getFieldValue() == 3);
    }

    @Test
    void testCompareTo() {
        SudokuField test = new SudokuField(2);
        SudokuField test1 = new SudokuField(3);
        assertTrue(test.compareTo(test) == 0);
        assertTrue(test.compareTo(test1) == -1);
        SudokuField test2 = new SudokuField(1);
        assertTrue(test.compareTo(test2) == 1);
        assertThrowsExactly(NullPointerException.class, () -> {test.compareTo(null);});

    }
}
