package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class SumOfGCDOfFormedPairs {
    public static void main(String[] args) {
        int[] nums = {2, 6, 4};
        System.out.println(gcdSum(nums));
    }

    // simulation tc O(nlogn+nlogU) sc O(n)
    static long gcdSum(int[] nums) {
        int n = nums.length;
        int[] mx = new int[n];
        int prefixMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            mx[i] = prefixMax;
        }
        int[] prefixGcd = new int[n];
        for (int i = 0; i < n; i++)
            prefixGcd[i] = gcd(nums[i], mx[i]);

        Arrays.sort(prefixGcd);

        long ans = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            ans += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        return ans;
    }

    private static int gcd(int x, int y) {
        while (y != 0) {
            int temp = x;
            x = y;
            y = temp % y;

        }
        return x;
    }

    private static int gcdI(int a, int b) {
        return b == 0 ? a : gcdI(b, a % b);
    }
}
