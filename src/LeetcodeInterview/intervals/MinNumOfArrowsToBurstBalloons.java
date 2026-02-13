package LeetcodeInterview.intervals;

import java.util.Arrays;

public class MinNumOfArrowsToBurstBalloons {
    public static void main(String[] args) {
//
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(findMinArrowShots(points));
    }

    static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int arrows = 0;
        int i = 0;
        while (i < points.length) {
            int end = points[i][1];
            while (i < points.length && points[i][0] <= end) {
                end = Math.min(end, points[i][1]);
                i++;
            }
            arrows++;
        }
        return arrows;
    }
}
