package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class CountZeroRequestServers {
    public static void main(String[] args) {
        int n = 3, x = 5;
        int[][] logs = {{1, 3}, {2, 6}, {1, 5}};
        int[] queries = {10, 11};
        System.out.println(Arrays.toString(countServers(n, logs, x, queries)));
    }

    //  Sorting and Sliding Window

    /// Let n be the number of servers, m be the number of logs, and k be the number of queries.
    ///
    /// Time complexity: O(mlogm+klogk).
    ///
    /// Sorting the m logs takes O(mlogm) time, and sorting the k query indices takes O(klogk) time. The two pointers left and right each traverse the logs array at most once across all queries (O(m) total steps), and iterating through the k queries takes O(k) steps. Therefore, the sliding window phase takes O(m+k) time, leading to an overall time complexity of O(mlogm+klogk).
    ///
    /// Space complexity: O(n+k+logm+logk).
    ///
    /// The frequency array cnt uses O(n) space, and the index array ids uses O(k) space. Sorting requires O(logm+logk) auxiliary stack space in C++, or up to O(m+k) auxiliary space in languages that use Timsort or auxiliary entry arrays (ignoring the space required for the output array)
    static int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        Integer[] ids = new Integer[m];
        for (int i = 0; i < m; i++) ids[i] = i;
        Arrays.sort(ids, (i, j) -> Integer.compare(queries[i], queries[j]));

        int[] ans = new int[m];
        int[] cnt = new int[n + 1];
        int left = 0,
                right = 0,
                active = 0;

        for (int i : ids) {
            int q = queries[i];
            while (right < logs.length && logs[right][1] <= q) {
                int id = logs[right++][0];
                if (cnt[id]++ == 0) {
                    ++active;
                }
            }
            while (left < right && logs[left][1] < q - x) {
                int id = logs[left++][0];
                if (--cnt[id] == 0) {
                    --active;
                }
            }
            ans[i] = n - active;
        }
        return ans;
    }
}
