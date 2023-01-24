
package pl.first.firstjava.view;





import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import pl.first.firstjava.*;

import java.util.Locale;
import java.util.ResourceBundle;


public class BoardController {
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardSolved;
    private JavaBeanIntegerProperty[][] fieldValueProperty = new JavaBeanIntegerProperty[9][9];

    private final StringConverter converter = new CustomConverter();
    private PopOutWindow popOutWindow = new PopOutWindow();
    private Locale locale = new Locale("pl");

    private ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.view.Lang", locale);
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
    @FXML
    Button buttonAuthors;
    @FXML
    Button buttonLoad;
    @FXML
    Button buttonSave;
    @FXML
    Label difficultyLabel;
    @FXML
    Label langLabel;




    public void difficulty(int lvl) throws CloneException {
        try {
            sudokuBoard = (SudokuBoard) generowanie().clone();
            sudokuBoardSolved = (SudokuBoard) sudokuBoard.clone();

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
    public void read() throws CloneException, DaoFileOperationException, PropertyBuilderNoSuchMethodException {
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
            gridPane.getChildren().clear();
            rysowanie();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());
        } catch (DaoFileOperationException e) {
            throw new DaoFileOperationException(e,BoardController.class.getSimpleName());
        }

    }

    @FXML
    protected void easymode() throws CloneException, PropertyBuilderNoSuchMethodException {
        try {
            gridPane.getChildren().clear();
            generowaniePustej();
            difficulty(1);
            rysowanie();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());
        }

    }

    @FXML
    protected void mediummode() throws CloneException, PropertyBuilderNoSuchMethodException {
        try {
            gridPane.getChildren().clear();
            generowaniePustej();
            difficulty(2);
            rysowanie();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());        }

    }

    @FXML
    protected void hardmode() throws CloneException, PropertyBuilderNoSuchMethodException  {
        try {
            gridPane.getChildren().clear();
            generowaniePustej();
            difficulty(3);
            rysowanie();
        } catch (CloneNotSupportedException e){
            throw new CloneException(BoardController.class.getSimpleName());
        }
    }

    @FXML
    public void initialize() throws NoSuchMethodException {
        langChangetoPL();
        //gridPane.getChildren().clear();
        generowaniePustej();
        rysowanie();
    }

    public void rysowanie() throws PropertyBuilderNoSuchMethodException {
        //czyszczenie


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(30);
                rectangle.setHeight(30);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.TRANSPARENT);
                gridPane.add(rectangle, j, i);
                gridPane.setHalignment(rectangle, HPos.CENTER);

                TextField textField = new TextField();
                textField.setMinSize(29, 29);
                textField.setMaxSize(29, 29);
                textField.setFont(Font.font(14));
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                }
                try {
                    fieldValueProperty[i][j] = JavaBeanIntegerPropertyBuilder.create()
                            .bean(new SudokuBoardAdapter(sudokuBoard, i, j))
                            .name("Field")
                            .build();
                } catch (NoSuchMethodException e) {
                    //logger.error("Cannot build required property");
                    throw new PropertyBuilderNoSuchMethodException();
                }
                textField.textProperty().bindBidirectional(fieldValueProperty[i][j], converter);
                int finalJ = j;
                int finalI = i;
                textField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String previous, String current) {
                        if (!((current.matches("[1-9]")) || (current.equals("")))) {
                            textField.setText(previous);
                        }
                        if((current.matches("[1-9]")) && Integer.valueOf(current) != sudokuBoardSolved.get(finalI, finalJ)) {

                            popOutWindow.messageBox("",
                                    (bundle.getString("wrongValue")),
                                    Alert.AlertType.INFORMATION);
                            textField.setText(previous);
                        }
                        else if ((current.matches("[1-9]")) && Integer.valueOf(current) == sudokuBoardSolved.get(finalI, finalJ)){
                            textField.setDisable(true);
                        }
                    }
                });
                if (sudokuBoard.get(i, j) == 0) {
                    textField.clear();
                }
                gridPane.add(textField, j, i);
                gridPane.setHalignment(textField, HPos.CENTER);
            }
        }
    }

    public SudokuBoard generowanie() throws CloneException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(solver, false);
        return sudokuBoard1;
    }

    @FXML
    public void authorsButton() {
        ResourceBundle listBundle = ResourceBundle.getBundle("pl.first.firstjava.view.Authors");
        popOutWindow.messageBox("",
                (listBundle.getObject("1. ") + "\n" + listBundle.getObject("2. ")),
                Alert.AlertType.INFORMATION);
    }

    public void generowaniePustej() {
        sudokuBoard = new SudokuBoard();
    }

    @FXML
    private void langChangetoPL() {
        locale = new Locale("pl");
        bundle = ResourceBundle.getBundle("pl.first.firstjava.view.Lang", locale);
        buttonEasy.setText(bundle.getString("btnEasy"));
        buttonMedium.setText(bundle.getString("btnMedium"));
        buttonHard.setText(bundle.getString("btnHard"));
        buttonAuthors.setText(bundle.getString("btnAuthors"));
        difficultyLabel.setText(bundle.getString("diffLbl"));
        langLabel.setText(bundle.getString("langLbl"));
        buttonLoad.setText(bundle.getString("loadBtn"));
        buttonSave.setText(bundle.getString("saveBtn"));
        buttonEN.setText(bundle.getString("btnEN"));
        buttonPL.setText(bundle.getString("btnPL"));
    }
    @FXML
    private void langChangetoEN() {
        locale = new Locale("en");
        bundle = ResourceBundle.getBundle("pl.first.firstjava.view.Lang", locale);
        buttonEasy.setText(bundle.getString("btnEasy"));
        buttonMedium.setText(bundle.getString("btnMedium"));
        buttonHard.setText(bundle.getString("btnHard"));
        buttonAuthors.setText(bundle.getString("btnAuthors"));
        difficultyLabel.setText(bundle.getString("diffLbl"));
        langLabel.setText(bundle.getString("langLbl"));
        buttonLoad.setText(bundle.getString("loadBtn"));
        buttonSave.setText(bundle.getString("saveBtn"));
        buttonEN.setText(bundle.getString("btnEN"));
        buttonPL.setText(bundle.getString("btnPL"));
    }
}
