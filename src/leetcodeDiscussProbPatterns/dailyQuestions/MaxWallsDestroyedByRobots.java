package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxWallsDestroyedByRobots {
    public static void main(String[] args) {
        int[] robots = {10, 2}, distance = {5, 1}, walls = {5, 2, 7};
        System.out.println(maxWallsII(robots, distance, walls));
    }

    // Binary Search + Dynamic Programming tc O(nlogm+nlogn+mlogm). Space complexity: O(n+logm).
    static int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] num = new int[n];
        Map<Integer, Integer> robotsToDistance = new HashMap<>();

        for (int i = 0; i < n; i++) {
            robotsToDistance.put(robots[i], distance[i]);
        }

        Arrays.sort(robots);
        Arrays.sort(walls);

        for (int i = 0; i < n; i++) {
            int pos1 = upperBound(walls, robots[i]);

            int leftPos = 0;
            if (i >= 1) {
                int leftBound = Math.max(
                        robots[i] - robotsToDistance.get(robots[i]),
                        robots[i - 1] + 1
                );
                leftPos = lowerBound(walls, leftBound);
            } else {
                leftPos = lowerBound(
                        walls,
                        robots[i] - robotsToDistance.get(robots[i])
                );
            }
            left[i] = pos1 - leftPos;

            int rightPos = 0;
            if (i < n - 1) {
                int rightBound = Math.min(
                        robots[i] + robotsToDistance.get(robots[i]),
                        robots[i + 1] - 1
                );
                rightPos = upperBound(walls, rightBound);
            } else {
                rightPos = upperBound(
                        walls,
                        robots[i] + robotsToDistance.get(robots[i])
                );
            }
            int pos2 = lowerBound(walls, robots[i]);
            right[i] = rightPos - pos2;

            if (i == 0) {
                continue;
            }
            int pos3 = lowerBound(walls, robots[i - 1]);
            num[i] = pos1 - pos3;
        }

        int subLeft = left[0];
        int subRight = right[0];
        for (int i = 1; i < n; i++) {
            int currentLeft = Math.max(
                    subLeft + left[i],
                    subRight -
                            right[i - 1] +
                            Math.min(left[i] + right[i - 1], num[i])
            );
            int currentRight = Math.max(
                    subLeft + right[i],
                    subRight + right[i]
            );
            subLeft = currentLeft;
            subRight = currentRight;
        }

        return Math.max(subLeft, subRight);
    }

    private static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // Two Pointers + Dynamic Programming tc O(nlogm+mlogm). Space complexity: O(n+logm).
    static int maxWallsI(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] num = new int[n];
        Map<Integer, Integer> robotsToDistance = new HashMap<>();

        for (int i = 0; i < n; i++) {
            robotsToDistance.put(robots[i], distance[i]);
        }

        Arrays.sort(robots);
        Arrays.sort(walls);

        int m = walls.length;
        int rightPtr = 0;
        int leftPtr = 0;
        int curPtr = 0;
        int robotPtr = 0;

        for (int i = 0; i < n; i++) {
            while (rightPtr < m && walls[rightPtr] <= robots[i]) {
                rightPtr++;
            }
            int pos1 = rightPtr;

            while (curPtr < m && walls[curPtr] < robots[i]) {
                curPtr++;
            }
            int pos2 = curPtr;

            int leftBound = robots[i] - robotsToDistance.get(robots[i]);
            if (i >= 1) {
                leftBound = Math.max(
                        robots[i] - robotsToDistance.get(robots[i]),
                        robots[i - 1] + 1
                );
            }
            while (leftPtr < m && walls[leftPtr] < leftBound) {
                leftPtr++;
            }
            int leftPos = leftPtr;
            left[i] = pos1 - leftPos;

            int rightBound = robots[i] + robotsToDistance.get(robots[i]);
            if (i < n - 1) {
                rightBound = Math.min(
                        robots[i] + robotsToDistance.get(robots[i]),
                        robots[i + 1] - 1
                );
            }
            while (rightPtr < m && walls[rightPtr] <= rightBound) {
                rightPtr++;
            }
            int rightPos = rightPtr;
            right[i] = rightPos - pos2;

            if (i == 0) {
                continue;
            }
            while (robotPtr < m && walls[robotPtr] < robots[i - 1]) {
                robotPtr++;
            }
            int pos3 = robotPtr;
            num[i] = pos1 - pos3;
        }

        int subLeft = left[0];
        int subRight = right[0];
        for (int i = 1; i < n; i++) {
            int currentLeft = Math.max(
                    subLeft + left[i],
                    subRight -
                            right[i - 1] +
                            Math.min(left[i] + right[i - 1], num[i])
            );
            int currentRight = Math.max(
                    subLeft + right[i],
                    subRight + right[i]
            );
            subLeft = currentLeft;
            subRight = currentRight;
        }

        return Math.max(subLeft, subRight);
    }

    // Two Pointers + Dynamic Programming + Space Optimization tc O(nlogm+mlogm). Space complexity: O(n+logm).

    static int maxWallsII(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] robotDist = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotDist[i][0] = robots[i];
            robotDist[i][1] = distance[i];
        }
        Arrays.sort(robotDist, (a, b) -> a[0] - b[0]);
        Arrays.sort(walls);

        int m = walls.length;
        int rightPtr = 0;
        int leftPtr = 0;
        int curPtr = 0;
        int robotPtr = 0;

        int prevLeft = 0;
        int prevRight = 0;
        int prevNum = 0;
        int subLeft = 0;
        int subRight = 0;

        for (int i = 0; i < n; i++) {
            int robotPos = robotDist[i][0];
            int robotDistVal = robotDist[i][1];

            while (rightPtr < m && walls[rightPtr] <= robotPos) {
                rightPtr++;
            }
            int pos1 = rightPtr;

            while (curPtr < m && walls[curPtr] < robotPos) {
                curPtr++;
            }
            int pos2 = curPtr;

            int leftBound = robotPos - robotDistVal;
            if (i >= 1) {
                leftBound = Math.max(
                        robotPos - robotDistVal,
                        robotDist[i - 1][0] + 1
                );
            }
            while (leftPtr < m && walls[leftPtr] < leftBound) {
                leftPtr++;
            }
            int leftPos = leftPtr;
            int currentLeft = pos1 - leftPos;

            int rightBound = robotPos + robotDistVal;
            if (i < n - 1) {
                rightBound = Math.min(
                        robotPos + robotDistVal,
                        robotDist[i + 1][0] - 1
                );
            }
            while (rightPtr < m && walls[rightPtr] <= rightBound) {
                rightPtr++;
            }
            int rightPos = rightPtr;
            int currentRight = rightPos - pos2;

            int currentNum = 0;
            if (i > 0) {
                while (robotPtr < m && walls[robotPtr] < robotDist[i - 1][0]) {
                    robotPtr++;
                }
                int pos3 = robotPtr;
                currentNum = pos1 - pos3;
            }

            if (i == 0) {
                subLeft = currentLeft;
                subRight = currentRight;
            } else {
                int newsubLeft = Math.max(
                        subLeft + currentLeft,
                        subRight -
                                prevRight +
                                Math.min(currentLeft + prevRight, currentNum)
                );
                int newsubRight = Math.max(
                        subLeft + currentRight,
                        subRight + currentRight
                );
                subLeft = newsubLeft;
                subRight = newsubRight;
            }

            prevLeft = currentLeft;
            prevRight = currentRight;
            prevNum = currentNum;
        }

        return Math.max(subLeft, subRight);
    }

}