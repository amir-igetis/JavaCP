package leetcodeContest;

import java.util.ArrayList;
import java.util.List;

public class CountTheNumOfSubstrWithDominantOnes {
    public static void main(String[] args) {
        String s = "101101";
        System.out.println(numberOfSubstringsI(s));
    }

    // soln for uwi
    static int numberOfSubstringsII(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s[i] == '0') zeros.add(i);
        }
        int m = zeros.size();
        int all = 0;
        if (m == 0) return n * (n + 1) / 2;
        for (int i = 0; i < m - 1; i++) {
            int l = zeros.get(i);
            int r = zeros.get(i + 1);
            all += (r - l) * (r - l - 1) / 2;
        }
        all += zeros.get(0) * (zeros.get(0) + 1) / 2;
        all += (n - zeros.get(m - 1)) * (n - zeros.get(m - 1) - 1) / 2;
//        tr(all)
        for (int len = 1; len <= 200; len++) {
            for (int i = 0; i + len - 1 < m; i++) {
                int v = zeros.get(i);
                int l = i - 1 >= 0 ? v - zeros.get(i - 1) - 1 : v;
                int r = i + len < m ? zeros.get(i + len) - zeros.get(i + len - 1) - 1 : n - zeros.get(i = len - 1) - 1;
                int b = len * len - (zeros.get(i + len - 1) - zeros.get(i) + 1 - len);
                if (l > r) {
                    int d = l;
                    l = r;
                    r = d;
                }
                int lall = go(l, r, b);
//                tr(l,r,len,b,lall);
                all += lall;
            }
        }
        return all;
    }

    private static int go(int l, int r, int b) {
        if (b > l + r) {
            return 0;
        } else if (b < 0)
            return (l + 1) * (r + 1);
        else if (b <= 1)
            return (l + 1) * (r + 1) - b * (b + 1) / 2;
        else if (b <= r)
            return (l + 1) * (r + 1) - l * (l + 1) / 2 - (b - l) * (l + 1);
        else return (l + r - b + 1) * (l + r - b + 2) / 2;
    }

    static int numberOfSubstringsI(String s) {
        int n = s.length();
        int[] preOne = new int[n + 1];
        int[] preZero = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preOne[i + 1] = preOne[i] + (s.charAt(i) == '1' ? 1 : 0);
            preZero[i + 1] = preZero[i] + (s.charAt(i) == '0' ? 1 : 0);
        }
        int count = 0;
        for (int st = 0; st < n; st++) {
            for (int e = st; e < n; e++) {
                int one = preOne[e + 1] - preOne[st];
                int zeros = preZero[e + 1] - preZero[st];
                if (one >= zeros * zeros) count++;
            }
        }
        return count;
    }

    static int numberOfSubstrings(String s) {
        int n = s.length();
        int count = 0;
        for (int start = 0; start < n; start++) {
            int ones = 0;
            int zeros = 0;
            for (int end = start; end < n; end++) {
                if (s.charAt(end) == '1') {
                    ones++;
                } else {
                    zeros++;
                }
                if (ones >= zeros * zeros) {
                    count++;
                }
            }
        }
        return count;
    }
}
