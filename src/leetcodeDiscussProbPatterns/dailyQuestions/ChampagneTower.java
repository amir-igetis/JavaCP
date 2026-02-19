package leetcodeDiscussProbPatterns.dailyQuestions;

public class ChampagneTower {
    public static void main(String[] args) {
        int poured = 1, query_row = 1, query_glass = 1;
        System.out.println(champagneTower(poured, query_row, query_glass));
    }

    static double champagneTower(int poured, int query_row, int query_glass) {
        double[] currRow = new double[1];
        currRow[0] = poured;
        for (int i = 0; i <= query_row; i++) {
            double[] nextRow = new double[i + 2];
            for (int j = 0; j <= i; j++) {
                if (currRow[j] > 1) {
                    double overflow = (currRow[j] - 1) / 2.0;
                    nextRow[j] += overflow;
                    nextRow[j + 1] += overflow;
                    currRow[j] = 1;
                }
            }
            if (i != query_row)
                currRow = nextRow;
        }
        return Math.min(1.0, currRow[query_glass]);

    }

    static double champagneTowerI(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 1][query_row + 1];
        dp[0][0] = poured;
        for (int i = 0; i < query_row; i++) {
            for (int j = 0; j <= i; j++) {
                double flow = (dp[i][j] - 1) / 2.0;
                if (flow > 0) {
                    dp[i + 1][j] += flow;
                    dp[i + 1][j + 1] += flow;
                }
            }
        }
        return Math.min(1, dp[query_row][query_glass]);
    }
}

