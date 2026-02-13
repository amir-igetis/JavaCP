package leetcodeDiscussProbPatterns.dailyQuestions;

public class CheckIfAllOnesAreAtLeastLenKPlacesAway {
    public static void main(String[] args) {
        int[] nums = {1, 0, 0, 0, 1, 0, 0, 1};
        int k = 2;
        System.out.println(kLengthApartI(nums, k));
    }

    static boolean kLengthApart(int[] nums, int k) {
        int prev = -1000000;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i - prev - 1 < k)
                    return false;

                prev = i;
            }
        }
        return true;
    }

    static boolean kLengthApartI(int[] nums, int k) {
        int prev = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (prev != -1 && i - prev - 1 < k)
                    return false;


                prev = i;
            }
        }
        return true;
    }
}
