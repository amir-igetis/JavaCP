package leetcodeContest.weekly516;

public class CheckAsciiPalidromic {
    public static void main(String[] args) {
        String s = "ff";
        String s1 = "leet";
        System.out.println(isPalindromic(s));
        System.out.println(isPalindromic(s1));
    }

    static boolean isPalindromic(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            for (int i = 7; i >= 0; i--)
                sb.append((ch >> i) & 1);
        }
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right))
                return false;

            left++;
            right--;
        }
        return true;
    }
}
