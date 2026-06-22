package leetcodeDiscussProbPatterns.dailyQuestions;

public class ProcessStringWithSpecialOpsI {
    public static void main(String[] args) {
        String s = "a#b%*";
        System.out.println(processStr(s));
    }

    //    simulation tc & sc O(2^n)
    static String processStr(String s) {
        StringBuilder res = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (res.length() > 0)
                    res.deleteCharAt(res.length() - 1);
            } else if (ch == '#')
                res.append(res.toString());
            else if (ch == '%')
                res.reverse();
            else res.append(ch);
        }
        return res.toString();

    }
}

