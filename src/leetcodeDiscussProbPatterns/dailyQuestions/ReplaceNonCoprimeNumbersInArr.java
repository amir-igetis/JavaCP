package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.List;
import java.util.Stack;

public class ReplaceNonCoprimeNumbersInArr {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 3, 3, 3};
        System.out.println(replaceNonCoprimes(nums));
    }

    static List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> st = new Stack<>();
        for (int x : nums) {
            st.add(x);
            while (st.size() > 1) {
                int a = st.get(st.size() - 2);
//                int b = st.get(st.size() - 1);
                int b = st.getLast();

                int g = gcd(a, b);
                if (g == 1) break;
                long lcm = (1L * a * b) / g;
//                st.remove(st.size() - 1);
                st.removeLast();
                st.removeLast();
                st.add((int) lcm);
            }
        }
        return st;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
