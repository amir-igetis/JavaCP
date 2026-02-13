package codeforcesContest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B {

    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char cell : row)
                System.out.print(cell);
            System.out.println();
        }
    }

    private static char[][] redGrid(char[][] grid, int n, int k) {
        int redSize = n / k;
        char[][] redGrid = new char[redSize][redSize];
        for (int i = 0; i < redSize; i++) {
            for (int j = 0; j < redSize; j++) {
                redGrid[i][j] = grid[i * k][j * k];
            }
        }
        return redGrid;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<char[][]> res = new ArrayList<>();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            sc.nextLine();
            char[][] grid = new char[n][n];
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                grid[i] = line.toCharArray();
            }
            char[][] redGrid = redGrid(grid, n, k);
            res.add(redGrid);
        }
        for (char[][] i : res) {
            printGrid(i);
            System.out.println();
        }
        sc.close();

//        my code(wrong)
//        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
//        while (t-- > 0) {
//            int n = sc.nextInt();
//            int k = sc.nextInt();
//            int[][] arr = new int[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    arr[i][j] = sc.nextInt();
//                }
//            }
//            int lenOfAns = n % k;
//            int[][] ans = new int[lenOfAns][lenOfAns];
//            for (int i = 0; i < lenOfAns; i++) {
//                for (int j = 0; j < lenOfAns; j++) {
//                    ans[i][j] = arr[i * lenOfAns + 1][j * lenOfAns + 1];
//                }
//            }
//
//            for (int i = 0; i < ans.length; i++) {
//                for (int j = 0; j < ans[0].length; i++) {
//                    System.out.print(ans[i][j]);
//                }
//                System.out.println();
//            }
//        }
    }
}
