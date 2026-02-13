package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestBalancedSubstrII {
    public static void main(String[] args) {
        String s = "abbac";
        System.out.println(longestBalancedI(s));
    }


    // most optimal
    static int longestBalancedI(String s) {
        char[] c = s.toCharArray();
        int n = c.length;

        int cur_a = 0, cur_b = 0, cur_c = 0;
        int max_a = 0, max_b = 0, max_c = 0;

        for (int i = 0; i < n; i++) {
            if (c[i] == 'a') {
                cur_a = (i > 0 && c[i - 1] == 'a') ? cur_a + 1 : 1;
                max_a = Math.max(max_a, cur_a);
            } else if (c[i] == 'b') {
                cur_b = (i > 0 && c[i - 1] == 'b') ? cur_b + 1 : 1;
                max_b = Math.max(max_b, cur_b);
            } else {
                cur_c = (i > 0 && c[i - 1] == 'c') ? cur_c + 1 : 1;
                max_c = Math.max(max_c, cur_c);
            }
        }

        int res = Math.max(Math.max(max_a, max_b), max_c);

        res = Math.max(res, find2(c, 'a', 'b'));
        res = Math.max(res, find2(c, 'a', 'c'));
        res = Math.max(res, find2(c, 'b', 'c'));

        res = Math.max(res, find3(c));

        return res;
    }

    private static int find2(char[] c, char x, char y) {
        int n = c.length, max_len = 0;
        int[] first = new int[2 * n + 1];
        Arrays.fill(first, -2);

        int clear_idx = -1, diff = n;
        first[diff] = -1;

        for (int i = 0; i < n; i++) {
            if (c[i] != x && c[i] != y) {
                clear_idx = i;
                diff = n;
                first[diff] = clear_idx;
            } else {
                if (c[i] == x) diff++;
                else diff--;

                if (first[diff] < clear_idx) {
                    first[diff] = i;
                } else {
                    max_len = Math.max(max_len, i - first[diff]);
                }
            }
        }

        return max_len;
    }

    private static int find3(char[] c) {
        long state = Long.MAX_VALUE / 2;
        Map<Long, Integer> first = new HashMap<>();
        first.put(state, -1);

        int max_len = 0;

        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'a') state += 1_000_001;
            else if (c[i] == 'b') state -= 1_000_000;
            else state--;

            if (first.containsKey(state)) {
                max_len = Math.max(max_len, i - first.get(state));
            } else {
                first.put(state, i);
            }
        }

        return max_len;
    }


    // TLE
    static int longestBalanced(String s) {
        int n = s.length();
        int[] maxi = new int[]{1};   // mimic reference passing

        // Case 1: All same characters
        int len = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                len++;
            } else {
                maxi[0] = Math.max(maxi[0], len);
                len = 1;
            }
        }
        maxi[0] = Math.max(maxi[0], len);

        // Case 2: Two distinct characters
        solve(s, 'a', 'b', maxi);
        solve(s, 'b', 'c', maxi);
        solve(s, 'a', 'c', maxi);

        // Case 3: All 3 have same frequency
        HashMap<String, Integer> mp = new HashMap<>();
        mp.put("0#0", -1);

        int[] count = new int[3];

        for (int i = 0; i < n; i++) {

            count[s.charAt(i) - 'a']++;

            int countAB = count[0] - count[1];
            int countAC = count[0] - count[2];

            String key = countAB + "#" + countAC;

            if (mp.containsKey(key)) {
                maxi[0] = Math.max(maxi[0], i - mp.get(key));
            } else {
                mp.put(key, i);
            }
        }

        return maxi[0];
    }

    private static void solve(String s, char x, char y, int[] maxi) {
        int n = s.length();
        int sum = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == x)
                sum += 1;
            else if (ch == y)
                sum -= 1;
            else {
                mp.clear();
                mp.put(0, i);
                sum = 0;
                continue;
            }
            if (mp.containsKey(sum)) {
                maxi[0] = Math.max(maxi[0], i - mp.get(sum));
            } else {
                mp.put(sum, i);
            }
        }
    }
}
