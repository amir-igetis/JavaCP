package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximizeConsecutiveElemsInAnArrayAfterModification {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 1, 1};
        System.out.println(maxSelectedElements(nums));
    }

    // sorting + DP

    /// Let n be the length of the array nums.
    ///
    /// Time complexity: O(nlogn).
    ///
    /// Sorting takes O(nlogn) time. During the traversal, each element requires only a constant number of hash table operations on average, taking O(n) time. Therefore, the overall time complexity is dominated by sorting, which is O(nlogn).
    ///
    /// Space complexity: O(n).
    ///
    /// The hash table stores O(n) states.
    static int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;
        for (int x : nums) {
            dp.put(
                    x + 1,
                    Math.max(dp.getOrDefault(x + 1, 0), dp.getOrDefault(x, 0) + 1)
            );
            dp.put(
                    x,
                    Math.max(dp.getOrDefault(x, 0), dp.getOrDefault(x - 1, 0) + 1)
            );
            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }
        return ans;

    }
}
