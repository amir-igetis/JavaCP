package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JumpGameIX {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        System.out.println(Arrays.toString(maxValueI(nums)));
    }

    // Interval Divide and Conquer
    private record Item(int value, int index) {
    }

    static int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Item[] prevMax = new Item[n];
        Item prev = new Item(Integer.MIN_VALUE, -1);
        for (int i = 0; i < n; i++) {
            if (nums[i] > prev.value())
                prev = new Item(nums[i], i);

            prevMax[i] = prev;
        }
        process(n - 1, Integer.MAX_VALUE, 0, prevMax, ans, nums);
        return ans;
    }

    private static void process(int r, int rightMin, int rightMax, Item[] prevMax, int[] ans, int[] nums) {
        int pMax = prevMax[r].value();
        int pivotIndex = prevMax[r].index();
        int currMax = pMax <= rightMin ? pMax : rightMax;

        int nextRightMin = Math.min(pMax, rightMin);
        for (int i = pivotIndex; i <= r; i++) {
            ans[i] = currMax;
            nextRightMin = Math.min(nextRightMin, nums[i]);
        }

        if (pivotIndex == 0)
            return;

        process(pivotIndex - 1, nextRightMin, currMax, prevMax, ans, nums);
    }

    // Monotonic Stack

    private record ItemI(int value, int left, int right) {
    }

    static int[] maxValueI(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        List<ItemI> st = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ItemI curr = new ItemI(nums[i], i, i);
            while (!st.isEmpty() && st.getLast().value() > nums[i]) {
                ItemI top = st.removeLast();
                curr = new ItemI(
                        Math.max(curr.value(), top.value()),
                        top.left(),
                        curr.right()
                );
            }
            st.add(curr);
        }

        for (int i = 0; i < st.size(); i++) {
            for (int j = st.get(i).left(); j <= st.get(i).right(); j++)
                ans[j] = st.get(i).value();
        }
        return ans;
    }

    static int[] maxValueII(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return new int[0];

        int[] pre = new int[n];
        int[] suff = new int[n];
        int[] res = new int[n];

        pre[0] = nums[0];
        for (int i = 1; i < n; i++)
            pre[i] = Math.max(nums[i], pre[i - 1]);

        suff[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suff[i] = Math.min(nums[i], suff[i + 1]);

        res[n - 1] = pre[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            res[i] = pre[i];
            if (pre[i] > suff[i + 1])
                res[i] = res[i + 1];
        }
        return res;
    }
}
