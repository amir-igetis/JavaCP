package leetcodeContest;

import java.util.ArrayList;
import java.util.List;

public class FindThePowerOfKSizeSubarraysI {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,3,2,5};int k = 3;
        int[] ans = resultsArray(arr, k);
        for (int a : ans) {
            System.out.print(a + ",");
        }
    }

    static int[] resultsArray(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= nums.length - k; i++) {
            boolean conse = true;
            int max = nums[i];
            for (int j = i; j < i + k - 1; j++) {
                if (nums[j] + 1 != nums[j + 1]) {
                    conse = false;
                    break;
                }
                max = Math.max(max, nums[j + 1]);
            }
            if (conse) {
                ans.add(max);
            } else {
                ans.add(-1);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
