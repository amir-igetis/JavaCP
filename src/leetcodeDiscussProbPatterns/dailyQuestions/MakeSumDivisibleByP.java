package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class MakeSumDivisibleByP {
    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        int p = 6;
        System.out.println(minSubarray(nums, p));
    }

    static int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int x : nums)
            total += x;

        int need = (int) (total % p);
        if (need == 0)
            return 0;

        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);

        long prefix = 0;
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefix = (prefix + nums[i]) % p;
            int target = (int) ((prefix - need + p) % p);

            if (mp.containsKey(target)) {
                ans = Math.min(ans, i - mp.get(target));
            }

            mp.put((int) prefix, i);
        }
        return (ans == nums.length ? -1 : ans)
                ;
    }

}
