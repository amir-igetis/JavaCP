package striverAToZ.greedyAlgo.easyProb;

import java.util.ArrayList;
import java.util.List;

public class GreedyAlgoFindMinCoin {

    public static void main(String[] args) {
        int N = 43;
        System.out.println(minPartition(N));
    }

    // soln for https://practice.geeksforgeeks.org/problems/-minimum-number-of-coins4426/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=minimum-number-of-coins
    // Greedy approach
    static List<Integer> minPartition(int N) {
        List<Integer> ans = new ArrayList<>();
        int[] denom = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
        for (int i = denom.length - 1; i >= 0; i--) {
            if (N >= denom[i]) {
                ans.add(denom[i]);
                N -= denom[i++];
            }
        }
        return ans;
    }

}
