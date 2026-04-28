package leetcodeDiscussProbPatterns.dailyQuestions;

public class TwoFurthestHousesWithDiffColors {
    public static void main(String[] args) {
        int[] colors = {1, 1, 1, 6, 1, 1, 1};
        System.out.println(maxDistanceI(colors));
    }

    //    enumeration
    static int maxDistance(int[] colors) {
        int n = colors.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (colors[i] != colors[j])
                    res = Math.max(res, j - i);
            }
        }
        return res;
    }

    // o(n)
    static int maxDistanceI(int[] colors) {
        int n = colors.length;
        int maxDist = 0;

        // Compare with first house
        for (int j = n - 1; j >= 0; j--) {
            if (colors[j] != colors[0]) {
                maxDist = j; // distance = j - 0
                break;
            }
        }

        // Compare with last house
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, (n - 1) - i);
                break;
            }
        }

        return maxDist;
    }

}
