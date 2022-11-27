package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FileSudokuBoardDaoTest {
    
    @Test
    void testRead() {
        SudokuBoardDaoFactory testFactory = new SudokuBoardDaoFactory();
        SudokuBoard testBoard = new SudokuBoard();
        Dao <SudokuBoard> testDao;
        testDao = testFactory.getFileDao("test");
        testDao.write(testBoard); 
        SudokuBoard testBoard2 = testDao.read();
        assertEquals(testBoard, testBoard2);
    }

    @Test
    void testReadExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getFileDao("Case2");
        assertEquals(testDao.read(), RuntimeException.class);
        ;
    }
}
