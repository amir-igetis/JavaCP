package LeetcodeInterview.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
//
        int n = 4, k = 2;
        List<List<Integer>> ans = combine(n, k);
        for (List<Integer> i : ans) {
            for (Integer j : i)
                System.out.println(i + ", ");
            System.out.println();
        }
    }

    static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> curr, int start, int n, int k) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i <= n; i++) {
            curr.add(i);
            backtrack(res, curr, i + 1, n, k);
            curr.remove(curr.size() - 1);
        }
    }
}
