package LeetcodeInterview.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
//
        int[] nums = {1, 2, 3};
        List<List<Integer>> ans = permute(nums);

        for (List<Integer> i : ans) {
            for (Integer j : i)
                System.out.print(i + ", ");
            System.out.println();
        }
    }

    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), nums, used);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> curr, int[] nums, boolean[] used) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            curr.add(nums[i]);
            used[i] = true;
            backtrack(res, curr, nums, used);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}
