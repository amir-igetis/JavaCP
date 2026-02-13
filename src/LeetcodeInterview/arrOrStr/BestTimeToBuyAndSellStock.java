package LeetcodeInterview.arrOrStr;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
//        soln for
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int mini = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int currProfit = prices[i] - mini;
            maxProfit = Math.max(maxProfit, currProfit);
            mini = Math.min(mini, prices[i]);
        }

        return maxProfit;
    }
}
