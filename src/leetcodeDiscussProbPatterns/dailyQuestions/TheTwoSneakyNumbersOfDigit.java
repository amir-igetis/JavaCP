package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class TheTwoSneakyNumbersOfDigit {
    public static void main(String[] args) {
        int[] nums = {0, 3, 2, 1, 3, 2};
        System.out.println(Arrays.toString(getSneakyNumbersI(nums)));
    }

    static int[] getSneakyNumbers(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int num : nums) {
            if (seen.contains(num))
                duplicates.add(num);
            else seen.add(num);
        }

        return new int[]{duplicates.get(0), duplicates.get(1)};
    }

    static int[] getSneakyNumbersI(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] res = new int[2];
        int idx = 0;
        for (int num : nums) {
            count[num]++;
            if (count[num] == 2)
                res[idx++] = num;
        }
        return res;
    }
}
