package striverAToZ.greedyAlgo.mediumHard;

import java.util.Arrays;

public class ShortestJobFirst {
    public static void main(String[] args) {
        int[] jobs = {4, 3, 7, 1, 2};
        System.out.println(calculateAverageWaitTime(jobs));
    }

    // tc O(nlog n + n) & sc O(1)
    static float calculateAverageWaitTime(int[] jobs) {
        Arrays.sort(jobs);
        float waitTime = 0;
        int totalTime = 0;
        int n = jobs.length;
        for (int i = 0; i < n; i++) {
            waitTime += totalTime;
            totalTime += jobs[i];
        }
        return waitTime / n;
    }
}
