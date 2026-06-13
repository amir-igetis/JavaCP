package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.PriorityQueue;

public class MaxTotalSubarrValII {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        int k = 2;
        System.out.println(maxTotalValueI(nums, k));
    }

    // sparse table + max heap tc O(nlogn+klogn) + sc O(nlogn)
    static long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int logn = 32 - Integer.numberOfLeadingZeros(n);
        int[][] stMax = new int[n][logn];
        int[][] stMin = new int[n][logn];
        for (int i = 0; i < n; i++) {
            stMax[i][0] = stMin[i][0] = nums[i];
        }
        for (int j = 1; j < logn; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[i][j] = Math.max(
                        stMax[i][j - 1],
                        stMax[i + (1 << (j - 1))][j - 1]
                );
                stMin[i][j] = Math.min(
                        stMin[i][j - 1],
                        stMin[i + (1 << (j - 1))][j - 1]
                );
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int l = 0; l < n; l++) {
            int j = 31 - Integer.numberOfLeadingZeros(n - 1 - l + 1);
            int mx = Math.max(stMax[l][j], stMax[n - 1 - (1 << j) + 1][j]);
            int mn = Math.min(stMin[l][j], stMin[n - 1 - (1 << j) + 1][j]);
            pq.offer(new int[]{mx - mn, l, n - 1});
        }
        long ans = 0;
        while (k-- > 0) {
            int[] top = pq.poll();
            ans += top[0];
            int l = top[1];
            int r = top[2];
            if (r > l) {
                int j = 31 - Integer.numberOfLeadingZeros(r - 1 - l + 1);
                int mx = Math.max(stMax[l][j], stMax[r - 1 - (1 << j) + 1][j]);
                int mn = Math.min(stMin[l][j], stMin[r - 1 - (1 << j) + 1][j]);
                pq.offer(new int[]{mx - mn, l, r - 1});
            }
        }
        return ans;
    }

    // segment tree + max heap tc O((n + k)logn) & sc O(n)
    private static class SegTree {

        int[] maxv;
        int[] minv;
        int n;

        SegTree(int[] nums) {
            n = nums.length;
            maxv = new int[n * 4];
            minv = new int[n * 4];
            build(1, 0, n - 1, nums);
        }

        void build(int node, int l, int r, int[] nums) {
            if (l == r) {
                maxv[node] = minv[node] = nums[l];
                return;
            }
            int m = (l + r) / 2;
            build(node * 2, l, m, nums);
            build(node * 2 + 1, m + 1, r, nums);
            maxv[node] = Math.max(maxv[node * 2], maxv[node * 2 + 1]);
            minv[node] = Math.min(minv[node * 2], minv[node * 2 + 1]);
        }

        int queryMax(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return maxv[node];
            }
            int m = (l + r) / 2;
            int res = Integer.MIN_VALUE;
            if (ql <= m) {
                res = Math.max(res, queryMax(node * 2, l, m, ql, qr));
            }
            if (qr > m) {
                res = Math.max(res, queryMax(node * 2 + 1, m + 1, r, ql, qr));
            }
            return res;
        }

        int queryMin(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return minv[node];
            }
            int m = (l + r) / 2;
            int res = Integer.MAX_VALUE;
            if (ql <= m) {
                res = Math.min(res, queryMin(node * 2, l, m, ql, qr));
            }
            if (qr > m) {
                res = Math.min(res, queryMin(node * 2 + 1, m + 1, r, ql, qr));
            }
            return res;
        }
    }

    static long maxTotalValueI(int[] nums, int k) {
        int n = nums.length;
        SegTree seg = new SegTree(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int l = 0; l < n; l++) {
            pq.offer(
                    new int[]{
                            seg.queryMax(1, 0, n - 1, l, n - 1) -
                                    seg.queryMin(1, 0, n - 1, l, n - 1),
                            l,
                            n - 1,
                    }
            );
        }
        long ans = 0;
        while (k-- > 0) {
            int[] top = pq.poll();
            ans += top[0];
            int l = top[1];
            int r = top[2];
            if (r > l) {
                pq.offer(
                        new int[]{
                                seg.queryMax(1, 0, n - 1, l, r - 1) -
                                        seg.queryMin(1, 0, n - 1, l, r - 1),
                                l,
                                r - 1,
                        }
                );
            }
        }
        return ans;
    }
}


