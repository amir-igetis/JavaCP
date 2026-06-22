package leetcodeContest.weekly506;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class FrequencyBalSubarr {
    public static void main(String[] args) {
        int[] nums = {5, 5, 5, 5};
        int[] numsI = {1, 2, 2, 1, 2, 3, 3, 3};
        int[] numsII = {1, 2, 3, 4};
        System.out.println(getLength(nums));
        System.out.println(getLength(numsI));
        System.out.println(getLength(numsII));
    }

    static public int getLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            TreeMap<Integer, Integer> freqCnt = new TreeMap<>();
            int distinct = 0;
            for (int j = i; j < n; j++) {
                int x = nums[j];
                if (freq.containsKey(x)) {
                    int oldFreq = freq.get(x);
                    freqCnt.put(oldFreq, freqCnt.get(oldFreq) - 1);
                    if (freqCnt.get(oldFreq) == 0)
                        freqCnt.remove(oldFreq);
                } else {
                    distinct++;
                }
                int newFreq = freq.getOrDefault(x, 0) + 1;
                freq.put(x, newFreq);
                freqCnt.put(newFreq, freqCnt.getOrDefault(newFreq, 0) + 1);
                boolean valid = false;
                if (distinct == 1)
                    valid = true;
                else if (freqCnt.size() == 2) {
                    Iterator<Integer> it = freqCnt.keySet().iterator();
                    int f1 = it.next();
                    int f2 = it.next();
                    if (f2 == 2 * f1)
                        valid = true;
                }
                if (valid)
                    ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
}
