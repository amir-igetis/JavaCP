package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinDistBetweenThreeEqualElemsI {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 3};
        System.out.println(minimumDistanceI(nums));
    }

    static int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = n + 1;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[i] != nums[j])
                    continue;

                for (int k = j + 1; k < n; k++) {
                    if (nums[j] == nums[k]) {
                        ans = Math.min(ans, k - i);
                        break;
                    }
                }
            }
        }
        return ans == n + 1 ? -1 : ans * 2;
    }

    // optimized using map
    static int minimumDistanceI(int[] nums) {
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int ans = Integer.MAX_VALUE;
        for (List<Integer> list : mp.values()) {
            int size = list.size();
            if (size < 3)
                continue;

            for (int i = 0; i <= size - 3; i++) {
                int first = list.get(i);
                int third = list.get(i + 2);

                int dist = 2 * (third - first);
                ans = Math.min(ans, dist);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;


    }
}
