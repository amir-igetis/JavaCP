package leetcodeContest.weekly506;

import java.util.Arrays;

public class MaximizeSumOfDeviceRatings {
    public static void main(String[] args) {
        int[][] units = {{1, 3}, {2, 2}};
        int[][] unitsI = {{1, 2, 3}, {4, 5, 6}};
        int[][] unitsII = {{5, 5, 5}, {1, 1, 1}};
        int[][] unitsIII = {{5}, {5}, {1}, {4}, {4}};
        System.out.println(maxRatings(units));
        System.out.println(maxRatings(unitsI));
        System.out.println(maxRatings(unitsII));
        System.out.println(maxRatings(unitsIII));
    }

    static long maxRatings(int[][] units) {
        int m = units.length;
        int n = units[0].length;

        if (m == 1) {
            int min = units[0][0];
            for (int val : units[0]) min = Math.min(min, val);
            return min;
        }
        long sumMin1 = 0;
        long sumMin2 = 0;
        int globalMin = Integer.MAX_VALUE;
        int minOfMin2 = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int m1 = Integer.MAX_VALUE;
            int m2 = Integer.MAX_VALUE;
            for (int val : units[i]) {
                if (val <= m1) {
                    m2 = m1;
                    m1 = val;
                } else if (val < m2) {
                    m2 = val;
                }
            }
            if (n == 1) {
                m2 = 0;
            }
            sumMin1 += m1;
            sumMin2 += m2;
            globalMin = Math.min(globalMin, m1);
            minOfMin2 = Math.min(minOfMin2, m2);
        }
        long formulaAns = sumMin2 - minOfMin2 + globalMin;
        return Math.max(sumMin1, formulaAns);
    }
}
