package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.*;

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
        testDao = factory.getFileDao("nothing");
        assertThrowsExactly(RuntimeException.class, () -> {testDao.read();}); 
    }
    @Test
    void testReadClassNotFoundEx() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;



//        try (FileOutputStream fileOutputStream = new FileOutputStream("testNoClassEx.txt");
//             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
//            objectOutputStream.writeObject(field);
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
        try {
            File file = new File("testNoClassEx2.txt");
            file.createNewFile();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        testDao = factory.getFileDao("testNoClassEx2");
        assertThrowsExactly(RuntimeException.class, () -> {testDao.read();});
    }
    @Test
    void testWriteException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        SudokuBoard testowe = new SudokuBoard();
        testDao = factory.getFileDao("/;;");
        assertThrowsExactly(RuntimeException.class, () -> {testDao.write(testowe);});

    }
    /*@Test
    void testIOExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getFileDao("nothingv2");
        SudokuBoard testBoard = new SudokuBoard();
        assertThrowsExactly(IOException.class, () -> {testDao.read();});
    }*/
}
