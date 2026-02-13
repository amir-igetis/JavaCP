package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MaxTotalDamageWithSpellCasting {
    public static void main(String[] args) {

        int[] power = {7, 1, 6, 6};
        System.out.println(maximumTotalDamageI(power));
    }

    static long maximumTotalDamage(int[] power) {
        Map<Integer, Long> damageMap = new HashMap<>();
        for (int p : power)
            damageMap.put(p, damageMap.getOrDefault(0, 0L) + p);

        List<Integer> unique = new ArrayList<>(damageMap.keySet());
        Collections.sort(unique);

        int n = unique.size();
        long[] dp = new long[n];

        dp[0] = damageMap.get(unique.get(0));

        for (int i = 1; i < n; i++) {
            long currDamage = damageMap.get(unique.get(i));
            int diff = unique.get(i) - unique.get(i - 1);

            if (diff <= 2) {
                long include = currDamage;
                if (i >= 2)
                    include += dp[i - 2];
                dp[i] = Math.max(dp[i - 1], include);
            } else {
                dp[i] = dp[i - 1] + currDamage;
            }


        }

        return dp[n - 1];
    }

//    the right solution

    static long maximumTotalDamageI(int[] power) {
        Map<Integer, Long> freq = new HashMap<>();
        for (int p : power) freq.put(p, freq.getOrDefault(p, 0L) + 1);

        List<Integer> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys);

        int n = keys.size();
        long[] dp = new long[n];
        dp[0] = freq.get(keys.get(0)) * keys.get(0);

        for (int i = 1; i < n; i++) {
            long take = freq.get(keys.get(i)) * keys.get(i);
            int prev = binarySearch(keys, i - 1, keys.get(i) - 3);
            if (prev >= 0) take += dp[prev];
            dp[i] = Math.max(dp[i - 1], take);
        }

        return dp[n - 1];
    }

    private static int binarySearch(List<Integer> keys, int end, int value) {
        int l = 0, r = end, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (keys.get(mid) <= value) {
                ans = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }
}
