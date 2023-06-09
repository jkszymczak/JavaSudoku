package pl.first.firstjava;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public Dao<SudokuBoard> getData(String filename) {
        return new JdbcSudokuBoardDao(filename);
    }
}
