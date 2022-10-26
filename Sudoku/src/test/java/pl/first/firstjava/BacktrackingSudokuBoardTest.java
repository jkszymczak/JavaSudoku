package pl.first.firstjava;
/**
 * BacktrackingSudokuBoardTest
 */

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BacktrackingSudokuBoardTest {

    @Test
    public void TestSolveGame(){
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        fillFirstRow(board);
        int[] check = {1,2,3,4,5,6,7,8,9};
        board.solveGame(solver);
        assertTrue(Arrays.equals(board.getBoard()[0], check));

    }
    private void fillFirstRow(SudokuBoard board){
        for (int i=0;i<9;i++){
            board.set(0, i, i+1);
        }
    }
}
