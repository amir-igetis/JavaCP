package codeforcesContest;

import java.util.Arrays;
import java.util.Scanner;

public class C {


    private static int sortFunc(char[] newA, char[] newB) {
        return Math.abs(Arrays.compare(newA, newB));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t--> 0) {
            int n = sc.nextInt();
            int q = sc.nextInt();
            String a = sc.nextLine();
            String b = sc.nextLine();
            int[][] lr = new int[q][2];
            for (int i = 0; i < q; i++) {
                lr[i][0] = sc.nextInt();
                lr[i][1] = sc.nextInt();
            }
            char[] astr = a.toCharArray();
            char[] bstr = a.toCharArray();
//            char[] newA = new new char[];
//            ArrayList<Character> newB = new ArrayList<>();
            int ans = 0;
            for (int i = 0; i < lr.length; i++) {
                for (int j = 0; j < 2; j++) {
                    Arrays.sort(astr, lr[i][0], lr[i][1]);
                    Arrays.sort(bstr, lr[i][0], lr[i][1]);
                    char[] newA = Arrays.copyOfRange(astr, lr[i][0], lr[i][1]);
                    char[] newB = Arrays.copyOfRange(bstr, lr[i][0], lr[i][1]);
                    sortFunc(newA, newB);
                }
            }
        }
    }
}
