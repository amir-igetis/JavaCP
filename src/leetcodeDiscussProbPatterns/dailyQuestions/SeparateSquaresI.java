package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class SeparateSquaresI {
    public static void main(String[] args) {
        int[][] squares = {{0, 0, 1}, {2, 2, 1}};
        System.out.println(separateSquares(squares));

    }


    //    binary search tc O(nlog(lu)) and sc O(1)
    static double separateSquares(int[][] squares) {
        double maxY = 0;
        double totalArea = 0;
        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            totalArea += (double) l * l;
            maxY = Math.max(maxY, (double) (y + l));
        }

        double lo = 0;
        double hi = maxY;
        double eps = 1e-5;
        while (Math.abs(hi - lo) > eps) {
            double mid = (hi + lo) / 2;
            if (check(mid, squares, totalArea)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return hi;
    }

    private static Boolean check(double limitY, int[][] squares, double totalArea) {
        double area = 0;
        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            if (y < limitY) {
                area += (double) l * Math.min(limitY - y, (double) l);
            }
        }
        return area >= totalArea / 2;
    }

//    scanning line tc O(nlog(n)) sc O(n)

    static double separateSquaresI(int[][] squares) {
        long totalArea = 0;
        List<int[]> events = new ArrayList<>();

        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            totalArea += (long) l * l;
            events.add(new int[]{y, l, 1});
            events.add(new int[]{y + l, l, -1});
        }

        // sort by y-coordinate
        events.sort((a, b) -> Integer.compare(a[0], b[0]));
        double coveredWidth = 0; // sum of all bottom edges under the current scanning line
        double currArea = 0; // current cumulative area
        double prevHeight = 0; // height of the previous scanning line

        for (int[] event : events) {
            int y = event[0];
            int l = event[1];
            int delta = event[2];

            int diff = y - (int) prevHeight;
            // additional area between two scanning lines
            double area = coveredWidth * diff;
            // if this part of the area exceeds more than half of the total area
            if (2L * (currArea + area) >= totalArea) {
                return (
                        prevHeight +
                                (totalArea - 2.0 * currArea) / (2.0 * coveredWidth)
                );
            }
            // update width: add width at the start event, subtract width at the end event
            coveredWidth += delta * l;
            currArea += area;
            prevHeight = y;
        }

        return 0.0;
    }
}
