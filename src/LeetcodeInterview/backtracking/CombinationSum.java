package LeetcodeInterview.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
//
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> ans = combinationSum(candidates, target);

        for (List<Integer> i : ans) {
            for (Integer j : i)
                System.out.print(j + ", ");
            System.out.println();
        }
    }

    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> curr, int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (target < 0)
            return;
        for (int i = start; i < candidates.length; i++) {
            curr.add(candidates[i]);
            backtrack(res, curr, candidates, target - candidates[i], i);
            curr.removeLast();

//            same as
//            curr.remove(curr.size() - 1);
        }
    }


}
