package leetcodeDiscussProbPatterns.dailyQuestions;

public class ConstructUniformParityArrII {
    public static void main(String[] args) {
        int[] nums1 = {1, 4, 7};
        System.out.println(uniformArray(nums1));
    }

    // Classification Discussion

    /// Let n be the length of the array nums_1
    ///
    /// Time complexity: O(n).
    ///
    /// We only need to traverse the array once to find the minimum value and to determine if there is an odd number.
    ///
    /// Space complexity: O(1).
    ///
    /// Use only constant extra space.
    static boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean hasOdd = false;
        for (int v : nums1) {
            if (v < min)
                min = v;
            if ((v & 1) == 1)
                hasOdd = true;
        }
        if ((min & 1) == 1)
            return true;

        return !hasOdd;
    }
}
