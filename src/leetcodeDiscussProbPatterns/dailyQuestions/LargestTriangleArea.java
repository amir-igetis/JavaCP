package leetcodeDiscussProbPatterns.dailyQuestions;

public class LargestTriangleArea {
    public static void main(String[] args) {
        int[][] points = {
                {0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}
        };

        System.out.println(largestTriangleArea(points));
    }

    static double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double area = triangleArea(points[i], points[j], points[k]);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private static double triangleArea(int[] p1, int[] p2, int[] p3) {
        return Math.abs(
                p1[0] * (p2[1] - p3[1]) +
                        p2[0] * (p3[1] - p1[1]) +
                        p3[0] * (p1[1] - p2[1])
        ) / 2.0;
    }
}
