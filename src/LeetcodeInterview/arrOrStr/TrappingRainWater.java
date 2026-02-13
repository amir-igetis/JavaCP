package LeetcodeInterview.arrOrStr;

public class TrappingRainWater {
    public static void main(String[] args) {
//
        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(height));
    }

    static int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int res = 0;
        int maxLeft = 0, maxRight = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxLeft)
                    maxLeft = height[left];
                else
                    res += maxLeft - height[left];

                left++;
            } else {
                if (height[right] >= maxRight)
                    maxRight = height[right];
                else
                    res += maxRight - height[right];

                right--;
            }
        }
        return res;
    }

    static int trapI(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        maxLeft[0] = height[0];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }

        maxRight[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }

        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            trappedWater += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        return trappedWater;
    }
}
