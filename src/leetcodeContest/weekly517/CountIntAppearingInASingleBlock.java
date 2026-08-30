package leetcodeContest.weekly517;

public class CountIntAppearingInASingleBlock {
    public static void main(String[] args) {
        int[] nums = { 3, 3, 1, 2, 2, 1 };
        System.out.println(countSpecialIntegers(nums));
    }

    static int countSpecialIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        boolean[] vis = new boolean[101];
        int left = 0;
        while (left < n) {
            int right = left;
            while (right + 1 < n && nums[right + 1] == nums[left])
                right++;

            int val = nums[left];
            if (!vis[val]) {
                vis[val] = true;
                boolean repeat = false;
                for (int i = right + 1; i < n; i++) {
                    if (nums[i] == val) {
                        repeat = true;
                        break;
                    }
                }

                if (!repeat)
                    ans++;
            }
            left = right + 1;
        }
        return ans;
    }

}
