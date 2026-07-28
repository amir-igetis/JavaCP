package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxProductOfThreeSums {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(maximumProduct(nums));
    }

    static int maximumProduct(int[] nums) {
        int maxi = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, thirdMax = Integer.MIN_VALUE;
        int mini = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxi) {
                thirdMax = secondMax;
                secondMax = maxi;
                maxi = nums[i];
            } else if (nums[i] > secondMax) {
                thirdMax = secondMax;
                secondMax = nums[i];
            } else if (nums[i] > thirdMax) {
                thirdMax = nums[i];
            }

            if (nums[i] < mini) {
                secondMin = mini;
                mini = nums[i];
            } else if (nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }
        return Math.max(maxi * secondMax * thirdMax,
                mini * secondMin * maxi);
    }
}
