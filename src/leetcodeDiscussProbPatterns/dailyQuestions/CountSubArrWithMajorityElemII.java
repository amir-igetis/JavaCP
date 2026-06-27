package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountSubArrWithMajorityElemII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3};
        int target = 2;
        System.out.println(countMajoritySubarrays(nums, target));
    }

    // prefix sum tc and sc O(n)
    static long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // represents the occurrence count of prefix sums -n, -(n-1), ..., 0, 1, ..., n, with index offset by n.
        int[] pre = new int[n * 2 + 1];
        pre[n] = 1;
        int cnt = n;
        long ans = 0;
        long presum = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == target) {
                presum += pre[cnt];
                ++cnt;
                ++pre[cnt];
            } else {
                --cnt;
                presum -= pre[cnt];
                ++pre[cnt];
            }
            ans += presum;
        }
        return ans;

    }
}
