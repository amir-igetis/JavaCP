package LeetcodeInterview.twoPointers;

public class ContainerWithMostWater {
    public static void main(String[] args) {
//
        int[] height = {1, 7, 4, 5, 8, 6, 3, 8, 2};
//        System.out.println(maxArea(height));
        System.out.println(maxArea(height));
    }

    static int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int area = 0;
        while (end > start) {
            int startHeight = height[start];
            int endHeight = height[end];
            int minHeight = Math.min(startHeight, endHeight);
            int length = end - start;
            int newArea = minHeight * length;
            area = Math.max(newArea, area);
            if (endHeight >= startHeight) {
                start++;
            } else {
                end--;
            }
//            System.out.println("startHeight" + startHeight + "index" + height[start]);
//            System.out.println("endHeitght" + endHeight + "index" + height[end]);
        }
        return area;
//        System.out.println(area);
    }
}
