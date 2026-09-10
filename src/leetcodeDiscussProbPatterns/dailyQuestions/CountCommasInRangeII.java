package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountCommasInRangeII {
    public static void main(String[] args) {
        int n = 1002;
        System.out.println(countCommas(n));
    }

    // place value contribution

    /// Time complexity: O(logn).
    ///
    /// The loop runs O((log_1000)*n)
    ///  n) times because p is multiplied by 1000 in each iteration (at most 5 iterations for n≤10^15
    ///
    /// Space complexity: O(1).
    static long countCommas(long n) {
        long p = 1000, res = 0;
        while (p <= n) {
            res += n - p + 1;
            p *= 1000;
        }
        return res;
    }
}
