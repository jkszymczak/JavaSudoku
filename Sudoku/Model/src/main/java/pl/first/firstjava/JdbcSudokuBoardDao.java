package pl.first.firstjava;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final String dataBase = "jdbc:sqlite:../Database/db.db";
    private Connection connection;
    private ResultSet resultSet;
    private String boardName;
    int size = 9;
    int boardID;

    public  JdbcSudokuBoardDao(String fileName) {
        this.boardName = fileName;
    }

    @Override
    public SudokuBoard read() throws FileException {
        try {
            connection = DriverManager.getConnection(dataBase);
            try (Statement stmt = connection.createStatement()) {
                String sql = "SELECT ID FROM Board where name="
                        + "\'" + boardName + "\'";
                resultSet = stmt.executeQuery(sql);
                if (!resultSet.next()) {
                    //tu rzucic wyjatek z brakiem Board o takiej nazwie.
                    System.out.println("nie ma takiej planszy");
                    return null;
                }
                boardID = resultSet.getInt("ID");
                sql = "SELECT row,column,value,board_id FROM Field where board_id="
                        + boardID;
                resultSet = stmt.executeQuery(sql);
                SudokuBoard obj = new SudokuBoard();
                if (!resultSet.isBeforeFirst()) {
                    //tu rzucic wyjatek z brakiem Field o powiazanych z board o danym id.
                    System.out.println("nie ma takich fields");
                    return null;
                }
                while (resultSet.next()) {
                    obj.set(resultSet.getInt("row"),
                            resultSet.getInt("column"),
                            resultSet.getInt("value"));
                }
                return obj;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws FileException {

    }
}
