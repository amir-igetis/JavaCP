package LeetcodeInterview.hashMap;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public static void main(String[] args) {
//
        int n = 19;
        System.out.println(isHappy(n));
    }

    static boolean isHappy(int n) {
        Set<Integer> st = new HashSet<>();
        while (n != 1 && !st.contains(n)) {
            st.add(n);
            n = getSumOfSquares(n);
        }

        return n == 1;
    }

    private static int getSumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }
}
