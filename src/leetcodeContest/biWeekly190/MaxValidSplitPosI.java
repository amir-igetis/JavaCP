package leetcodeContest.biWeekly190;

public class MaxValidSplitPosI {
    public static void main(String[] args) {
        int[] nums = { 10, 30, 15, 10 };
        int[] nums2 = { 2, 10, 14 };
        int[] nums3 = { 2, 4 };
        System.out.println(maxValidSplitPos(nums));
        System.out.println(maxValidSplitPos(nums2));
        System.out.println(maxValidSplitPos(nums3));
    }

    static int maxValidSplitPos(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int remove = 0; remove <= n; remove++) {
            int m = (remove == n) ? n : n - 1;
            int[] arr = new int[m];

            int index = 0;

            for (int i = 0; i < n; i++) {
                if (i != remove)
                    arr[index++] = nums[i];

            }
            if (m <= 1)
                continue;

            int[] prefix = new int[m];

            prefix[0] = arr[0];

            for (int i = 1; i < m; i++)
                prefix[i] = gcd(prefix[i - 1], arr[i]);

            int[] suffix = new int[m];

            suffix[m - 1] = arr[m - 1];

            for (int i = m - 2; i >= 0; i--)
                suffix[i] = gcd(suffix[i + 1], arr[i]);

            int score = 0;

            for (int i = 0; i < m - 1; i++) {

                int leftGcd = prefix[i];
                int rightGcd = suffix[i + 1];

                if (leftGcd == rightGcd)
                    score++;

            }

            ans = Math.max(ans, score);
        }

        return ans;
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}