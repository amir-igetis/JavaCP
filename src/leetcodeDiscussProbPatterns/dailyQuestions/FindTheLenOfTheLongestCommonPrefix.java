package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class FindTheLenOfTheLongestCommonPrefix {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3}, arr2 = {4, 4, 4};
        System.out.println(longestCommonPrefix(arr1, arr2));
    }

    static int longestCommonPrefix(int[] arr1, int[] arr2) {
        Map<String, Integer> mp = new HashMap<>();
        for (int i : arr1) {
            String strNum = Integer.toString(i);
            String pre = "";
            for (char ch : strNum.toCharArray()) {
                pre += ch;
                mp.put(pre, mp.getOrDefault(pre, 0) + 1);
            }
        }
        int maxLen = 0;
        for (int i : arr2) {
            String strNum = Integer.toString(i);
            String pre = "";
            for (char ch : strNum.toCharArray()) {
                pre += ch;
                if (mp.containsKey(pre)) {
                    maxLen = Math.max(maxLen, pre.length());
                }
            }
        }
        return maxLen;
    }

}
