package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MaximizeActiveSectionWithTradeII {
    public static void main(String[] args) {
        String s = "1000100";
        int[][] queries = {{1, 5}, {0, 6}, {0, 4}};
        List<Integer> ans = maxActiveSectionsAfterTrade(s, queries);
        List<Integer> ansI = maxActiveSectionsAfterTradeI(s, queries);
        List<Integer> ansII = maxActiveSectionsAfterTradeII(s, queries);
        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();
        for (Integer i : ansI)
            System.out.print(i + " ");
        System.out.println();
        for (Integer i : ansII)
            System.out.print(i + " ");
        System.out.println();
    }

    // binary search + segment tree tc O(n+qlogn) and sc O(n)
    static List<Integer> maxActiveSectionsAfterTrade(
            String s,
            int[][] queries
    ) {
        int n = s.length();
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                cnt1++;
            }
        }

        List<Integer> zeroBlocks = new ArrayList<>();
        List<Integer> blockLeft = new ArrayList<>();
        List<Integer> blockRight = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int st = i;
            while (i < n && s.charAt(i) == s.charAt(st)) {
                i += 1;
            }
            if (s.charAt(st) == '0') {
                zeroBlocks.add(i - st);
                blockLeft.add(st);
                blockRight.add(i - 1);
            }
        }

        int m = zeroBlocks.size();
        if (m < 2) {
            // continuous 0 blocks less than 2 segments, return the answer directly
            List<Integer> result = new ArrayList<>();
            for (int q = 0; q < queries.length; q++) {
                result.add(cnt1);
            }
            return result;
        }

        int[] tmpSum = new int[m - 1];
        for (int k = 0; k < m - 1; k++) {
            tmpSum[k] = zeroBlocks.get(k) + zeroBlocks.get(k + 1);
        }
        SegmentTree seg = new SegmentTree(tmpSum);
        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0],
                    r = q[1];
            int idx = lowerBound(blockRight, l);
            int jdx = upperBound(blockLeft, r) - 1;

            // at most 1 continuous block of 0s within the substring
            if (idx > m - 1 || jdx < 0 || idx >= jdx) {
                ans.add(cnt1);
                continue;
            }
            int firstLen =
                    blockRight.get(idx) - Math.max(blockLeft.get(idx), l) + 1; // actual length of the first consecutive block of 0s in the substring
            int lastLen =
                    Math.min(blockRight.get(jdx), r) - blockLeft.get(jdx) + 1; // actual length of the last consecutive block of 0s in the substring
            // exactly 2 consecutive 0 blocks within the substring
            if (idx + 1 == jdx) {
                int bestGain = firstLen + lastLen;
                ans.add(cnt1 + bestGain);
                continue;
            }

            int val1 = firstLen + zeroBlocks.get(idx + 1);
            int val2 = zeroBlocks.get(jdx - 1) + lastLen;
            int val3 = seg.query(idx + 1, jdx - 2);
            int bestGain = Math.max(Math.max(val1, val2), val3);
            ans.add(cnt1 + bestGain);
        }

        return ans;
    }

    private static int lowerBound(List<Integer> list, int target) {
        int left = 0,
                right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int upperBound(List<Integer> list, int target) {
        int left = 0,
                right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // binary search + sparse table tc O((n+q)logn) and sc O(nlogn)
    static List<Integer> maxActiveSectionsAfterTradeI(
            String s,
            int[][] queries
    ) {
        int n = s.length();
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') cnt1++;
        }

        List<Integer> zeroBlocks = new ArrayList<>();
        List<Integer> blockLeft = new ArrayList<>();
        List<Integer> blockRight = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int st = i;

            while (i < n && s.charAt(i) == s.charAt(st)) {
                i += 1;
            }

            if (s.charAt(st) == '0') {
                zeroBlocks.add(i - st);
                blockLeft.add(st);
                blockRight.add(i - 1);
            }
        }

        int m = zeroBlocks.size();
        if (m < 2) {
            // continuous 0 blocks less than 2 segments, return the answer directly
            List<Integer> result = new ArrayList<>();
            for (int q = 0; q < queries.length; q++) {
                result.add(cnt1);
            }
            return result;
        }

        List<Integer> tmpSum = new ArrayList<>();
        for (int k = 0; k < m - 1; k++) {
            tmpSum.add(zeroBlocks.get(k) + zeroBlocks.get(k + 1));
        }
        SparseTable st = new SparseTable(tmpSum);
        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0],
                    r = q[1];
            int idx = lowerBoundI(blockRight, l);
            int jdx = upperBoundI(blockLeft, r) - 1;

            // at most 1 continuous block of 0s within the substring
            if (idx > m - 1 || jdx < 0 || idx >= jdx) {
                ans.add(cnt1);
                continue;
            }

            int firstLen =
                    blockRight.get(idx) - Math.max(blockLeft.get(idx), l) + 1; // actual length of the first consecutive block of 0s in the substring
            int lastLen =
                    Math.min(blockRight.get(jdx), r) - blockLeft.get(jdx) + 1; // actual length of the last consecutive block of 0s in the substring
            // exactly 2 consecutive 0 blocks within the substring
            if (idx + 1 == jdx) {
                int bestGain = firstLen + lastLen;
                ans.add(cnt1 + bestGain);
                continue;
            }

            int val1 = firstLen + zeroBlocks.get(idx + 1);
            int val2 = zeroBlocks.get(jdx - 1) + lastLen;
            int val3 = st.query(idx + 1, jdx - 2);
            int bestGain = Math.max(Math.max(val1, val2), val3);
            ans.add(cnt1 + bestGain);
        }

        return ans;
    }

    private static int lowerBoundI(List<Integer> list, int target) {
        int left = 0,
                right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int upperBoundI(List<Integer> list, int target) {
        int left = 0,
                right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


    // Roll bakc the left endpoint tc O(qlogq + n root(n) + q root(n)) sc O(n)
    static List<Integer> maxActiveSectionsAfterTradeII(
            String s,
            int[][] queries
    ) {
        int n = s.length(),
                m = queries.length;
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') cnt1++;
        }
        // left[i]: represents the length of the continuous block ending at position i, which is the same as s[i]
        int[] left = new int[n];
        // right[i]: represents the length of the continuous block starting at position i with the same value as s[i]
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            left[i] =
                    i > 0 && s.charAt(i - 1) == s.charAt(i) ? left[i - 1] + 1 : 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            right[i] =
                    i < n - 1 && s.charAt(i + 1) == s.charAt(i)
                            ? right[i + 1] + 1
                            : 1;
        }

        List<Integer> ans = new ArrayList<>(Collections.nCopies(m, -1));
        int block_size = (int) Math.sqrt(n);
        // query with length greater than block length
        List<int[]> longQueries = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int l = queries[i][0],
                    r = queries[i][1];
            if (r - l + 1 > block_size) {
                longQueries.add(new int[]{l / block_size, l, r, i});
            } else {
                // queries shorter than block length, brute-force calculation
                ans.set(i, cnt1 + bruteForce(s, l, r));
            }
        }

        // sort by the ID of the block where the left endpoint is located as the first keyword, and by the right endpoint as the second keyword
        longQueries.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[2], b[2]);
        });

        Deque<Integer> subZeroBlocks = new ArrayDeque<>();
        int L = 0,
                R = 0,
                bestGain = 0;

        for (int i = 0; i < longQueries.size(); i++) {
            int[] q = longQueries.get(i);
            int bid = q[0],
                    l = q[1],
                    r = q[2],
                    qid = q[3];

            if (i == 0 || bid > longQueries.get(i - 1)[0]) {
                // traverse to a new block, perform initialization operations
                L = (bid + 1) * block_size - 1; // L is initialized to the right endpoint of the block
                R = (bid + 1) * block_size; // R is initialized to the left endpoint of the next block
                subZeroBlocks.clear();
                bestGain = 0;
            }

            while (R <= r) {
                int sz = Math.min(r - R + 1, right[R]);
                if (s.charAt(R) == '0') {
                    if (!subZeroBlocks.isEmpty() && s.charAt(R - 1) == '0') {
                        subZeroBlocks.offerLast(subZeroBlocks.pollLast() + sz);
                    } else {
                        subZeroBlocks.offerLast(sz);
                    }
                    if (subZeroBlocks.size() >= 2) {
                        int last = subZeroBlocks.pollLast();
                        int secondLast = subZeroBlocks.peekLast();
                        subZeroBlocks.offerLast(last);
                        bestGain = Math.max(last + secondLast, bestGain);
                    }
                }
                R += sz;
            }

            // before moving the left endpoint L, backup the value of bestGain
            int tmp_bestGain = bestGain;
            // value of the first element of subZeroBlocks before moving the left endpoint
            int tmp_firstValue = subZeroBlocks.isEmpty()
                    ? -1
                    : subZeroBlocks.peekFirst();
            // the number of digits added from the left during the process of recording the movement of the left endpoint L
            int cnt = 0;

            while (L >= l) {
                int sz = Math.min(L - l + 1, left[L]);
                if (s.charAt(L) == '0') {
                    if (!subZeroBlocks.isEmpty() && s.charAt(L + 1) == '0') {
                        subZeroBlocks.offerFirst(
                                subZeroBlocks.pollFirst() + sz
                        );
                    } else {
                        subZeroBlocks.offerFirst(sz);
                        cnt++;
                    }
                    if (subZeroBlocks.size() >= 2) {
                        int first = subZeroBlocks.peekFirst();
                        subZeroBlocks.pollFirst();
                        int second = subZeroBlocks.peekFirst();
                        subZeroBlocks.offerFirst(first);
                        bestGain = Math.max(first + second, bestGain);
                    }
                }
                L -= sz;
            }

            // answering inquiries
            ans.set(qid, bestGain + cnt1);
            // restore left endpoint L
            L = (bid + 1) * block_size - 1;
            // restore bestGain
            bestGain = tmp_bestGain;
            // restore subZeroBlocks
            for (int j = 0; j < cnt; j++) {
                subZeroBlocks.pollFirst();
            }
            if (tmp_firstValue != -1) {
                subZeroBlocks.pollFirst();
                subZeroBlocks.offerFirst(tmp_firstValue);
            }
        }
        return ans;
    }

    private static int bruteForce(String s, int l, int r) {
        int i = l;
        int best = 0;
        int prev = Integer.MIN_VALUE;

        while (i <= r) {
            int start = i;
            while (i <= r && s.charAt(i) == s.charAt(start)) {
                i++;
            }
            if (s.charAt(start) == '0') {
                int cur = i - start;
                if (prev != Integer.MIN_VALUE && prev + cur > best) {
                    best = prev + cur;
                }
                prev = cur;
            }
        }
        return best;
    }


    private static class SegmentTree {

        private int n;
        private int[] arr;
        private int[] seg;

        private void build(int p, int l, int r) {
            if (l == r) {
                seg[p] = arr[l];
                return;
            }

            int mid = (l + r) >> 1;
            build(p << 1, l, mid);
            build((p << 1) | 1, mid + 1, r);
            seg[p] = Math.max(seg[p << 1], seg[(p << 1) | 1]);
        }

        private int _query(int p, int l, int r, int L, int R) {
            if (L <= l && r <= R) {
                return seg[p];
            }

            int mid = (l + r) >> 1;
            int res = 0;
            if (L <= mid) {
                res = Math.max(res, _query(p << 1, l, mid, L, R));
            }
            if (R > mid) {
                res = Math.max(res, _query((p << 1) | 1, mid + 1, r, L, R));
            }

            return res;
        }

        public SegmentTree(int[] arr) {
            this.arr = arr;
            this.n = arr.length;
            this.seg = new int[n << 2];
            build(1, 0, n - 1);
        }

        public int query(int L, int R) {
            if (L > R) {
                return 0;
            }

            return _query(1, 0, n - 1, L, R);
        }
    }


    private static class SparseTable {

        private List<List<Integer>> st;

        public SparseTable(List<Integer> data) {
            st = new ArrayList<>();
            st.add(new ArrayList<>(data));
            int i = 1,
                    N = st.get(0).size();
            while (2 * i <= N + 1) {
                List<Integer> pre = st.get(st.size() - 1);
                List<Integer> cur = new ArrayList<>();
                for (int j = 0; j < N - 2 * i + 1; j++) {
                    cur.add(Math.max(pre.get(j), pre.get(j + i)));
                }
                st.add(cur);
                i <<= 1;
            }
        }

        public int query(int begin, int end) {
            if (begin > end) {
                return 0;
            }
            int len = end - begin + 1;
            int lg = 31 - Integer.numberOfLeadingZeros(len);
            return Math.max(
                    st.get(lg).get(begin),
                    st.get(lg).get(end - (1 << lg) + 1)
            );
        }
    }


}
