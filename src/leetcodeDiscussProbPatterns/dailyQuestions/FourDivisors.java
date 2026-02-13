package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class FourDivisors {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(sumFourDivisorsI(nums));
    }

    static int sumFourDivisorsI(int[] nums) {
        int ans = 0;

        for (int n : nums) {
            int a = 0, b = 0;
            int cnt = 0;

            // Find at most one divisor pair
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    a = i;
                    b = n / i;
                    cnt++;
                    if (cnt > 1) break; // more than 2 divisors
                }
            }

            // Case 1: n = p^3
            if (cnt == 1 && a == b) {
                int p = a;
                if ((long) p * p * p == n) {
                    ans += 1 + p + p * p + n;
                }
            }
            // Case 2: n = p * q (p != q)
            else if (cnt == 1 && a != b) {
                ans += 1 + a + b + n;
            }
        }
        return ans;
    }

    // my soln
    static int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> div = uniqueDiv(nums[i]);
            if (div.size() == 4) {
                for (int j : div)
                    sum += j;
            }
        }
        return sum;
    }

    private static List<Integer> uniqueDiv(int n) {
        List<Integer> div = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                div.add(i);
                if (i != n / i)
                    div.add(n / i);
            }
        }
        return div;
    }
}
