package leetcodeDiscussProbPatterns.dailyQuestions;

public class ShortestAndLexicographicallySmallestBeautifulString {
	public static void main(String[] args) {
		String s = "100011001";
		int k = 3;
		System.out.println(shortestBeautifulSubstring(s, k));
	}

// enumeration
/// Let n be the length of the string s.
///
/// Time complexity: O(n^3).
///
/// There are O(n) possible substring lengths, and for each length, we enumerate O(n) substrings. Checking the number of ones and extracting each substring both take O(n) time, resulting in a total time complexity of O(n^3).
/// 
/// Space complexity: O(n) or O(1).
/// 
/// At any time, we store a substring of length at most n and the current answer, both of which require O(n) space.
	static String shortestBeautifulSubstring(String s, int k) {
		int n = s.length();
		for (int m = k; m <= n; m++) {
			String ans = "";
			for (int i = m; i <= n; i++) {
				String t = s.substring(i - m, i);
				int cnt = 0;
				for (int j = 0; j < t.length(); j++) {
					cnt += t.charAt(j) - '0';
				}
				if ((ans.isEmpty() || t.compareTo(ans) < 0) && cnt == k) {
					ans = t;
				}
			}
			if (!ans.isEmpty()) {
				return ans;
			}
		}
		return "";

	}

// Sliding Window
/// Let n be the length of the string s.
///
/// Time complexity: O(n^2)
///
/// The sliding window itself takes O(n) time, since both left and right move from left to right at most once. However, extracting a substring takes O(n) time in the worst case, and this operation can be performed O(n) times. Therefore, the total time complexity is O(n^2).
///
/// Space complexity: O(n) or O(1).
/// 
/// The current substring and the answer can each require O(n) space.
	static String shortestBeautifulSubstringI(String s, int k) {
		int total = 0;
		for (int i = 0; i < s.length(); i++)
			total += s.charAt(i) - '0';
		if (total < k)
			return "";
		String ans = s;
		int cnt = 0, left = 0;
		for (int right = 0; right < s.length(); right++) {
			cnt += s.charAt(right) - '0';
			while (cnt > k || s.charAt(left) == '0') {
				cnt -= s.charAt(left++) - '0';
			}
			if (cnt == k) {
				String t = s.substring(left, right + 1);
				if (t.length() < ans.length() || (t.length() == ans.length() && t.compareTo(ans) < 0)) {
					ans = t;
				}
			}
		}
		return ans;
	}
}
