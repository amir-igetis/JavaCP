package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumberOfUniqueXORTripletsII {
    public static void main(String[] args) {
        int[] nums = {6, 7, 8, 9};
        System.out.println(uniqueXorTriplets(nums));
    }

    // enumeration tc O(n^2 + nm) sc O(m)
    static int uniqueXorTripletsI(int[] nums) {
        int n = nums.length;
        int m = 0;
        for (int v : nums) {
            m = Math.max(m, v);
        }
        int u = 1;
        while (u <= m) {
            u <<= 1;
        }
        boolean[] s = new boolean[u];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                s[nums[i] ^ nums[j]] = true;
            }
        }
        boolean[] t = new boolean[u];
        for (int x = 0; x < u; x++) {
            if (!s[x]) {
                continue;
            }
            for (int v : nums) {
                t[x ^ v] = true;
            }
        }
        int ans = 0;
        for (boolean b : t) {
            if (b) {
                ans++;
            }
        }
        return ans;
    }


    // enumeration (optimization) tc O(nm) sc O(m)
    static int uniqueXorTriplets(int[] nums) {
        int m = 0;
        for (int v : nums) {
            m = Math.max(m, v);
        }
        int u = 1;
        while (u <= m) {
            u <<= 1;
        }
        boolean[] one = new boolean[u];
        boolean[] two = new boolean[u];
        boolean[] three = new boolean[u];
        for (int v : nums) {
            one[v] = true;
            for (int x = 0; x < u; x++) {
                if (one[x]) {
                    two[x ^ v] = true;
                }
            }
        }
        for (int v : nums) {
            for (int x = 0; x < u; x++) {
                if (two[x]) {
                    three[x ^ v] = true;
                }
            }
        }
        int ans = 0;
        for (boolean b : three) {
            if (b) {
                ans++;
            }
        }
        return ans;
    }
}
