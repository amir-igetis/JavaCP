package LeetcodeInterview.twoPointers;

import java.util.Arrays;

public class TwoSumIIInputArrSorted {
    public static void main(String[] args) {
//
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    static int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 0)
            return null;

        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int elem = numbers[start] + numbers[end];
            if (target < elem)
                end--;
            else if (target > elem)
                start++;
            else return new int[]{start + 1, end + 1};
        }
        return null;
    }
}
