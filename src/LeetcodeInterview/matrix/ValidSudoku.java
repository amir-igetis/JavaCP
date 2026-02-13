package LeetcodeInterview.matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
//
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(isValidSudokuI(board));
    }

    static boolean isValidSudoku(char[][] board) {
        Set<Character>[] rowSt = new HashSet[9];
        Set<Character>[] colSt = new HashSet[9];
        Set<Character>[][] boxSt = new HashSet[3][3];

        for (int i = 0; i < 9; i++) {
            rowSt[i] = new HashSet<>();
            colSt[i] = new HashSet<>();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxSt[i][j] = new HashSet<>();
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char num = board[row][col];
                if (num == '.') continue;
                ;

                if (rowSt[row].contains(num) ||
                        colSt[col].contains(num) ||
                        boxSt[row / 3][col / 3].contains(num))
                    return false;

                rowSt[row].add(num);
                colSt[col].add(num);
                boxSt[row / 3][col / 3].add(num);
            }
        }
        return true;
    }

    static boolean isValidSodokuII(char[][] board) {
        List<Set<Character>> rows = new ArrayList<>();
        List<Set<Character>> cols = new ArrayList<>();
        List<Set<Character>> boxes = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];
                if (val == '.')
                    continue;

                if (!rows.get(r).add(val))
                    return false;

                if (!cols.get(c).add(val))
                    return false;

                int boxIdx = (r / 3) * 3 + (c / 3);
                if (!boxes.get(boxIdx).add(val))
                    return false;
            }
        }
        return true;
    }

    static boolean isValidSudokuI(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9]; // 1D flat array for boxes

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];
                if (val == '.') continue;

                // Row check
                if (rows[r].contains(val)) return false;
                rows[r].add(val);

                // Column check
                if (cols[c].contains(val)) return false;
                cols[c].add(val);

                // Box check using flat index
                int boxIdx = (r / 3) * 3 + (c / 3);
                if (boxes[boxIdx].contains(val)) return false;
                boxes[boxIdx].add(val);
            }
        }

        return true;
    }
}
