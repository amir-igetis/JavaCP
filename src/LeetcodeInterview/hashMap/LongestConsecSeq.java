package LeetcodeInterview.hashMap;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecSeq {
    public static void main(String[] args) {
//

        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    static int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        Set<Integer> st = new HashSet<>();
        for (int i : nums)
            st.add(i);

        int maxLen = 0;
        for (int i : st) {
            if (!st.contains(i - 1)) {
                int currNum = i;
                int currLen = 1;

                while (st.contains(currNum + 1)) {
                    currNum++;
                    currLen++;
                }

                maxLen = Math.max(maxLen, currLen);
            }
        }
        return maxLen;
    }
}
