package leetcodeContest;

import java.util.*;

public class A {
    public static void main(String[] args) {
//

//        int[] nums = {1, 2, 2};
//        int[] nums = {2, 2, -1, 3, -2, 2, 1, 1, 1, 0, -1};
//        System.out.println(minimumPairRemovalI(nums));

        int n = 3;
        int[][] buildings = {{1, 2}, {2, 2}, {3, 2}, {2, 1}, {2, 3}};
        System.out.println(minimumPairRemoval(n, buildings));
    }

    static int minimumPairRemoval(int n, int[][] buildings) {
        // Maps to track min/max in each row and column
        Map<Integer, Integer> rowMinY = new HashMap<>(), rowMaxY = new HashMap<>();
        Map<Integer, Integer> colMinX = new HashMap<>(), colMaxX = new HashMap<>();

        // Initialize with extreme values
        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            rowMinY.put(x, Math.min(rowMinY.getOrDefault(x, Integer.MAX_VALUE), y));
            rowMaxY.put(x, Math.max(rowMaxY.getOrDefault(x, Integer.MIN_VALUE), y));

            colMinX.put(y, Math.min(colMinX.getOrDefault(y, Integer.MAX_VALUE), x));
            colMaxX.put(y, Math.max(colMaxX.getOrDefault(y, Integer.MIN_VALUE), x));
        }

        int coveredCount = 0;
        // Check each building
        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            boolean hasLeft = rowMinY.get(x) < y;
            boolean hasRight = rowMaxY.get(x) > y;
            boolean hasAbove = colMinX.get(y) < x;
            boolean hasBelow = colMaxX.get(y) > x;

            if (hasLeft && hasRight && hasAbove && hasBelow) {
                coveredCount++;
            }
        }

        return coveredCount;
    }
}
