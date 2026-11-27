package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxNumOfItemsFromSaleI {
    public static void main(String[] args) {
        int[][] items = new int[][]{{6, 2}, {2, 6}, {3, 4}};
        int budget = 9;
        System.out.println(maximumSaleItems(items, budget));
    }

    // DP (Knapsack)

    /// Let n be the length of the array items, and let budget be the given budget.
    ///
    /// Time complexity: O(n^2+n⋅budget).
    ///
    /// For each item, counting the items that satisfy the divisibility condition takes O(n) time, and updating the 0-1 knapsack states takes O(budget) time. Finally, finding the maximum over all j∈[0,budget] takes O(budget) time.
    ///
    /// Space complexity: O(budget).
    ///
    /// This is the space used by the dynamic programming array f.
    static int maximumSaleItems(int[][] items, int budget) {
        int[] f = new int[budget + 1];
        int mn = Integer.MAX_VALUE;
        for (int[] item : items) {
            int factor = item[0],
                    price = item[1];
            mn = Math.min(mn, price);
            int cnt = 0;
            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    cnt++;
                }
            }
            for (int j = budget; j >= price; j--) {
                f[j] = Math.max(f[j], f[j - price] + cnt);
            }
        }
        int ans = 0;
        for (int j = 0; j <= budget; j++) {
            ans = Math.max(ans, f[j] + (budget - j) / mn);
        }
        return ans;
    }
}
