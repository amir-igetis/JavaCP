package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindGreatestCommonDivisorOfAnArray {
    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 9, 10};
        System.out.println(findGCD(nums));
    }

    // Calculate as Required tc O(n+logM) sc O(1)
    static int findGCD(int[] nums) {
        int mx = Integer.MIN_VALUE;
        int mn = Integer.MAX_VALUE;
        for (int i : nums) {
            mn = Math.min(mn, i);
            mx = Math.max(mx, i);
        }
        return gcd(mx, mn);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
