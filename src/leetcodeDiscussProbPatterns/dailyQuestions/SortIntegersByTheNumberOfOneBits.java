package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.Comparator;

public class SortIntegersByTheNumberOfOneBits {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(sortByBits(arr)));
    }

    static int[] sortByBits(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] += Integer.bitCount(arr[i]) * 10001;

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++)
            arr[i] = arr[i] % 10001;

        return arr;
    }

    // using built in function
    static int[] sortByBitsI(int[] arr) {
        Integer[] intArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = new BitCountComparator();
        Arrays.sort(intArr, comparator);
        int[] sortedArr = Arrays.stream(intArr).mapToInt(Integer::intValue).toArray();

        return sortedArr;
    }

    private static class BitCountComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            int bitCountA = Integer.bitCount(a);
            int bitCountB = Integer.bitCount(b);
            if (bitCountA == bitCountB)
                return a - b;
            else return bitCountA - bitCountB;
        }
    }

    // Brian Kerninghan's Algo
    static int[] sortByBitsII(int[] arr) {
        // Convert the input array to an array of Integer objects for sorting
        Integer[] nums = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        // Create a custom comparator for sorting based on bit counts and numerical values
        Comparator<Integer> comparator = new BitCountComparatorI();

        // Sort the array using the custom comparator
        Arrays.sort(nums, comparator);

        // Convert the sorted Integer array back to a primitive int array
        return Arrays.stream(nums).mapToInt(Integer::intValue).toArray();
    }

    private static class BitCountComparatorI implements Comparator<Integer> {
        private int findBitCount(int num) {
            // Count the number of set bits (1s) in the binary representation of num
            int count = 0;

            while (num > 0) {
                count++;
                num &= (num - 1);
            }

            return count;
        }

        @Override
        public int compare(Integer a, Integer b) {
            int bitCountA = findBitCount(a);
            int bitCountB = findBitCount(b);

            if (bitCountA == bitCountB) {
                return a - b; // If bit counts are the same, compare numerically.
            }

            return bitCountA - bitCountB; // Sort by the bit count in ascending order.
        }
    }
}
