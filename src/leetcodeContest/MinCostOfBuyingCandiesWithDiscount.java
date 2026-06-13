package leetcodeContest;

import java.util.Arrays;

public class MinCostOfBuyingCandiesWithDiscount {
    public static void main(String[] args) {
        int[] cost = {1, 2, 3};
        System.out.println(minimumCost(cost));
    }

    static int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int res = 0;
        int n = cost.length;
        for (int i = n - 1; i >= 0; i--) {
            if ((n - 1 - i) % 3 != 2)
                res += cost[i];
        }
        return res;
    }
}
