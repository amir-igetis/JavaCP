package leetcodeContest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinDeletionCostToMakeAllCharEqual {
    public static void main(String[] args) {

        String s = "aabaac";
        int[] cost = {1, 2, 3, 4, 1, 10};
        System.out.println(minCost(s, cost));
    }


    static long minCost(String s, int[] cost) {
        Set<Character> st = new HashSet<>();
        for (char ch : s.toCharArray())
            st.add(ch);

        int minCost = Integer.MAX_VALUE;

        for (char ch : st) {
            int currCost = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ch)
                    currCost += cost[i];
            }
            minCost = Math.min(minCost, currCost);

        }

        return minCost;
    }
}

