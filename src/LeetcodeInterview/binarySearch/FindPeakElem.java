package LeetcodeInterview.binarySearch;

public class FindPeakElem {
    public static void main(String[] args) {
//
        int[] nums = {1, 2, 3, 1};
        System.out.println(findPeakElement(nums));
    }

    static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
