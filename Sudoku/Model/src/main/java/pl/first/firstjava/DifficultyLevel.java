package pl.first.firstjava;

import java.util.Random;

public enum DifficultyLevel {

    Easy(1),
    Hard(3),
    Medium(2);
    int lvlDifficulty = 20;
    int boardSize = 9;

    DifficultyLevel(int lvl) {
    }

    void randomZero(int lvl, SudokuBoard sudokuBoard) {
        int ozero = 0;
        int lozero = 0;
        Random random = new Random();
        for (int i = 0; i < (lvlDifficulty * lvl); i++) {
            do {
                ozero = random.nextInt(boardSize);
                lozero = random.nextInt(boardSize);
            } while (sudokuBoard.get(ozero, lozero) == 0);
            sudokuBoard.set(ozero, lozero, 0);
        }
    }

    public void zeroFields(int lvl, SudokuBoard sudokuBoard) {
        switch (lvl) {
            //case 1 - easy
            case 1:
                randomZero(1, sudokuBoard);
                break;

            //case 2 - medium
            case 2:
                randomZero(2, sudokuBoard);
                break;

            //case 3 - hard
            case 3:
                randomZero(3, sudokuBoard);
                break;

            default:
                break;
        }
    }


}