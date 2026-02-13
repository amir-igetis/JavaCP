package LeetcodeInterview.arrOrStr;

public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) {
//        soln for

        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;

        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];

            }
        }
        return maxProfit;
    }
}
