package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Stack;

public class MinOpsToConvertAllElemsToZero {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 1, 2};
        System.out.println(minOperations(nums));
    }

    // using monotonic stack
    static int minOperations(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int res = 0;
        for (int n : nums) {
            while (!st.isEmpty() && st.peek() > n)
                st.pop();

            if (n == 0)
                continue;

            if (st.isEmpty() || st.peek() < n) {
                res++;
                st.push(n);
            }
        }
        return res;
    }
}
