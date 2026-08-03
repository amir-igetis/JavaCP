package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinNumOfPushesToTypeWordI {
    public static void main(String[] args) {
        String word = "abcde";
        System.out.println(minimumPushes(word));
    }

    ///  greedy tc O(n) sc O(1)
    static int minimumPushes(String word) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            ans += i / 8 + 1;

        return ans;
    }

    /// math tc & sc O(1)
    static int minimumPushesI(String word) {
        int n = word.length();
        int m = (n - 1) / 8 + 1;
        return m * (m - 1) * 4 + (n - (m - 1) * 8) * m;
    }
}
