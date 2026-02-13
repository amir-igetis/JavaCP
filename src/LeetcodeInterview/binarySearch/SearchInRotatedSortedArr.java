package LeetcodeInterview.binarySearch;

public class SearchInRotatedSortedArr {
    public static void main(String[] args) {
//
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;

        System.out.println(search(nums, target));
    }

    static int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
