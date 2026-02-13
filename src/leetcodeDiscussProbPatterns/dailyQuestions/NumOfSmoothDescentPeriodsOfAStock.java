package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfSmoothDescentPeriodsOfAStock {
    public static void main(String[] args) {
        int[] prices = {3, 2, 1, 4};
        System.out.println(getDescentPeriods(prices));
    }

    static long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long res = 1;
        int prev = 1;
        for (int i = 1; i < n; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                prev++;
            } else {
                prev = 1;
            }
            res += prev;
        }
        return res;
    }
}
