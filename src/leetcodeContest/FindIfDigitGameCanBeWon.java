package leetcodeContest;

public class FindIfDigitGameCanBeWon {

    // solution from uwi
    static boolean canAliceWinI(int[] nums) {
        int s = 0;
        for (int v : nums) s += v;
        int h = 0;
        for (int v : nums) {
            if (v <= 9) h += v;
        }
        return h * 2 != s;
    }

    static boolean canAliceWin(int[] nums) {
        int singleDigSum = 0, doubleDigSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 1 && nums[i] <= 9)
                singleDigSum += nums[i];
            else if (nums[i] >= 10 && nums[i] <= 99)
                doubleDigSum += nums[i];
        }
        if (singleDigSum == doubleDigSum) return false;
        else return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 10};
        System.out.println(canAliceWin(nums));
    }
}
