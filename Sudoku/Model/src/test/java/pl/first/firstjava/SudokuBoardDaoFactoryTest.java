package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {
    @Test
    void testGetFileDao() {
        SudokuBoardDaoFactory test = new SudokuBoardDaoFactory();
        assertNotNull(test.getFileDao("test"));
        SudokuBoardDaoFactory test1 = new SudokuBoardDaoFactory();
        assertNotEquals(test, test1);
    }
    @Test
    void testGetDataDao() {
        SudokuBoardDaoFactory test = new SudokuBoardDaoFactory();
        assertNotNull(test.getData("test"));
        SudokuBoardDaoFactory test1 = new SudokuBoardDaoFactory();
        assertNotEquals(test, test1);
    }
}
