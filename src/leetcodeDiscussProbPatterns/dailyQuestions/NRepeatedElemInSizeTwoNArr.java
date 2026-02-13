package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class NRepeatedElemInSizeTwoNArr {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3};
        System.out.println(repeatedNTimesI(nums));
    }

    //    count tc & sc O(N)
    static int repeatedNTimes(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i : nums) {
            mp.put(i, mp.getOrDefault(i, 0) + 1);
        }
        for (int i : mp.keySet())
            if (mp.get(i) > 1)
                return i;
        return -1;
    }

    //    compare  tc & sc O(N) and O(1)
    static int repeatedNTimesI(int[] nums) {
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] == nums[j + i])
                    return nums[j];
            }
        }
        return -1;
    }
}
