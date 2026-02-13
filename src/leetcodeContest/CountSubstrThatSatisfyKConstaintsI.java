package leetcodeContest;

public class CountSubstrThatSatisfyKConstaintsI {
    public static void main(String[] args) {
        String s = "11111";
        int k = 1;
        System.out.println(countKConstraintSubstrings(s, k));
    }

    static int countKConstraintSubstrings(String s, int k) {
        return countSubstrings(s, '0', k) + countSubstrings(s, '1', k);
    }

    private static int countSubstrings(String s, char ch, int k) {
        int left = 0;
        int count = 0;
        int currentCount = 0;

        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == ch) {
                currentCount++;
            }

            // Shrink the window if the count of the character exceeds k
            while (currentCount > k) {
                if (s.charAt(left) == ch) {
                    currentCount--;
                }
                left++;
            }

            // Add the number of valid substrings ending at 'right'
            count += (right - left + 1);
        }

        return count;
    }
}
