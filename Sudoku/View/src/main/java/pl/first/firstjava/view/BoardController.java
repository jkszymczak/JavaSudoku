package pl.first.firstjava.view;

//import static pl.first.firstjava.DifficultyLevel.Easy;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;
import pl.first.firstjava.*;


public class BoardController implements Initializable {
    int playerselectedcol;
    int playerselectedrow;

    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardSolved;


    @FXML
    Button buttonOne; // remember our

    @FXML
    Button buttonTwo;
    @FXML
    Button buttonThree;
    @FXML
    Button buttonFour;
    @FXML
    Button buttonFive;
    @FXML
    Button buttonSix;
    @FXML
    Button buttonSeven;
    @FXML
    Button buttonEight;
    @FXML
    Button buttonNine;
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
    Canvas canvas;
    @FXML
    Pane pane;

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
    /*@FXML
    public void polski(ActionEvent event) {
        Locale.setDefault(new Locale("pl"));
    }*/
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
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawOnCanvas(context);

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
            difficulty(1);
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawOnCanvas(context);
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());
        }

    }

    @FXML
    protected void mediummode() throws CloneException {
        try {
            difficulty(2);
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawOnCanvas(context);
        } catch (CloneNotSupportedException e) {
            throw new CloneException(BoardController.class.getSimpleName());        }

    }

    @FXML
    protected void hardmode() throws CloneException {
        try {
            difficulty(3);
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawOnCanvas(context);
        } catch (CloneNotSupportedException e){
            throw new CloneException(BoardController.class.getSimpleName());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        generowaniePustej();
        GraphicsContext context = canvas.getGraphicsContext2D();

        try {
            drawOnCanvas(context);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }

    public SudokuBoard generowanie() throws CloneException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(solver, false);
        return sudokuBoard1;
    }

    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int mouse_x = (int) event.getX();
                int mouse_y = (int) event.getY();


                playerselectedrow = (int) (mouse_y / 50);
                playerselectedcol = (int) (mouse_x / 50);

                //get the canvas graphics context and redraw
                try {
                    drawOnCanvas(canvas.getGraphicsContext2D());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void generowaniePustej() {
        sudokuBoard = new SudokuBoard();
    }

    public void drawOnCanvas(GraphicsContext context) throws CloneException {
        context.clearRect(0, 0, 450, 450);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int positionY = row * 50 + 2;


                int positionX = col * 50 + 2;


                int width = 46;

                context.setFill(Color.WHITE);


                context.fillRoundRect(positionX, positionY, width, width, 10, 10);
            }
        }

        context.setStroke(Color.BLUE);
        context.setLineWidth(5);

        context.strokeRoundRect(playerselectedcol * 50 + 2,
                playerselectedrow * 50 + 2, 46, 46, 10, 10);
        /*for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sudokuBoard.get(row,col).addListener((observable, oldValue, newValue) -> {
                    context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    context.fillText(newValue, row, col);
                });
            }
        }*/


        int[][] initial = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                initial[row][col] = sudokuBoard.get(row, col);
            }
        }

        // for loop is the same as before
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {


                int positionY = row * 50 + 30;


                int positionX = col * 50 + 20;

                context.setFill(Color.BLACK);

                context.setFont(new Font(20));


                if (initial[row][col] != 0) {

                    context.fillText(initial[row][col] + "", positionX, positionY);

                }
            }
        }
    }
}
