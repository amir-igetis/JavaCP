package leetcodeContest;

import java.util.*;

public class A {
    public static void main(String[] args) {
//

        int[] nums = {1, 2, 4, 2, 3, 2};

        List<Integer> ans = findValidElements(nums);
        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        prefix[0] = nums[0];
        for (int i = 1; i < n; i++)
            prefix[i] = Math.max(prefix[i - 1], nums[i]);

        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suffix[i] = Math.max(suffix[i + 1], nums[i]);

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n - 1)
                res.add(nums[i]);
            else if (nums[i] > prefix[i - 1] || nums[i] > suffix[i + 1])
                res.add(nums[i]);
        }

        return res;
    }
}
