package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinNumberOfSecondToMakeMountainHeightZero {
    public static void main(String[] args) {
        int mountainHeight = 4;
        int[] workerTimes = {2, 1, 1};
        System.out.println(minNumberOfSeconds(mountainHeight, workerTimes));
    }

    static long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long lo = 1, hi = 10000000000000000L;

        while (lo < hi) {
            long mid = (lo + hi) >> 1;
            long tot = 0;
            for (int i = 0; i < workerTimes.length && tot < mountainHeight; i++)
                tot += (long) (Math.sqrt((double) mid / workerTimes[i] * 2 + 0.25) - 0.5);
            if (tot >= mountainHeight)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }
}
