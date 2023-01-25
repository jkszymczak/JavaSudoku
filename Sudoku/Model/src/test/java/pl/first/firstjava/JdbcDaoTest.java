package pl.first.firstjava;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class JdbcDaoTest {

    @Test
    void testRead() throws SQLException {
        //JdbcSudokuBoardDao testDao = new JdbcSudokuBoardDao("hope");
        //utworz.createBoardTable();
        SudokuBoardDaoFactory testFactory = new SudokuBoardDaoFactory();
        SudokuBoard testBoard = new SudokuBoard();
        Dao <SudokuBoard> testDao;
        testDao = testFactory.getData("test");

        try{
            testBoard.set(0,0,9);
            //testDao.write(testBoard);
            SudokuBoard test = testDao.read();
        }
        catch (Exception e){
           throw new SQLException(e);
        }

    }
    //@Disabled
    //@Test
    /*
    void testReadExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getData("nothing");
        assertThrowsExactly(DaoFileOperationException.class, () -> {testDao.read();});
    }

    @Test
    void testWriteException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        SudokuBoard testowe = new SudokuBoard();
        testDao = factory.getData("/;;");
        assertThrowsExactly(DaoFileOperationException.class, () -> {testDao.write(testowe);});

    }
    @Test
    void testIOExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getData("nothingv2");
        SudokuBoard testBoard = new SudokuBoard();
        assertThrowsExactly(DaoFileOperationException.class, () -> {testDao.read();});
    }*/
}
