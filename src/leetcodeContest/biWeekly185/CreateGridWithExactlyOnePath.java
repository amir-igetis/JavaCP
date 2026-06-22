package leetcodeContest.biWeekly185;

import java.util.Arrays;

public class CreateGridWithExactlyOnePath {
    public static void main(String[] args) {
        int m = 2, n = 3;
//        System.out.println(Arrays.toString(createGrid(m, n)));
        String[] ans = createGrid(m, n);
        for (String s : ans)
            System.out.print(s + " ");
        System.out.println();
    }

    static String[] createGrid(int m, int n) {

        String[] arr = new String[n];
        for (int i = 0; i < m; i++) {
            StringBuilder r = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == n - 1)
                    r.append('.');
                else r.append('#');
            }
            arr[i] = r.toString();

        }
        return arr;
    }
}
