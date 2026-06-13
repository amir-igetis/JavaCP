package leetcodeContest.weeklyContest500;

import java.util.Arrays;

public class CountIndicesWithOppositeParity {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        System.out.println(Arrays.toString(countOppositeParity(nums)));
    }

    static int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int even = 0, odd = 0;
        for (int i : nums) {
            if (i % 2 == 0)
                even++;
            else odd++;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0)
                even--;
            else odd--;

            if (nums[i] % 2 == 0)
                res[i] = odd;
            else res[i] = even;
        }

        return res;
    }
}
