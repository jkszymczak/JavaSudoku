package pl.first.firstjava;

import java.sql.*;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final String string = "jdbc:derby:sudokuDatabase;";
    private Connection connection;
    private ResultSet resultSet;
    private String fileName;

    int size = 9;

    public  JdbcSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    public void createBoardTable() throws SQLException {
        String connectionString = "jdbc:derby:memory:sudokuDatabase;create=true";
        try (Connection connection = DriverManager.getConnection(string)) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE Boards ("
                    + "ID INT PRIMARY KEY NOT NULL, "
                    + "name VARCHAR(255) NOT NULL"
                    + ")");
            statement.execute("CREATE TABLE Fields ("
                    + "ID INT PRIMARY KEY NOT NULL, "
                    + "row INT NOT NULL, "
                    + "col INT NOT NULL, "
                    + "value INT NOT NULL, "
                    + "board_id INT NOT NULL, "
                    + "FOREIGN KEY (board_id) REFERENCES Boards(ID)"
                    + ")");
            System.out.println("Tables created successfully");
        } catch (SQLException e) {
         //   e.printStackTrace();
            throw new SQLException(e);
        }
    }

    @Override
    public SudokuBoard read() throws FileException {
            SudokuBoard board = new SudokuBoard();
            try {
                if (connection == null) {
                    connection = DriverManager.getConnection(string);
                }
                PreparedStatement preparedStatement =
                        connection.prepareStatement("SELECT ID FROM Boards WHERE name = ?",
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                preparedStatement.setString(1, fileName);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.first()) {
                    //throw new DaoReadingException("No such board in the database");
                }
                int id = resultSet.getInt("ID");
                preparedStatement =
                        connection.prepareStatement("SELECT * FROM Fields WHERE board_id = ?",
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    board.set(resultSet.getInt("row"),
                            resultSet.getInt("col"), resultSet.getInt("value"));
                }
            } catch (SQLException e) {
                throw new DaoFileOperationException(e, e.getMessage());
            }
            return board;
    }

    @Override
    public void write(SudokuBoard obj) throws FileException {
        try (Connection connection = DriverManager.getConnection(string)) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Boards (ID,name) VALUES (?,?)",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setInt(1, 10);
            preparedStatement.setString(2, fileName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoFileOperationException(e, e.getMessage());
        }
    }
}
