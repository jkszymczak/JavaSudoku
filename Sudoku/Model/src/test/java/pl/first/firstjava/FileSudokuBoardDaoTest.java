package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileSudokuBoardDaoTest {
    
    @Test
    void testRead() throws DaoFileOperationException {
        SudokuBoardDaoFactory testFactory = new SudokuBoardDaoFactory();
        SudokuBoard testBoard = new SudokuBoard();
        Dao <SudokuBoard> testDao;
        testDao = testFactory.getFileDao("test");

        try{
            testDao.write(testBoard);
            SudokuBoard testBoard2 =testDao.read();
            assertEquals(testBoard, testBoard2);
        }
        catch (Exception e){
            throw new DaoFileOperationException(e,FileSudokuBoardDao.class.getSimpleName()+e.getMessage());
        }

    }

    @Test
    void testReadExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getFileDao("nothing");
        assertThrowsExactly(DaoFileOperationException.class, () -> {testDao.read();});
    }

    @Test
    void testWriteException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        SudokuBoard testowe = new SudokuBoard();
        testDao = factory.getFileDao("/;;");
        assertThrowsExactly(DaoFileOperationException.class, () -> {testDao.write(testowe);});

    }
    @Test
    void testIOExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getFileDao("nothingv2");
        SudokuBoard testBoard = new SudokuBoard();
        assertThrowsExactly(DaoFileOperationException.class, () -> {testDao.read();});
    }
}
