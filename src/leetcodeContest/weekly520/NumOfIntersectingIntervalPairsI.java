package leetcodeContest.weekly520;

public class NumOfIntersectingIntervalPairsI {

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(countIntersectingIntervals(intervals));
    }

    static int countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = intervals[i][0];
                int b = intervals[j][0];
                int c = intervals[i][1];
                int d = intervals[j][1];
                if (Math.max(a, b) <= Math.min(c, d))
                    count++;
            }
        }

        return count;
    }
}
