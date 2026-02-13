package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.List;

public class ConstructTheMinBitwiseArrayII {
    public static void main(String[] args) {
        List<Integer> nums = List.of(2, 3, 5, 7);
        System.out.println(Arrays.toString(minBitwiseArray(nums)));
    }

//    bitwise approach
    static int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            int res = -1;
            int d = 1;
            while ((x & d) != 0) {
                res = x - d;
                d <<= 1;
            }
            result[i] = res;
        }

        return result;

    }
}
