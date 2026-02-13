package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxTheMinimumPoweredCity {
    public static void main(String[] args) {
        int[] stations = {1, 2, 4, 5, 0};
        int r = 1, k = 2;
        System.out.println(maxPower(stations, r, k));
    }

    static long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] power = new long[n], prefix = new long[n + 1];

        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stations[1];

        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n - 1, i + r);
            power[i] = prefix[right + 1] - prefix[left];
        }

        long low = 0, high = (long) 1e18, ans = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canAchieve(power, r, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }

        return ans;
    }

    private static boolean canAchieve(long[] power, int r, int k, long target) {
        int n = power.length;
        long[] added = new long[n];
        long extra = 0;

        for (int i = 0; i < n; i++) {
            if (i - r - 1 >= 0)
                extra -= added[i - r - 1];

            if (power[i] + extra < target) {
                long need = target - (power[i] + extra);
                if (need > k)
                    return false;
                k -= need;

                int addPos = Math.min(n - 1, i + r);
                added[addPos] += need;
                extra += need;
            }
        }
        return true;
    }
}
