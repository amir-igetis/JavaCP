package leetcodeDiscussProbPatterns.dailyQuestions;

public class CalculateDigitSumOfAString {
    public static void main(String[] args) {
        String s = "11111222223";
        int k = 3;
        System.out.println(digitSum(s, k));
    }

    // Simulation

    /// Let n be the length of the string s.
    ///
    /// Time complexity: O(n).
    ///
    /// In each round, a string of current length m is divided into ⌈m/k⌉ groups. Each group sums to at most 9k.
    ///
    /// For k≥3, the sum 9k has at most ⌊log_10(9k)⌋+1<k digits, strictly shrinking the length by a factor of at least 2/3 every round.
    /// For k=2, each pair sums to at most 18 (at most 2 digits). While a single round might not strictly reduce the length, the digits of 18 sum to 9 (1 digit) in the subsequent round, ensuring the string length halves at least every two rounds.
    /// Because the length decreases by at least a constant factor periodically, the total number of characters processed across all rounds forms a convergent geometric series bounded by O(n). Since processing each character takes O(1) time, the overall time complexity is O(n).
    ///
    /// Space complexity: O(n).
    ///
    /// In each round, we construct an auxiliary string (or list) tmp to hold the next round's string, which takes O(n) space in the initial round
    static String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder tmp = new StringBuilder(); // The string after each round
            int n = s.length();
            for (int i = 0; i < n; i += k) {
                int val = 0;
                for (int j = i; j < Math.min(i + k, n); ++j) {
                    val += s.charAt(j) - '0';
                }
                tmp.append(val);
            }
            s = tmp.toString();
        }
        return s;
    }

}
