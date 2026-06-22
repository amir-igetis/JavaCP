package leetcodeContest.biWeekly185;

import java.util.Arrays;

public class MinLightsToIlluminateARoad {
    public static void main(String[] args) {
        int[] lights = {0, 0, 0, 2, 0};

        System.out.println(minLights(lights));
    }

    static int minLights(int[] lights) {

        int n = lights.length;
        int[] maxReacgh = new int[n];
        Arrays.fill(maxReacgh, -1);
        for (int i = 0; i < n; i++) {
            if (lights[i] > 0) {
                int l = Math.max(0, i - lights[i]);
                int r = Math.min(n - 1, i + lights[i]);
                maxReacgh[l] = Math.max(maxReacgh[l], r);
            }
        }
        int addBul = 0, currEnd = -1, far = -1;
        for (int i = 0; i < n; i++) {
            far = Math.max(far, maxReacgh[i]);
            if (i > currEnd) {
                if (far < i) {
                    addBul++;
                    far = Math.max(far, i + 2);
                }
                currEnd = far;
            }
        }
        return addBul;
    }
}
