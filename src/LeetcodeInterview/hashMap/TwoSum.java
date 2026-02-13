package LeetcodeInterview.hashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
//

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (mp.containsKey(comp)) {
                return new int[]{mp.get(comp), i};
            } else {
                mp.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};

    }
}
