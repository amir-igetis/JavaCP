package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class SetIntersectionSizeAtLeastTwo {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {3, 7}, {8, 9}};
        System.out.println(intersectionSizeTwo(intervals));
    }

    static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) ->
                a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]
        );

        int a = -1, b = -1;
        int count = 0;

        for (int[] it : intervals) {
            int start = it[0], end = it[1];
            boolean hasA = (a >= start && a <= end);
            boolean hasB = (b >= start && b <= end);

            if (hasA && hasB) {
                continue;
            } else if (hasA) {
                count++;
                b = a;
                a = end;
            } else {
                count += 2;
                b = end - 1;
                a = end;
            }
        }
        return count;
    }
}
