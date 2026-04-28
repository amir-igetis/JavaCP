package leetcodeContest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class D {

    public static void main(String[] args) {
        int[] nums = {5, 4, 2};
        int k = 2;

        System.out.println(maxAlternatingSum(nums, k));
    }

    static long maxAlternatingSum(int[] nums, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> mp = new HashMap<>();
        int r = 1;
        for (int x : sorted) {
            if (!mp.containsKey(x))
                mp.put(x, r++);
        }

        int size = r + 2;

        SegmentTree upTree = new SegmentTree(size);
        SegmentTree downTree = new SegmentTree(size);

        long[] up = new long[n];
        long[] down = new long[n];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int val = mp.get(nums[i]);

            if (i - k >= 0) {
                int prevVal = mp.get(nums[i - k]);
                upTree.update(prevVal, up[i - k]);
                downTree.update(prevVal, down[i - k]);
            }

            long bestDown = downTree.query(1, val - 1);
            long bestUp = upTree.query(val + 1, size);

            up[i] = nums[i];
            down[i] = nums[i];

            if (bestDown != Long.MIN_VALUE)
                up[i] = Math.max(up[i], nums[i] + bestDown);

            if (bestUp != Long.MIN_VALUE)
                down[i] = Math.max(down[i], nums[i] + bestUp);

            ans = Math.max(ans, Math.max(up[i], down[i]));
        }

        return ans;
    }
}

class SegmentTree {
    int size;
    long[] tree;

    SegmentTree(int n) {
        size = n;
        tree = new long[4 * n];
        Arrays.fill(tree, Long.MIN_VALUE);
    }

    void update(int idx, long val) {
        update(1, 1, size, idx, val);
    }

    void update(int node, int start, int end, int idx, long val) {
        if (start == end) {
            tree[node] = Math.max(tree[node], val);
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid)
            update(2 * node, start, mid, idx, val);
        else
            update(2 * node + 1, mid + 1, end, idx, val);

        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    long query(int l, int r) {
        if (l > r) return Long.MIN_VALUE;
        return query(1, 1, size, l, r);
    }

    long query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Long.MIN_VALUE;

        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return Math.max(
                query(2 * node, start, mid, l, r),
                query(2 * node + 1, mid + 1, end, l, r)
        );
    }
}
