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


    public boolean findBoard() throws JdbcException, FileException {
        try {
            connection = DriverManager.getConnection(dataBase);
            try (Statement stmt = connection.createStatement()) {
                String sql = "SELECT ID FROM Board where name="
                        + "\'" + boardName + "\'";
                resultSet = stmt.executeQuery(sql);
                if (!resultSet.isBeforeFirst()) {
                    return false;
                }
                boardID = resultSet.getInt("ID");
                return true;
                }
            } catch (SQLException e) {
                throw new JdbcException(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new FileException(ex,ex.getMessage());
            }
        }
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
                    throw new JdbcException("Board not found");
                }
                boardID = resultSet.getInt("ID");
                sql = "SELECT row,column,value,board_id FROM Field where board_id="
                        + boardID;
                resultSet = stmt.executeQuery(sql);
                SudokuBoard obj = new SudokuBoard();
                if (!resultSet.isBeforeFirst()) {
                    throw new JdbcException("Fields not Found");
                }
                while (resultSet.next()) {
                    obj.set(resultSet.getInt("row"),
                            resultSet.getInt("column"),
                            resultSet.getInt("value"));
                }
                return obj;

            } catch (SQLException e) {
                throw new JdbcException(e.getMessage());
            }

        } catch (SQLException e) {
            throw new FileException(e,e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new FileException(ex,ex.getMessage());
            }
        }
    }

    private void createNew(SudokuBoard obj) throws FileException {
        String sql = "INSERT INTO Board(name) VALUES(?)";
        try (Connection connection = DriverManager.getConnection(dataBase);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sql = "INSERT INTO Field(row,column,value,board_id) VALUES(?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(dataBase);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            findBoard();
            //pstmt = connection.prepareStatement(sql);
            for (int i = 0;i < size;i++) {
                for (int j = 0;j < size;j++) {
                    pstmt.setInt(1,i);
                    pstmt.setInt(2,j);
                    pstmt.setInt(3,obj.get(i,j));
                    pstmt.setInt(4,boardID);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new FileException(e,e.getMessage());
        }

    }

    private void updateOld(SudokuBoard obj) throws FileException {

        try {
            connection = DriverManager.getConnection(dataBase);
            String sql = "UPDATE Field SET value = ? where board_id=? AND row=? AND column=?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                for (int i = 0;i < size;i++) {
                    for (int j = 0;j < size;j++) {
                        pstmt.setInt(1, obj.get(i,j));
                        pstmt.setInt(2, boardID);
                        pstmt.setInt(3, i);
                        pstmt.setInt(4, j);
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new FileException(e,e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new FileException(ex,ex.getMessage());
            }
        }
    }

    @Override
    public void write(SudokuBoard obj) throws FileException {
        try {
            if (findBoard()) {
                updateOld(obj);
            } else {
                createNew(obj);
            }
        } catch (JdbcException e) {
            throw new FileException(e,e.getMessage());
        }
    }
}
