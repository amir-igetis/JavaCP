package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinNumOfFlipsToMakeTheBinaryStrAlternating {
    public static void main(String[] args) {
        String s = "111000";
        System.out.println(minFlips(s));
    }

    static int minFlips(String s) {
        int n = s.length();
        int ans1 = 0, ans2 = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 2 * n; i++) {
            int curr = s.charAt(i % n) - '0';
            if (i % 2 != curr)
                ans1++;
            if ((i + 1) % 2 != curr)
                ans2++;
            if (i >= n) {
                int prev = s.charAt(i - n) - '0';
                if ((i - n) % 2 != prev)
                    ans1--;
                if ((i - n + 1) % 2 != prev)
                    ans2--;
            }
            if (i >= n - 1)
                ans = Math.min(ans, Math.min(ans1, ans2));

        }
        return ans;
    }

    // another solution same O(n) and sc O(1)
    static int minFlipsI(String s) {
        int n = s.length();
        int[][] cnt = new int[2][2];

        // cnt[0][0] = number of '0' in even positions
        // cnt[0][1] = number of '0' in odd positions
        // cnt[1][0] = number of '1' in even positions
        // cnt[1][1] = number of '1' in odd positions

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            cnt[digit][i % 2]++;
        }

        int ans = cnt[1][0] + cnt[0][1]; // make even=0, odd=1
        ans = Math.min(ans, cnt[0][0] + cnt[1][1]); // make even=1, odd=0

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            // remove current char from its position
            cnt[digit][i % 2]--;

            // simulate rotation (send char to end)
            cnt[digit][(n + i) % 2]++;

            ans = Math.min(ans, cnt[1][0] + cnt[0][1]);
            ans = Math.min(ans, cnt[0][0] + cnt[1][1]);
        }

        return ans;
    }

}
