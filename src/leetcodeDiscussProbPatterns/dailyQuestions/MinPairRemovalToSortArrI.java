package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinPairRemovalToSortArrI {
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};
        System.out.println(minimumPairRemovalI(nums));
    }

    //    previous code
    static int minimumPairRemovalII(int[] nums) {
        int n = nums.length;
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : nums)
            list.add(num);

        while (!nonDec(list)) {
            int minSum = Integer.MAX_VALUE;
            int indexToMerge = -1;
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    indexToMerge = i;
                }
            }

            int merged = list.get(indexToMerge) + list.get(indexToMerge + 1);
            list.set(indexToMerge, merged);
            list.remove(indexToMerge + 1);
            count++;
        }

        return count;
    }

    private static boolean nonDec(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    //    simulation tc and sc O(N^2) and O(1)
    static int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        var count = 0;

        while (list.size() > 1) {
            var isAscending = true;
            var minSum = Integer.MAX_VALUE;
            var targetIndex = -1;

            for (var i = 0; i < list.size() - 1; i++) {
                var sum = list.get(i) + list.get(i + 1);

                if (list.get(i) > list.get(i + 1)) {
                    isAscending = false;
                }

                if (sum < minSum) {
                    minSum = sum;
                    targetIndex = i;
                }
            }

            if (isAscending) {
                break;
            }

            count++;
            list.set(targetIndex, minSum);
            list.remove(targetIndex + 1);
        }

        return count;
    }

    //    simulation + Array Simulation of linked list tc O(n^2) and O(1)
    static int minimumPairRemovalI(int[] nums) {
        var n = nums.length;
        var next = new int[n];
        Arrays.setAll(next, i -> i + 1);
        next[n - 1] = -1;
        var count = 0;

        while (n - count > 1) {
            var curr = 0;
            var target = 0;
            var targetAdjSum = nums[target] + nums[next[target]];
            var isAscending = true;

            while (curr != -1 && next[curr] != -1) {
                if (nums[curr] > nums[next[curr]]) {
                    isAscending = false;
                }

                var currAdjSum = nums[curr] + nums[next[curr]];
                if (currAdjSum < targetAdjSum) {
                    target = curr;
                    targetAdjSum = currAdjSum;
                }
                curr = next[curr];
            }

            if (isAscending) {
                break;
            }

            count++;
            next[target] = next[next[target]];
            nums[target] = targetAdjSum;
        }

        return count;
    }
}
