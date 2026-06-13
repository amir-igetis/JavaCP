package leetcodeContest.weekly503;

import java.util.HashSet;
import java.util.Set;

public class PasswordStrength {
    public static void main(String[] args) {
        String password = "aA1!";
        System.out.println(passwordStrength(password));
    }

    static int passwordStrength(String password) {
        Set<Character> st = new HashSet<>();
        int pow = 0;
        for (char ch : password.toCharArray()) {
            if (st.contains(ch))
                continue;

            st.add(ch);
            if (ch >= 'a' && ch <= 'z')
                pow += 1;
            else if (ch >= 'A' && ch <= 'Z')
                pow += 2;
            else if (ch >= '0' && ch <= '9')
                pow += 3;
            else if (ch == '!' || ch == '@' || ch == '#' || ch == '$')
                pow += 5;

        }
        return pow;
    }
}
