package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestBalancedSubarrI {
    public static void main(String[] args) {
        int[] nums = {2, 5, 4, 3};
        System.out.println(longestBalanced(nums));
    }

    //    brute force tc O(n^2) & sc O(n^2)
    static int longestBalanced(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> odd = new HashMap<>();
            Map<Integer, Integer> even = new HashMap<>();
            for (int j = i; j < nums.length; j++) {
                Map<Integer, Integer> mp = (nums[j] & 1) == 1 ? odd : even;
                mp.put(nums[j], mp.getOrDefault(nums[j], 0) + 1);
                if (odd.size() == even.size())
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    static int longestBalancedSubarrayI(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> evenSet = new HashSet<>();
            Set<Integer> oddSet = new HashSet<>();

            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 0)
                    evenSet.add(nums[j]);
                else
                    oddSet.add(nums[j]);

                if (evenSet.size() == oddSet.size()) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

}
