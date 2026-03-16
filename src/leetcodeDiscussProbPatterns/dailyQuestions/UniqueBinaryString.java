package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashSet;
import java.util.Set;

public class UniqueBinaryString {
    public static void main(String[] args) {
        String[] nums = {"01", "10"};
        System.out.println(findDifferentBinaryStringII(nums));
    }

//    recursively generate all string tc & sc O(N^2)

    static int n;
    static Set<String> numsSet = new HashSet<>();

    static String findDifferentBinaryString(String[] nums) {
        n = nums.length;
        for (String s : nums) {
            numsSet.add(s);
        }
        return generate("");
    }

    private static String generate(String curr) {
        if (curr.length() == n) {
            if (!numsSet.contains(curr))
                return curr;

            return "";
        }
        String addZero = generate(curr + "0");
        if (addZero.length() > 0)
            return addZero;

        return generate(curr + "1");
    }

    //    iterate over integer equivalents tc O(N^2) and sc O(1)
    static String findDifferentBinaryStringI(String[] nums) {
        Set<Integer> integers = new HashSet<>();
        for (String num : nums)
            integers.add(Integer.parseInt(num, 2));

        int n = nums.length;
        for (int num = 0; num <= n; num++) {
            if (!integers.contains(num)) {
                String ans = Integer.toBinaryString(num);
                while (ans.length() < n)
                    ans = "0" + ans;

                return ans;
            }
        }
        return "";
    }


    //    cantor's diagonal argument tc O(N) and sc O(1)
    static String findDifferentBinaryStringII(String[] nums) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            Character curr = nums[i].charAt(i);
            ans.append(curr == '0' ? '1' : '0');
        }
        return ans.toString();
    }
}
