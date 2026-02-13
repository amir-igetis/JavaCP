package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class BinaryPrefixDivisibleByFive {
    public static void main(String[] args) {
        int[] nums = {0, 1, 1};
        List<Boolean> ans = prefixesDivBy5(nums);
        for (boolean i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();
        int val = 0;

        for (int bit : nums) {
            val = (val * 2 + bit) % 5;
            res.add(val == 0);
        }

        return res;
    }
}
