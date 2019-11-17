package ru.job4j.array;

public class MatrixCheck {
    public static boolean isWin(char[][] board) {
        boolean result = false;
        int countXByRow;
        int countXByColl;
        char x = 'X';
        for (int i = 0; i < board.length; i++) {
            countXByRow = 0;
            countXByColl = 0;
            for (int j = 0; j < board.length; j++) {
                if (x == board [i][j])
                    countXByRow += 1;
                if (x == board [j][i])
                    countXByColl += 1;
            }
            if ((countXByRow == board.length)||(countXByColl == board.length)) {
                result = true;
                break;
            }
        }
        return result;
    }
}