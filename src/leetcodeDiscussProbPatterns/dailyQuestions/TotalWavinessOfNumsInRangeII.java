package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TotalWavinessOfNumsInRangeII {
    public static void main(String[] args) {
        int num1 = 120, num2 = 130;
        System.out.println(totalWavinessI(num1, num2));
    }


    //    Digit Dynamic Programming tc O(D^3lognum_2) and sc O(D^2lognum_2)
    private static String s;
    private static int n;
    private static long[][][] memo_cnt;
    private static long[][][] memo_sum;

    static long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    // calculate the sum of fluctuation values of all numbers in the range [0, num]
    private static long solve(long num) {
        // if the fluctuation value of numbers less than 3 is 0
        if (num < 100) {
            return 0L;
        }
        s = Long.toString(num);
        n = s.length();

        // memoized search uses two independent arrays
        // memo_cnt[pos][x][y]: the number of valid filling schemes where the current digit is at position pos, and the previous two digits are x and y
        memo_cnt = new long[16][10][10];
        // memo_sum[pos][x][y]: the fluctuation value when the current position is pos, and the two left digits are x and y
        memo_sum = new long[16][10][10];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(memo_cnt[i][j], -1);
                Arrays.fill(memo_sum[i][j], -1);
            }
        }

        long[] result = dfs(0, -1, -1, true, true);
        return result[1];
    }

    private static long[] dfs(
            int pos,
            int prev,
            int curr,
            boolean isLimit,
            boolean isLeading
    ) {
        // end position
        if (pos == n) {
            return new long[]{1L, 0L};
        }
        // use memoization only when not bounded by an upper limit and without leading zeros
        if (!isLimit && !isLeading && prev >= 0 && curr >= 0) {
            if (memo_cnt[pos][prev][curr] != -1) {
                return new long[]{
                        memo_cnt[pos][prev][curr],
                        memo_sum[pos][prev][curr],
                };
            }
        }

        // calculate the number of schemes and fluctuation value under the current conditions
        long cnt = 0;
        long sum = 0;
        int up = isLimit ? (s.charAt(pos) - '0') : 9;
        for (int digit = 0; digit <= up; ++digit) {
            boolean newLeading = isLeading && (digit == 0);
            // the previous number is updated to curr
            int newPrev = curr;
            // the current number is updated to digit
            int newCurr = newLeading ? -1 : digit;
            long[] sub = dfs(
                    pos + 1,
                    newPrev,
                    newCurr,
                    isLimit && (digit == up),
                    newLeading
            );
            long subCnt = sub[0];
            long subSum = sub[1];
            // only calculate the fluctuation value when there are no leading zeros
            if (!newLeading && prev >= 0 && curr >= 0) {
                // when the digit is a peak or a valley, update the current fluctuation value
                if (
                        (prev < curr && curr > digit) ||
                                (prev > curr && curr < digit)
                ) {
                    sum += subCnt;
                }
            }

            cnt += subCnt;
            sum += subSum;
        }

        if (!isLimit && !isLeading && prev >= 0 && curr >= 0) {
            // update the memoization array
            memo_cnt[pos][prev][curr] = cnt;
            memo_sum[pos][prev][curr] = sum;
        }

        return new long[]{cnt, sum};
    }

//    Bottom-Up Dynamic Programming tc O(D^3lognum_2) and sc O(D^2)

    private static class State {

        int prev, curr, tight, lead;
        long cnt, sum;

        State(int prev, int curr, int tight, int lead, long cnt, long sum) {
            this.prev = prev;
            this.curr = curr;
            this.tight = tight;
            this.lead = lead;
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    private static long solveI(long num) {
        // if the number has fewer than 3 digits, the fluctuation value is 0
        if (num < 100) {
            return 0;
        }
        String str = Long.toString(num);
        int n = str.length();

        List<State> currStates = new ArrayList<>();
        // digit 10 represents the invalid state when there is a leading zero
        currStates.add(new State(10, 10, 1, 1, 1, 0));

        for (int pos = 0; pos < n; ++pos) {
            int limit = str.charAt(pos) - '0';
            long[][][][] cnt = new long[2][2][11][11];
            long[][][][] sum = new long[2][2][11][11];

            for (State st : currStates) {
                int maxDigit = st.tight == 1 ? limit : 9;
                for (int digit = 0; digit <= maxDigit; ++digit) {
                    int newLead = (st.lead == 1 && digit == 0) ? 1 : 0;
                    int newPrev = st.curr;
                    int newCurr = newLead == 1 ? 10 : digit;
                    int newTight = (st.tight == 1 && digit == maxDigit) ? 1 : 0;

                    long add = 0;
                    // calculate fluctuation only when there are three significant digits (both prev and curr are valid and not leading zeros)
                    if (newLead == 0 && st.prev != 10 && st.curr != 10) {
                        if (
                                (st.prev < st.curr && st.curr > digit) ||
                                        (st.prev > st.curr && st.curr < digit)
                        ) {
                            add = st.cnt;
                        }
                    }

                    cnt[newTight][newLead][newPrev][newCurr] += st.cnt;
                    sum[newTight][newLead][newPrev][newCurr] += st.sum + add;
                }
            }

            // collect legal states
            List<State> nextStates = new ArrayList<>();
            for (int tight = 0; tight < 2; ++tight) {
                for (int lead = 0; lead < 2; ++lead) {
                    for (int prev = 0; prev <= 10; ++prev) {
                        for (int curr = 0; curr <= 10; ++curr) {
                            long c = cnt[tight][lead][prev][curr];
                            long s = sum[tight][lead][prev][curr];
                            // if the current state is valid, proceed to the next round of calculation
                            if (c != 0) {
                                nextStates.add(
                                        new State(prev, curr, tight, lead, c, s)
                                );
                            }
                        }
                    }
                }
            }

            currStates = nextStates;
        }

        // sum of fluctuation values of all valid states
        long ans = 0;
        for (State st : currStates) {
            ans += st.sum;
        }
        return ans;
    }

    static long totalWavinessI(long num1, long num2) {
        return solveI(num2) - solveI(num1 - 1);
    }


}
