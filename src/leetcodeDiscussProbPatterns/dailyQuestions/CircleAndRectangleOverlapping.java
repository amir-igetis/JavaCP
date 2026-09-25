package leetcodeDiscussProbPatterns.dailyQuestions;

public class CircleAndRectangleOverlapping {
    public static void main(String[] args) {
        int radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1;
        System.out.println("Divide into Ranges: ");
        System.out.println(checkOverlap(radius, xCenter, yCenter, x1, y1, x2, y2));
        System.out.println("Minimum Distance from the Circle's Center to the Rectangle: ");
        System.out.println(checkOverlapI(radius, xCenter, yCenter, x1, y1, x2, y2));
    }

    // divide into ranges

    /// Time complexity: O(1).
    ///
    /// Space complexity: O(1).
    static boolean checkOverlap(
            int radius,
            int xCenter,
            int yCenter,
            int x1,
            int y1,
            int x2,
            int y2
    ) {
        /* The center of the circle is inside the rectangle */
        if (x1 <= xCenter && xCenter <= x2 && y1 <= yCenter && yCenter <= y2) {
            return true;
        }
        /* The center of the circle is above the rectangle */
        if (
                x1 <= xCenter &&
                        xCenter <= x2 &&
                        y2 <= yCenter &&
                        yCenter <= y2 + radius
        ) {
            return true;
        }
        /* The center of the circle is below the rectangle */
        if (
                x1 <= xCenter &&
                        xCenter <= x2 &&
                        y1 - radius <= yCenter &&
                        yCenter <= y1
        ) {
            return true;
        }
        /* The center of the circle is to the left of the rectangle */
        if (
                x1 - radius <= xCenter &&
                        xCenter <= x1 &&
                        y1 <= yCenter &&
                        yCenter <= y2
        ) {
            return true;
        }
        /* The center of the circle is to the right of the rectangle */
        if (
                x2 <= xCenter &&
                        xCenter <= x2 + radius &&
                        y1 <= yCenter &&
                        yCenter <= y2
        ) {
            return true;
        }
        /* The upper-left corner of the rectangle */
        if (distance(xCenter, yCenter, x1, y2) <= radius * radius) {
            return true;
        }
        /* The lower-left corner of the rectangle */
        if (distance(xCenter, yCenter, x1, y1) <= radius * radius) {
            return true;
        }
        /* The upper-right corner of the rectangle */
        if (distance(xCenter, yCenter, x2, y2) <= radius * radius) {
            return true;
        }
        /* The lower-right corner of the rectangle */
        if (distance(xCenter, yCenter, x2, y1) <= radius * radius) {
            return true;
        }
        /* No intersection */
        return false;
    }

    private static long distance(int ux, int uy, int vx, int vy) {
        return (long) Math.pow(ux - vx, 2) + (long) Math.pow(uy - vy, 2);
    }

    // Minimum Distance from the Circle's Center to the Rectangle

    /// Time complexity: O(1).
    ///
    /// Space complexity: O(1)
    static boolean checkOverlapI(
            int radius,
            int xCenter,
            int yCenter,
            int x1,
            int y1,
            int x2,
            int y2
    ) {
        double dist = 0;
        if (xCenter < x1 || xCenter > x2) {
            dist += Math.min(
                    Math.pow(x1 - xCenter, 2),
                    Math.pow(x2 - xCenter, 2)
            );
        }
        if (yCenter < y1 || yCenter > y2) {
            dist += Math.min(
                    Math.pow(y1 - yCenter, 2),
                    Math.pow(y2 - yCenter, 2)
            );
        }
        return dist <= radius * radius;
    }
}
