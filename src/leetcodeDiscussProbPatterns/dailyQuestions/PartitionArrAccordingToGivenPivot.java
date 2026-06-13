package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.LinkedList;

public class PartitionArrAccordingToGivenPivot {
    public static void main(String[] args) {
        int[] nums = {9, 12, 5, 10, 14, 3, 10};
        int pivot = 10;
        System.out.println(Arrays.toString(pivotArrayI(nums, pivot)));
    }

    // dynamic lists tc & sc O(n)
    static int[] pivotArray(int[] nums, int pivot) {
        LinkedList<Integer> less = new LinkedList<>();
        LinkedList<Integer> equal = new LinkedList<>();
        LinkedList<Integer> greater = new LinkedList<>();
        for (int num : nums) {
            if (num < pivot)
                less.add(num);
            else if (num > pivot)
                greater.add(num);
            else equal.add(num);
        }
        less.addAll(equal);
        less.addAll(greater);

        int i = 0;
        int[] ans = new int[nums.length];
        for (int num : less)
            ans[i++] = num;

        return ans;
    }

    // two passes with fixed arr tc & sc O(n)
    static int[] pivotArrayI(int[] nums, int pivot) {
        int less = 0;
        int equal = 0;
        for (int num : nums) {
            if (num < pivot) less++;
            else if (num == pivot) equal++;
        }

        int[] ans = new int[nums.length];
        int lessI = 0;
        int equalI = less;
        int greaterI = less + equal;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < pivot) {
                ans[lessI] = num;
                lessI++;
            } else if (num > pivot) {
                ans[greaterI] = num;
                greaterI++;
            } else {
                ans[equalI] = num;
                equalI++;
            }
        }
        return ans;
    }

}
