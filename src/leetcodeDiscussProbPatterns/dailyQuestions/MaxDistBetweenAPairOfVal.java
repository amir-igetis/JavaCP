package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxDistBetweenAPairOfVal {
    public static void main(String[] args) {
        int[] nums1 = {55, 30, 5, 4, 2}, nums2 = {100, 20, 10, 10, 5};
        System.out.println(maxDistance(nums1, nums2));
    }

    static int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int maxDist = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else {
                i++;
            }
        }
        return maxDist;
    }
}
