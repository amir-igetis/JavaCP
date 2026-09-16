package leetcodeDiscussProbPatterns.dailyQuestions;

public class UniqueThreeDigitEvenNums {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(totalNumbers(nums));
    }

    // enumeration

    /// Let n be the length of the digits array.
    ///
    /// Time complexity: O(n^3).
    ///
    /// We use three nested loops to enumerate the three indices i, j, and k, which takes O(n^3) time.
    ///
    /// Space complexity: O(1).
    ///
    /// The boolean array vis has a fixed length of 1000, independent of the input size.
    static int totalNumbers(int[] digits) {
        int n = digits.length;
        boolean[] vis = new boolean[1000];
        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (k == i || k == j || digits[k] % 2 != 0) {
                        continue;
                    }
                    int x = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (!vis[x]) {
                        vis[x] = true;
                        ++ans;
                    }
                }
            }
        }

        return ans;
    }
}
