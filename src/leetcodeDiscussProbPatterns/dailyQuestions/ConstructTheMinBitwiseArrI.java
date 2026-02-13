package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.List;

public class ConstructTheMinBitwiseArrI {
    public static void main(String[] args) {
        List<Integer> nums = List.of(2, 3, 5, 7);
        System.out.println(Arrays.toString(minBitwiseArrayI(nums)));
    }

    //    brute force tc O(mn) sc O(1)
    static int[] minBitwiseArray(List<Integer> nums) {
        int[] res = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            int original = nums.get(i);
            int candidate = -1;
            for (int j = 1; j < original; j++) {
                if ((j | (j + 1)) == original) {
                    candidate = j;
                    break;

                }
            }
            res[i] = candidate;
        }

        return res;
    }

    //    bitwise operator tc O(NlogM) sc O(1)
    static int[] minBitwiseArrayI(List<Integer> nums) {
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
