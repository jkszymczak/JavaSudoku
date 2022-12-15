package pl.first.firstjava.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pl.first.firstjava.BacktrackingSudokuSolver;
import pl.first.firstjava.DifficultyLevel;
import pl.first.firstjava.SudokuBoard;

/* Controller needs to implement Initializable as JavaFX relies on the class having
 * an "initialize" method. It is going to execute the "initialize" method only AFTER the layout
 * file has been loaded.
 */
public class BoardController implements Initializable{
    int player_selected_col;
    int player_selected_row;


    private SudokuBoard sudokuBoard;

    @FXML // The FXML loader is going to inject from the layout
    Button button_one; // remember our fx:id's for our buttons? name should be the same in order for the FXMLLoader to find it.
    @FXML
    Button button_two;
    @FXML
    Button button_three;
    @FXML
    Button button_four;
    @FXML
    Button button_five;
    @FXML
    Button button_six;
    @FXML
    Button button_seven;
    @FXML
    Button button_eight;
    @FXML
    Button button_nine;
    @FXML
    Button button_easy;
    @FXML
    Button button_medium;
    @FXML
    Button button_hard;
    @FXML
    Canvas canvas;

    public void difficulty(int lvl) throws CloneNotSupportedException {
        sudokuBoard = (SudokuBoard) generowanie().clone();

        DifficultyLevel dupa= DifficultyLevel.Easy;
        dupa.zeroFields(lvl, sudokuBoard);
    }

    @FXML
    protected void easymode() throws CloneNotSupportedException {
        difficulty(1);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }
    @FXML
    protected void mediummode() throws CloneNotSupportedException {
        difficulty(2);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }
    @FXML
    protected void hardmode() throws CloneNotSupportedException {
        difficulty(3);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        generowaniePustej();
        //Get graphics context from canvas
        GraphicsContext context = canvas.getGraphicsContext2D();
        //Call drawOnCanvas method, with the context we have gotten from the canvas

        try {
            drawOnCanvas(context);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }
    public SudokuBoard generowanie() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(solver,false);
        return sudokuBoard1;
    }

    public void generowaniePustej() {
        sudokuBoard= new SudokuBoard();
    }

    public void drawOnCanvas(GraphicsContext context) throws CloneNotSupportedException {
        /*
        button_easy.setOnAction(event -> {
            try {
                difficulty(1);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });
        button_medium.setOnAction(event -> {
            try {
                difficulty(2);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });
        button_medium.setOnAction(event -> {
            try {
                difficulty(3);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });*/
        context.clearRect(0, 0, 450, 450);
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {
                // finds the y position of the cell, by multiplying the row number by 50, which is the height of a row 					// in pixels
                // then adds 2, to add some offset
                int position_y = row * 50 + 2;

                // finds the x position of the cell, by multiplying the column number by 50, which is the width of a 					// column in pixels
                // then add 2, to add some offset
                int position_x = col * 50 + 2;

                // defines the width of the square as 46 instead of 50, to account for the 4px total of blank space 					// caused by the offset
                // as we are drawing squares, the height is going to be the same
                int width = 46;

                // set the fill color to white (you could set it to whatever you want)
                context.setFill(Color.WHITE);

                // draw a rounded rectangle with the calculated position and width. The last two arguments specify the 					// rounded corner arcs width and height.
                // Play around with those if you want.
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }
        // draw highlight around selected cell
        // set stroke color to res
        context.setStroke(Color.BLUE);
        // set stroke width to 5px
        context.setLineWidth(5);
        // draw a strokeRoundRect using the same technique we used for drawing our board.
        context.strokeRoundRect(player_selected_col * 50 + 2, player_selected_row * 50 + 2, 46, 46, 10, 10);




        // draw the initial numbers from our GameBoard instance
        int[][] initial= new int [9][9];
        for(int row = 0; row<9; row++) {
            for (int col = 0; col < 9; col++) {
                initial[row][col] = sudokuBoard.get(row,col);
            }
        }



        // for loop is the same as before
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {

                // finds the y position of the cell, by multiplying the row number by 50, which
                // is the height of a row in pixels then adds 2, to add some offset
                int position_y = row * 50 + 30;

                // finds the x position of the cell, by multiplying the column number by 50,
                // which is the width of a column in pixels then add 2, to add some offset
                int position_x = col * 50 + 20;

                // set the fill color to white (you could set it to whatever you want)
                context.setFill(Color.BLACK);

                // set the font, from a new font, constructed from the system one, with size 20
                context.setFont(new Font(20));

                // check if value of coressponding initial array position is not 0, remember that
                // we treat zeroes as squares with no values.
                if(initial[row][col]!=0) {

                    // draw the number using the fillText method
                    context.fillText(initial[row][col] + "", position_x, position_y);
                }
            }
        }
    }
    /*void probna(GraphicsContext context) {
        int[][] initial= new int [9][9];
        for(int row = 0; row<9; row++) {
            for (int col = 0; col < 9; col++) {
                initial[row][col] = sudokuBoard.get(row,col);
            }
        }



        // for loop is the same as before
        for(int row = 0; row<9; row++) {
            for(int col = 0; col<9; col++) {

                // finds the y position of the cell, by multiplying the row number by 50, which
                // is the height of a row in pixels then adds 2, to add some offset
                int position_y = row * 50 + 30;

                // finds the x position of the cell, by multiplying the column number by 50,
                // which is the width of a column in pixels then add 2, to add some offset
                int position_x = col * 50 + 20;

                // set the fill color to white (you could set it to whatever you want)
                context.setFill(Color.BLACK);

                // set the font, from a new font, constructed from the system one, with size 20
                context.setFont(new Font(20));

                // check if value of coressponding initial array position is not 0, remember that
                // we treat zeroes as squares with no values.
                if(initial[row][col]!=0) {

                    // draw the number using the fillText method
                    context.fillText(initial[row][col] + "", position_x, position_y);
                }
            }
        }
    }*/
}
