package binarySearch;

public class BinarySearch {

    static int bs(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target)
                return mid + 1;
            else if (target < arr[mid]) {
                end = mid - 1;
            } else start = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 7, 8, 12, 22, 45, 60};
        int target = 45;
        System.out.println(bs(arr, target));
    }
}
