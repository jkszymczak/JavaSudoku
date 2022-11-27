package pl.first.firstjava;
/**
 * BacktrackingSudokuBoardTest
 */

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

public class BacktrackingSudokuBoardTest {

    @Test
    public void TestSolveGame(){
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        fillFirstRow(board);
        board.solveGame(solver);
        assertTrue(!board.checkBoard(0,0,1));
    }
    private void fillFirstRow(SudokuBoard board){
        for (int i=0;i<9;i++){
            board.set(0, i, i+1);
        }
    }
}
