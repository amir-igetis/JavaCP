package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaximizeTheNumOfPartitionsAfterOps {
    public static void main(String[] args) {
        String s = "aabaab";
        int k = 3;
        System.out.println(maxPartitionsAfterOperationsI(s, k));
    }

    //    not working
    static int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        Integer[][] memo = new Integer[n + 1][2];
        return dfs(s, 0, 0, k, memo);
    }

    private static int dfs(String s, int i, int used, int k, Integer[][] memo) {
        if (i == s.length())
            return 0;
        if (memo[i][used] != null)
            return memo[i][used];

        int res = 0;
        int mask = 0, distinct = 0;
        for (int j = i; j < s.length(); j++) {
            int bit = 1 << (s.charAt(j) - 'a');
            if ((mask & bit) == 0)
                distinct++;

            mask |= bit;

            if (distinct > k) {
                if (used == 0) {
                    res = Math.max(res, 1 + dfs(s, j + 1, 1, k, memo));

                }
                break;
            }
            res = Math.max(res, 1 + dfs(s, j + 1, used, k, memo));
        }
        return memo[i][used] = res;
    }

    //    working code
    static int maxPartitionsAfterOperationsI(String s, int k) {
        if (k == 26) return 1;

        int n = s.length();
        int[] leftMask = new int[n];
        int[] leftDup = new int[n];
        int[] leftParts = new int[n];

        int mask = 0, dup = 0, parts = 1;
        for (int i = 0; i < n; i++) {
            int bit = 1 << (s.charAt(i) - 'a');
            dup |= mask & bit;
            mask |= bit;
            if (Integer.bitCount(mask) > k) {
                mask = bit;
                dup = 0;
                parts++;
            }
            leftMask[i] = mask;
            leftDup[i] = dup;
            leftParts[i] = parts;
        }

        int result = parts;
        mask = 0;
        dup = 0;
        parts = 0;

        for (int i = n - 1; i >= 0; i--) {
            int bit = 1 << (s.charAt(i) - 'a');
            dup |= mask & bit;
            mask |= bit;

            int bitCount = Integer.bitCount(mask);
            if (bitCount > k) {
                mask = bit;
                dup = 0;
                parts++;
            }

            if (bitCount == k) {
                if (((bit & dup) != 0) &&
                        ((bit & leftDup[i]) != 0) &&
                        (Integer.bitCount(leftMask[i]) == k) &&
                        ((leftMask[i] | mask) != 0x3FFFFFF)) {
                    result = Math.max(result, parts + leftParts[i] + 2);
                } else if (dup != 0) {
                    result = Math.max(result, parts + leftParts[i] + 1);
                }
            }
        }
        return result;
    }
}
