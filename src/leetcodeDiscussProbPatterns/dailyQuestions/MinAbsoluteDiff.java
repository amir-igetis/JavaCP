package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinAbsoluteDiff {
    public static void main(String[] args) {
        int[] nums = {3, 8, -10, 23, 19, -4, -14, 27};
        List<List<Integer>> ans = minimumAbsDifference(nums);
        for (List<Integer> i : ans) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
        System.out.println();
    }

    static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int currDiff = arr[i + 1] - arr[i];
            if (currDiff < minDiff)
                minDiff = currDiff;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == minDiff)
                res.add(Arrays.asList(arr[i], arr[i + 1]));
        }
        return res;
    }
}
