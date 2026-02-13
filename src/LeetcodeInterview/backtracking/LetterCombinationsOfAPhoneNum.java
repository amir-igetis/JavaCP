package LeetcodeInterview.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNum {
    public static void main(String[] args) {
//

        String digits = "23";
        List<String> ans = letterCombinations(digits);
        for (String i : ans)
            System.out.print(i + ", ");
        System.out.println();
    }

    private static final Map<Character, String> phoneMap = Map.of(
            '2', "abc", '3', "def", '4', "ghi",
            '5', "jkl", '6', "mno", '7', "pqrs",
            '8', "tuv", '9', "wxyz"
    );

    static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) return result;
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = phoneMap.get(digits.charAt(index));
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(result, current, digits, index + 1);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}
