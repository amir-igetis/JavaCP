package leetcodeContest.weekly503;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LimitOccurrencesInSortedArr {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(limitOccurrences(nums, k)));
    }

    static int[] limitOccurrences(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0)
            return new int[0];
        List<Integer> ans = new ArrayList<>();
        int count = 1;
        ans.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                count++;
            else count = 1;

            if (count <= k)
                ans.add(nums[i]);
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);

        return res;
    }
}
