package pl.first.firstjava;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final String string = "jdbc:sqlite:../Database/db.db";
    private Connection connection;
    private ResultSet resultSet;
    private String fileName;

    int size = 9;

    public  JdbcSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }


    public void wyp() {
        connection = null;
        try {
            //connect to the database
            connection = DriverManager.getConnection(string);
            Statement stmt = connection.createStatement();

            //insert a new row into the table
            String sql = "INSERT INTO Board (ID, name) "
                    + "VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Prosze");
            pstmt.executeUpdate();

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
                    connection.prepareStatement("INSERT INTO Board (ID,Name) VALUES (?,?)",
                            ResultSet.TYPE_FORWARD_ONLY);
            preparedStatement.setInt(1, 10);
            preparedStatement.setString(2, fileName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoFileOperationException(e, e.getMessage());
        }
    }

    public void write2 (SudokuBoard board) throws SQLException {
        try (Connection connection = DriverManager.getConnection(string)) {

            // connection = DriverManager.getConnection(string);
            Statement stmt = connection.createStatement();

            //insert a new row into the table
            String sql = "SELECT ID FROM Board WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.executeUpdate();
            connection.setAutoCommit(false); // wyłączamy autoCommity
            int id; // idetnyfikator planszy
            resultSet = pstmt.executeQuery();
            if (resultSet.first()) {
                id = resultSet.getInt("ID");
                pstmt =
                        connection.prepareStatement("DELETE FROM Field WHERE board_id = ?");
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            } else {
                pstmt =
                        connection.prepareStatement("SELECT max(ID) AS maxi FROM Board");
                resultSet = pstmt.executeQuery();
                resultSet.first();
                id = resultSet.getInt("maxi") + 1;
                pstmt = connection.prepareStatement("INSERT INTO Board VALUES (?,?)");
                pstmt.setInt(1, id);
                pstmt.setString(2, fileName);
                pstmt.executeUpdate(); // wykonanie zapytania bez zwracania wyniku
            }
            pstmt = connection.prepareStatement("SELECT max(ID) AS maxi FROM Field");
            resultSet = pstmt.executeQuery();
            resultSet.first();
            int idF = resultSet.getInt("maxi");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board.get(i, j) == 0) {
                        continue;
                    }
                    pstmt =
                            connection.prepareStatement("INSERT INTO Fields Values (?,?,?,?,?)",
                                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    pstmt.setInt(1, ++idF);
                    pstmt.setInt(2, i);
                    pstmt.setInt(3, j);
                    pstmt.setInt(4, board.get(i, j));
                    pstmt.setInt(5, id);
                    pstmt.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {

            throw new SQLException(e.getMessage(), e);
        }
    }
}
