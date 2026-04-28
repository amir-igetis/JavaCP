package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MinTotalDistTravel {
    public static void main(String[] args) {
        List<Integer> robot = Arrays.asList(0, 4, 6);
        int[][] factory = {{2, 2}, {6, 2}};
        System.out.println(minimumTotalDistanceIII(robot, factory));
    }

    //    using recursion tc O(n^2 * m) ans tc O(m + n)
    static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Sort robots and factories by position
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        // Flatten factory positions according to their capacities
        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        // Recursively calculate minimum total distance with memoization
        return calculateMinDistance(0, 0, robot, factoryPositions);
    }

    private static long calculateMinDistance(
            int robotIdx,
            int factoryIdx,
            List<Integer> robot,
            List<Integer> factoryPositions
    ) {
        // All robots assigned
        if (robotIdx == robot.size()) return 0;
        // No factories left to assign
        if (factoryIdx == factoryPositions.size()) return (long) 1e12;

        // Option 1: Assign current robot to current factory
        long assign =
                Math.abs(robot.get(robotIdx) - factoryPositions.get(factoryIdx)) +
                        calculateMinDistance(
                                robotIdx + 1,
                                factoryIdx + 1,
                                robot,
                                factoryPositions
                        );

        // Option 2: Skip current factory for the current robot
        long skip = calculateMinDistance(
                robotIdx,
                factoryIdx + 1,
                robot,
                factoryPositions
        );

        // Take the minimum and store in memo
        return Math.min(assign, skip);
    }

    //    memoizations tc O(n^2 * m) ans tc O(m * n)
    static long minimumTotalDistanceI(List<Integer> robot, int[][] factory) {
        // Sort robots and factories by position
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        // Flatten factory positions according to their capacities
        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        int robotCount = robot.size();
        int factoryCount = factoryPositions.size();
        long[][] memo = new long[robotCount][factoryCount];
        for (long[] row : memo) Arrays.fill(row, -1);

        // Recursively calculate minimum total distance with memoization
        return calculateMinDistanceI(0, 0, robot, factoryPositions, memo);
    }

    private static long calculateMinDistanceI(
            int robotIdx,
            int factoryIdx,
            List<Integer> robot,
            List<Integer> factoryPositions,
            long[][] memo
    ) {
        // All robots assigned
        if (robotIdx == robot.size()) return 0;
        // No factories left to assign
        if (factoryIdx == factoryPositions.size()) return (long) 1e12;
        // Check memo
        if (memo[robotIdx][factoryIdx] != -1) return memo[robotIdx][factoryIdx];

        // Option 1: Assign current robot to current factory
        long assign =
                Math.abs(robot.get(robotIdx) - factoryPositions.get(factoryIdx)) +
                        calculateMinDistanceI(
                                robotIdx + 1,
                                factoryIdx + 1,
                                robot,
                                factoryPositions,
                                memo
                        );

        // Option 2: Skip current factory for the current robot
        long skip = calculateMinDistanceI(
                robotIdx,
                factoryIdx + 1,
                robot,
                factoryPositions,
                memo
        );

        // Take the minimum and store in memo
        memo[robotIdx][factoryIdx] = Math.min(assign, skip);
        return memo[robotIdx][factoryIdx];
    }

    //    tabulation or dp tc O(n^2 * m) ans tc O(m * n)
    static long minimumTotalDistanceII(List<Integer> robot, int[][] factory) {
        // Sort robots and factories by position
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        // Flatten factory positions according to their capacities
        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        int robotCount = robot.size();
        int factoryCount = factoryPositions.size();

        // Initialize dp array
        long[][] dp = new long[robotCount + 1][factoryCount + 1];

        // Initialize base cases
        for (int i = 0; i < robotCount; i++) {
            dp[i][factoryCount] = (long) 1e12; // No factories left
        }

        // Fill DP table bottom-up
        for (int i = robotCount - 1; i >= 0; i--) {
            for (int j = factoryCount - 1; j >= 0; j--) {
                // Option 1: Assign current robot to current factory
                long assign =
                        Math.abs(robot.get(i) - factoryPositions.get(j)) +
                                dp[i + 1][j + 1];

                // Option 2: Skip current factory for the current robot
                long skip = dp[i][j + 1];

                // Take the minimum option
                dp[i][j] = Math.min(assign, skip);
            }
        }

        // Minimum distance starting from first robot and factory
        return dp[0][0];
    }

    //    space optimized dp tc O(n^2 * m) ans tc O(m + s)
    static long minimumTotalDistanceIII(List<Integer> robots, int[][] factories) {
        // Sort robots and factories by position
        Collections.sort(robots);
        Arrays.sort(factories, Comparator.comparingInt(a -> a[0]));

        // Flatten factory positions according to their capacities
        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] factory : factories) {
            for (int i = 0; i < factory[1]; i++) {
                factoryPositions.add(factory[0]);
            }
        }

        int robotCount = robots.size(), factoryCount = factoryPositions.size();
        long[] next = new long[factoryCount + 1];
        long[] current = new long[factoryCount + 1];

        current[factoryCount] = (long) 1e12;

        // Fill DP table using two rows for optimization
        for (int i = robotCount - 1; i >= 0; i--) {
            for (int j = factoryCount - 1; j >= 0; j--) {
                // Assign current robot to current factory
                long assign =
                        Math.abs((long) robots.get(i) - factoryPositions.get(j)) +
                                next[j + 1];
                // Skip current factory for this robot
                long skip = current[j + 1];
                // Take the minimum option
                current[j] = Math.min(assign, skip);
            }
            // Move to next robot
            System.arraycopy(current, 0, next, 0, factoryCount + 1);
        }

        // Return minimum distance starting from the first robot
        return current[0];
    }

}
