package LeetcodeInterview.multidimensionalDP;

import java.util.Arrays;
import java.util.List;

public class Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = List.of(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        );

        System.out.println(minimumTotal(triangle));
    }

    static int minimumTotal(List<List<Integer>> triangle) {
        int l = triangle.size();
        int[] total = new int[l];

        for (int i = 0; i < triangle.get(l - 1).size(); i++) {
            total[i] = triangle.get(l - 1).get(i);
        }

        for (int i = l - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                total[j] = triangle.get(i).get(j) + Math.min(total[j], total[j + 1]);
            }
        }

        return total[0];
    }
}
