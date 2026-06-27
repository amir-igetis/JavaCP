package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountSubArrWithMajorityElem {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(countMajoritySubarrays(nums, target));
    }

    static int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == target)
                    count += 1;
                else count -= 1;
                if (count > 0)
                    ans++;
            }
        }
        return ans;
    }
}
