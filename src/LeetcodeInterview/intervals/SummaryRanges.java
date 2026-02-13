package LeetcodeInterview.intervals;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
//

        int[] nums = {0, 1, 2, 4, 5, 7};
        List<String> ans = summaryRanges(nums);
        for (String i : ans) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) return res;

        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                res.add(formatRange(start, nums[i - 1]));
                start = nums[i];
            }
        }
        res.add(formatRange(start, nums[nums.length - 1]));
        return res;
    }

    private static String formatRange(int start, int end) {
        return start == end ?
                String.valueOf(start) : start + "->" + end;
    }
}
