package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreatestSumDivisibleByThree {
    public static void main(String[] args) {
        int[] nums = {3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree(nums));
    }

    static int maxSumDivThree(int[] nums) {
        int sum = 0;
        List<Integer> rem1 = new ArrayList<>();
        List<Integer> rem2 = new ArrayList<>();

        for (int x : nums) {
            sum += x;
            if (x % 3 == 1)
                rem1.add(x);
            else if (x % 3 == 2)
                rem2.add(x);
        }

        Collections.sort(rem1);
        Collections.sort(rem2);

        if (sum % 3 == 0)
            return sum;

        int ans = 0;

        if (sum % 3 == 1) {
            int remove1 = rem1.size() > 0 ?
                    rem1.get(0) : Integer.MAX_VALUE;
            int remove2 = rem2.size() > 1 ?
                    rem2.get(0) + rem2.get(1) : Integer.MAX_VALUE;
            ans = sum - Math.min(remove1, remove2);
        }

        if (sum % 3 == 2) {
            int remove1 = rem2.size() > 0 ?
                    rem2.get(0) : Integer.MAX_VALUE;
            int remove2 = rem1.size() > 1 ?
                    rem1.get(0) + rem1.get(1) : Integer.MAX_VALUE;
            ans = sum - Math.min(remove1, remove2);
        }

        return ans < 0 ? 0 : ans;
    }
}
