package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeparateTheDigitsInAnArray {
    public static void main(String[] args) {
        int[] nums = {13, 25, 83, 77};
        System.out.println(Arrays.toString(separateDigits(nums)));
    }

    // simulation tc O(n logM) & sc O(logM)
    static int[] separateDigits(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i : nums) {
            List<Integer> temp = new ArrayList<>();
            while (i > 0) {
                temp.add(i % 10);
                i /= 10;
            }
            for (int j = temp.size() - 1; j >= 0; j--)
                res.add(temp.get(j));
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            ans[i] = res.get(i);

        return ans;
    }

    // reverse Traversal tc O(n logM) & sc O(1)
    static int[] separateDigitsI(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int x = nums[i];
            while (x > 0) {
                res.add(x % 10);
                x /= 10;
            }
        }

        Collections.reverse(res);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            ans[i] = res.get(i);

        return ans;
    }
}
