package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinTimeToMakeRopeColorful {
    public static void main(String[] args) {
        String colors = "abaac";
        int[] neededTime = {1, 2, 3, 4, 5};
        System.out.println(minCost(colors, neededTime));
    }

    static int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return calculateMinCost(
                n - 1, colors, neededTime, dp, 'A', neededTime[n - 1]);
    }

    private static int calculateMinCost(int i, String colorSeq, int[] timeReq, int[] memo, char prevColor, int prevTime) {
        if (i < 0)
            return 0;
        if (memo[i] != -1)
            return memo[i];
        if (colorSeq.charAt(i) == prevColor)
            return memo[i] = calculateMinCost(
                    i - 1, colorSeq, timeReq, memo, colorSeq.charAt(i), Math.max(timeReq[i], prevTime)) + Math.min(timeReq[i], prevTime);
        else
            return memo[i] = calculateMinCost(
                    i - 1, colorSeq, timeReq, memo, colorSeq.charAt(i), timeReq[i]);
    }

    static int minCostI(String colors, int[] neededTime) {
        int n = colors.length();
        int total = 0;
        int sum = neededTime[0];
        int maxTime = neededTime[0];

        for (int i = 1; i < n; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                sum += neededTime[i];
                maxTime = Math.max(maxTime, neededTime[i]);
            } else {
                total += sum - maxTime;
                sum = neededTime[i];
                maxTime = neededTime[i];
            }
        }
        total += sum - maxTime;

        return total;
    }

    static int minCostII(String colors, int[] neededTime) {
        int n = colors.length();
        int totalCost = 0;

        for (int i = 1; i < n; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                // Remove the one with the smaller needed time
                totalCost += Math.min(neededTime[i], neededTime[i - 1]);

                // Keep the balloon with larger time
                neededTime[i] = Math.max(neededTime[i], neededTime[i - 1]);
            }
        }

        return totalCost;
    }
}
