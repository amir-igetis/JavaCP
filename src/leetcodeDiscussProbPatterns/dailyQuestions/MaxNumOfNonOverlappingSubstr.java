package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNumOfNonOverlappingSubstr {
    public static void main(String[] args) {
        String s = "adefaddaccc";
        System.out.println("The substings are " + maxNumOfSubstrings(s));
        System.out.println("The substings are " + maxNumOfSubstringsI(s));

    }

    // greedy

    /// Let n be the length of the string, and let Σ be the size of its character set.
    ///
    /// Time complexity: O(nΣ+ΣlogΣ).
    ///
    /// Preprocessing the left and right endpoints of every interval takes O(nΣ) time. Greedy selection takes O(ΣlogΣ+Σ) time. Therefore, the total time complexity is O(nΣ+ΣlogΣ).
    ///
    /// Space complexity: O(Σ).
    ///
    /// We need O(Σ) space to record the left and right endpoints of the interval containing each character.
    static List<String> maxNumOfSubstrings(String s) {
        Seg[] seg = new Seg[26];
        for (int i = 0; i < 26; ++i) {
            seg[i] = new Seg(-1, -1);
        }
        // Preprocess the left and right endpoints.
        for (int i = 0; i < s.length(); ++i) {
            int charIdx = s.charAt(i) - 'a';
            if (seg[charIdx].left == -1) {
                seg[charIdx].left = seg[charIdx].right = i;
            } else {
                seg[charIdx].right = i;
            }
        }
        for (int i = 0; i < 26; ++i) {
            if (seg[i].left != -1) {
                for (int j = seg[i].left; j <= seg[i].right; ++j) {
                    int charIdx = s.charAt(j) - 'a';
                    if (
                            seg[i].left <= seg[charIdx].left &&
                                    seg[charIdx].right <= seg[i].right
                    ) {
                        continue;
                    }
                    seg[i].left = Math.min(seg[i].left, seg[charIdx].left);
                    seg[i].right = Math.max(seg[i].right, seg[charIdx].right);
                    j = seg[i].left;
                }
            }
        }
        // Greedily select intervals.
        Arrays.sort(seg);
        List<String> ans = new ArrayList<>();
        int end = -1;
        for (Seg segment : seg) {
            int left = segment.left,
                    right = segment.right;
            if (left == -1) {
                continue;
            }
            if (end == -1 || left > end) {
                end = right;
                ans.add(s.substring(left, right + 1));
            }
        }
        return ans;
    }

    private static class Seg implements Comparable<Seg> {

        int left, right;

        public Seg(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Seg rhs) {
            if (right == rhs.right) {
                return rhs.left - left;
            }
            return right - rhs.right;
        }
    }


    // greedy ChatGPT
    static List<String> maxNumOfSubstringsI(String s) {

        int n = s.length();

        // first[c] = first occurrence of character c
        // last[c]  = last occurrence of character c
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try creating a valid interval starting
        // at the first occurrence of every character.
        for (int c = 0; c < 26; c++) {

            if (last[c] == -1)
                continue;

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int curr = s.charAt(i) - 'a';

                // This character appeared before left.
                if (first[curr] < left) {
                    valid = false;
                    break;
                }

                // This character appears after current right.
                if (last[curr] > right) {
                    right = last[curr];
                }
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort by ending position.
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            // Non-overlapping
            if (left > prevEnd) {

                result.add(s.substring(left, right + 1));

                prevEnd = right;
            }
        }
        return result;
    }
}
