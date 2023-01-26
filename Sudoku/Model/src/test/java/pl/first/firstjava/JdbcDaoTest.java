package pl.first.firstjava;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcDaoTest {

    @Test
    void testWriteRead() throws SQLException, FileException {
        SudokuBoardDaoFactory testFactory = new SudokuBoardDaoFactory();
        SudokuBoard testBoard = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        testBoard.solveGame(solver);
        Dao <SudokuBoard> testDao;
        testDao = testFactory.getData("nowy2");
        try{
            testDao.write(testBoard);
        }
        catch (Exception e){
           throw new SQLException(e);
        }
        assertTrue(testBoard.equals(testDao.read()));
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
