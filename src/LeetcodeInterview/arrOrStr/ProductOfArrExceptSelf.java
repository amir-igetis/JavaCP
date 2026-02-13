package LeetcodeInterview.arrOrStr;

import java.util.Arrays;

public class ProductOfArrExceptSelf {
    public static void main(String[] args) {
//
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        ans[0] = 1;
        for (int i = 1; i < n; i++)
            ans[i] = ans[i - 1] * nums[i - 1];

        int suffixProd = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= suffixProd;
            suffixProd *= nums[i];
        }

        return ans;
    }
}
