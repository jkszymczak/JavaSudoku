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
        testDao = testFactory.getData("nowy2");

        try{
            testBoard.set(8,8,9);
            System.out.println(testBoard.get(8,8));
            testBoard.set(0,3,8);
            testDao.write(testBoard);

        }
        catch (Exception e){
           throw new SQLException(e);
        }

    }

    @Test

    void testReadExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getData("nothing");
        assertThrowsExactly(FileException.class, () -> {testDao.read();});
    }
    @Test
    void testIOExceptions() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> testDao;
        testDao = factory.getData("nothingv2");
        SudokuBoard testBoard = new SudokuBoard();
        assertThrowsExactly(FileException.class, () -> {testDao.read();});
    }
}
