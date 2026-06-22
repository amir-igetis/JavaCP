package leetcodeContest.weekly506;

import java.util.Arrays;

public class MaxSubarrSumAfterAtMostKSwaps {
    public static void main(String[] args) {
        int[] nums = {1, -1, 0, 2};
        int k = 1;
        int[] numsI = {4, 3, 2, 4};
        int k2 = 2;
        int[] numsII = {-1, -2};
        int k3 = 0;
        System.out.println(maxSum(nums, k));
        System.out.println(maxSum(numsI, k2));
        System.out.println(maxSum(numsII, k3));

    }


    static int[] bitACount, bitBCount;
    static long[] bitASum, bitBSum;
    static int maxUniqueValues, maxPower;
    static int[] vals;
    static long queryResultValue;
    static long queryResultSum;

    private static void updateA(int index, int value, int delta) {
        for (int i = index; i <= maxUniqueValues; i += i & -i) {
            bitACount[i] += delta;
            bitASum[i] += (long) value * delta;
        }
    }

    private static void updateB(int index, int value, int delta) {
        for (int i = index; i <= maxUniqueValues; i += i & -i) {
            bitBCount[i] += delta;
            bitBSum[i] += (long) value * delta;
        }
    }

    private static void calculateKthAndSum(int[] countBit, long[] sumBit, int k) {
        if (k == 0) {
            queryResultValue = 0;
            queryResultSum = 0;
            return;
        }

        int index = 0;
        int currentCount = 0;
        long currentSum = 0;

        for (int i = maxPower; i >= 0; i--) {
            int nextIndex = index + (1 << i);
            if (nextIndex <= maxUniqueValues && currentCount + countBit[nextIndex] < k) {
                index = nextIndex;
                currentCount += countBit[index];
                currentSum += sumBit[index];
            }
        }

        queryResultValue = vals[index];
        queryResultSum = currentSum + (long) (k - currentCount) * queryResultValue;
    }

    private static boolean canSwap(int j, int lenB) {
        calculateKthAndSum(bitACount, bitASum, j);
        long valA = queryResultValue;

        calculateKthAndSum(bitBCount, bitBSum, lenB - j + 1);
        long valB = queryResultValue;

        return valB > valA;
    }

    public static long maxSum(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] temp = nums.clone();
        Arrays.sort(temp);
        maxUniqueValues = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || temp[i] != temp[i - 1]) {
                temp[maxUniqueValues++] = temp[i];
            }
        }
        vals = Arrays.copyOf(temp, maxUniqueValues);

        maxPower = 0;
        while ((1 << maxPower) <= maxUniqueValues) {
            maxPower++;
        }
        maxPower--;

        int[] mapped = new int[n];
        for (int i = 0; i < n; i++) {
            mapped[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }

        bitACount = new int[maxUniqueValues + 1];
        bitASum = new long[maxUniqueValues + 1];
        bitBCount = new int[maxUniqueValues + 1];
        bitBSum = new long[maxUniqueValues + 1];

        long totalSumBInitial = 0;
        for (int i = 0; i < n; i++) {
            updateB(mapped[i], nums[i], 1);
            totalSumBInitial += nums[i];
        }

        long maxAns = Long.MIN_VALUE;

        for (int left = 0; left < n; left++) {
            long currentSum = 0;
            long totalSumB = totalSumBInitial;
            int bestJ = 0;

            for (int right = left; right < n; right++) {
                int val = nums[right];
                int mappedIndex = mapped[right];

                currentSum += val;
                updateA(mappedIndex, val, 1);
                updateB(mappedIndex, val, -1);
                totalSumB -= val;

                int lenA = right - left + 1;
                int lenB = n - lenA;

                int maxJ = Math.min(k, Math.min(lenA, lenB));
                if (bestJ > maxJ) bestJ = maxJ;

                while (bestJ < maxJ && canSwap(bestJ + 1, lenB)) {
                    bestJ++;
                }
                while (bestJ > 0 && !canSwap(bestJ, lenB)) {
                    bestJ--;
                }

                long gain = 0;
                if (bestJ > 0) {
                    calculateKthAndSum(bitACount, bitASum, bestJ);
                    long sumAJ = queryResultSum;
                    calculateKthAndSum(bitBCount, bitBSum, lenB - bestJ);
                    long sumBSmallest = queryResultSum;
                    long sumBLargest = totalSumB - sumBSmallest;

                    gain = sumBLargest - sumAJ;
                }

                if (currentSum + gain > maxAns) {
                    maxAns = currentSum + gain;
                }
            }
            for (int right = left; right < n; right++) {
                int val = nums[right];
                int mappedIndex = mapped[right];
                updateA(mappedIndex, val, -1);
                updateB(mappedIndex, val, 1);
            }
        }

        return maxAns;
    }


}


