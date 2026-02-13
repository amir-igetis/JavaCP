package leetcodeContest;

import java.util.ArrayList;
import java.util.List;

public class FindThePowerOfKSizeSubarraysII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3, 2, 5};
        int k = 3;
        int[] ans = resultsArray(nums, k);
        for (int a : ans)
            System.out.print(a + ",");
    }

    static int[] resultsArrayI(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if (k > nums.length) return ans.stream().mapToInt(Integer::intValue).toArray();
        boolean isConse = true;
        for (int i = 1; i < k; i++) {


            if (nums[i] != nums[i - 1] + 1) {
                isConse = false;
                break;
            }
        }
        if (isConse) {
            ans.add(nums[k - 1]);
        } else {
            ans.add(-1);
        }
        for (int i = 1; i <= nums.length - k; i++) {
            if (isConse) {
                if (nums[i + k - 1] != nums[i + k - 2] + 1)
                    isConse = false;
            } else {
                isConse = true;
                for (int j = i + 1; j < i + k; j++) {
                    if (nums[j] != nums[j - 1] + 1) {
                        isConse = false;
                        break;
                    }
                }
            }
            if (isConse) {
                ans.add(nums[i + k - 1]);
            } else {
                ans.add(-1)
                ;
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] resultsArray(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if (k > nums.length) return ans.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i <= nums.length - k; i++) {
            boolean isConse = true;
            int max = nums[i];
            for (int j = 1; j < k; j++) {
                if (nums[i + j] != nums[i + j - 1] + 1) {
                    isConse = false;
                    break;
                }
            }
            if (isConse) {
                ans.add(nums[i + k - 1]);
            } else ans.add(-1);
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
