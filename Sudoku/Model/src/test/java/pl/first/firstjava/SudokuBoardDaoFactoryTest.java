package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class SudokuBoardDaoFactoryTest {
    @Test
    void testGetFileDao() {
        SudokuBoardDaoFactory test = new SudokuBoardDaoFactory();
        assertNotNull(test.getFileDao("test"));
        SudokuBoardDaoFactory test1 = new SudokuBoardDaoFactory();
        assertEquals(test1, test1);
    }
}
