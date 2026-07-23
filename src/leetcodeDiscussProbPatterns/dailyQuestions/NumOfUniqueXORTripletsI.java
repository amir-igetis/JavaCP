package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfUniqueXORTripletsI {
    public static void main(String[] args) {
        int[] nums = {1, 2};
        System.out.println(uniqueXorTriplets(nums));
    }

    // find the pattern tc O(logn) sc O(1)
    static int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return n;
        int ans = 1;
        while (ans <= n)
            ans <<= 1;

        return ans;
    }
}
