package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class FindXSumOfAllKLongSubarrI {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 4, 2, 3};
        int k = 6, x = 2;

        System.out.println(Arrays.toString(findXSum(nums, k, x)));
    }

    static int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int j = i; j < i + k; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            }

            List<int[]> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> e : freq.entrySet())
                list.add(new int[]{e.getKey(), e.getValue()});

            list.sort((a, b) -> {
                if (b[1] != a[1])
                    return b[1] - a[1];

                return b[0] - a[0];
            });

            int sum = 0;
            int count = 0;
            for (int[] p : list) {
                if (count == x)
                    break;
                sum += p[0] * p[1];
                count++;
            }
            ans[i] = sum;
        }

        return ans;
    }
}
