package LeetcodeInterview.math;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
    public static void main(String[] args) {
//
        int[][] points = {
                {1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}
        };
        System.out.println(maxPoints(points));
    }

    static int maxPoints(int[][] points) {
        if (points.length < 3)
            return points.length;

        int maxPoints = 1;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicate = 0, localMax = 1;
            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }

                int gcd = gcd(dx, dy);
                String slope = (dx / gcd) + "/" + (dy / gcd);
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 1) + 1);
                localMax = Math.max(localMax, slopeMap.get(slope));
            }
            maxPoints = Math.max(maxPoints, localMax + duplicate);
        }
        return maxPoints;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
