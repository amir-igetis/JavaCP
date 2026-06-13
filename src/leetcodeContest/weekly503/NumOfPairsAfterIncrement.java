package leetcodeContest.weekly503;

import java.util.Arrays;

public class NumOfPairsAfterIncrement {
    public static void main(String[] args) {
        int[] nums1 = {1, 2}, nums2 = {3, 4};
        int[][] queries = {{2, 5}, {1, 0, 0, 2}, {2, 5}};
        System.out.println(Arrays.toString(numberOfPairs(nums1, nums2, queries)));
    }

    static int[] numberOfPairs(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums2.length;
        int B = 400;
        int numBlk = (n + B - 1) / B;

        long[] arr = new long[n];
        long[] sortArr = new long[n];
        long[] lazy = new long[numBlk];

        for (int i = 0; i < n; i++) {
            arr[i] = nums2[i];
            sortArr[i] = nums2[i];
        }
        for (int b = 0; b < numBlk; b++) {
            int start = b * B;
            int end = Math.min(start + B, n);
            Arrays.sort(sortArr, start, end);
        }
        Arrays.sort(nums1);

        int q2Cnt = 0;
        for (int[] q : queries) {
            if (q[0] == 2) q2Cnt++;
        }

        int[] answer = new int[q2Cnt];
        int ansIdx = 0;

        for (int[] q : queries) {
            if (q[0] == 1) {
                int L = q[1];
                int R = q[2];
                long val = q[3];

                int bL = L / B;
                int bR = R / B;

                if (bL == bR) {
                    for (int i = L; i <= R; i++) {
                        arr[i] += val;
                    }
                    updateBlock(bL, B, n, arr, sortArr);
                } else {
                    for (int i = L; i < (bL + 1) * B; i++) {
                        arr[i] += val;
                    }
                    updateBlock(bL, B, n, arr, sortArr);
                    for (int b = bL + 1; b < bR; b++) {
                        lazy[b] += val;
                    }

                    for (int i = bR * B; i <= R; i++) {
                        arr[i] += val;
                    }
                    updateBlock(bR, B, n, arr, sortArr);
                }
            } else {
                long tot = q[1];
                int totalPairs = 0;

                for (int i = 0; i < nums1.length; i++) {
                    if (i > 0 && nums1[i] == nums1[i - 1]) continue;

                    int freq1 = 1;
                    while (i + 1 < nums1.length && nums1[i + 1] == nums1[i]) {
                        freq1++;
                        i++;
                    }

                    long target = tot - nums1[i];
                    int countTarget = 0;

                    for (int b = 0; b < numBlk; b++) {
                        long valToFind = target - lazy[b];
                        int start = b * B;
                        int end = Math.min(start + B, n);

                        int lower = lowerBound(sortArr, start, end, valToFind);
                        if (lower < end && sortArr[lower] == valToFind) {
                            int upper = upperBound(sortArr, start, end, valToFind);
                            countTarget += (upper - lower);
                        }
                    }
                    totalPairs += countTarget * freq1;
                }
                answer[ansIdx++] = totalPairs;
            }
        }

        return answer;
    }

    private static void updateBlock(int b, int B, int n, long[] arr, long[] sortArr) {
        int start = b * B;
        int end = Math.min(start + B, n);
        System.arraycopy(arr, start, sortArr, start, end - start);
        Arrays.sort(sortArr, start, end);
    }

    private static int lowerBound(long[] a, int start, int end, long val) {
        int low = start;
        int high = end - 1;
        int ans = end;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (a[mid] >= val) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private static int upperBound(long[] a, int start, int end, long val) {
        int low = start;
        int high = end - 1;
        int ans = end;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (a[mid] > val) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

}
