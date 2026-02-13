package LeetcodeInterview.hashMap;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
//
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    static List<List<String>> groupAnagramsII(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> mp = new HashMap<>();
        for (String str : strs) {
            char[] arr = new char[26];
            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }
            String ans = new String(arr);
            if (mp.containsKey(ans)) {
                mp.get(ans).add(str);
            } else {
                ArrayList<String> array = new ArrayList<>();
                array.add(str);
                mp.put(ans, array);
            }
        }
        res.addAll(mp.values());
        return res;
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> mp = new HashMap<>();

        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            mp.putIfAbsent(sortedStr, new ArrayList<>());
            mp.get(sortedStr).add(s);
        }
        return new ArrayList<>(mp.values());
    }

    static List<List<String>> groupAnagramI(String[] strs) {
        Map<String, List<String>> mp = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray())
                count[c - 'a']++;

            StringBuilder keyBuilder = new StringBuilder();
            for (int num : count) {
                keyBuilder.append(num).append('#');
            }

            String key = keyBuilder.toString();
            mp.putIfAbsent(key, new ArrayList<>());
            mp.get(key).add(s);
        }

        return new ArrayList<>(mp.values());

    }
}
