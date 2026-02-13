package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashSet;

public class KeepMultiplyingFoundValByTwo {
    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 1, 12};
        int original = 3;
        System.out.println(findFinalValue(nums, original));
    }

    static int findFinalValue(int[] nums, int original) {
        HashSet<Integer> st = new HashSet<>();
        for (int n : nums)
            st.add(n);

        while (st.contains(original))
            original *= 2;

        return original;
    }
}
