package pl.first.firstjava.view;




import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import pl.first.firstjava.*;
import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;


public class BoardController implements Initializable {
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardSolved;
    private TextField[][] sudokuCells;

    @FXML
    Button buttonEasy;
    @FXML
    Button buttonMedium;
    @FXML
    Button buttonHard;
    @FXML
    Button buttonPL;
    @FXML
    Button buttonEN;
    @FXML
    GridPane gridPane;


    public void difficulty(int lvl) throws CloneException {
        try {
            sudokuBoard = (SudokuBoard) generowanie().clone();
            DifficultyLevel probne = DifficultyLevel.Easy;
            probne.zeroFields(lvl, sudokuBoard);

        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());
        }
    }
    private static String randomString(int n)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {


            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    @FXML
    public void save() throws DaoFileOperationException {
        try{
            FileSudokuBoardDao zapisywacz = new FileSudokuBoardDao(randomString(5));
            zapisywacz.write(sudokuBoard);
        } catch (DaoFileOperationException e) {
            throw new DaoFileOperationException(e,BoardController.class.getSimpleName());

        }

    }
    @FXML
    public void read() throws CloneException, DaoFileOperationException {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            String fileName = chooser.showOpenDialog(new Stage()).getName().replaceFirst("[.][^.]+$", "");
            System.out.println(fileName);
            FileSudokuBoardDao plik = new FileSudokuBoardDao(fileName);
            sudokuBoard = plik.read();


            BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
            sudokuBoardSolved = (SudokuBoard) sudokuBoard.clone();
            sudokuBoardSolved.solveGame(solver);
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());
        } catch (DaoFileOperationException e) {
            throw new DaoFileOperationException(e,BoardController.class.getSimpleName());
        }

    }

    @FXML
    protected void easymode() throws CloneException {
        try {
            generowaniePustej();
            difficulty(1);
            rysowanie();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());
        }

    }

    @FXML
    protected void mediummode() throws CloneException {
        try {
            generowaniePustej();
            difficulty(2);
            rysowanie();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());        }

    }

    @FXML
    protected void hardmode() throws CloneException {
        try {
            generowaniePustej();

            difficulty(3);
            rysowanie();
        } catch (CloneNotSupportedException e){
            throw new CloneException(BoardController.class.getSimpleName());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        generowaniePustej();
        rysowanie();



    }

    public void rysowanie() {
        //czyszczenie


        gridPane.getChildren().clear();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Label label = new Label(String.valueOf(sudokuBoard.get(i,j)));
                gridPane.add(label, j, i);

                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(30);
                rectangle.setHeight(30);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.TRANSPARENT);
                gridPane.add(rectangle, j, i);
                gridPane.setHalignment(label, HPos.CENTER);
            }
        }

    }

    public SudokuBoard generowanie() throws CloneException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(solver, false);
        return sudokuBoard1;
    }



    public void generowaniePustej() {
        sudokuBoard = new SudokuBoard();
    }


}
