package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxLenSubstringWithTwoOccurrences {
	public static void main(String[] args) {
		String s = "bcbbbcba";
		System.out.println(maximumLengthSubstring(s));
		System.out.println(maximumLengthSubstringI(s));
	}

// enumerate the left endpoint
/// Let n be the length of s.
/// Time complexity: O(n^2)
///
/// Space complexity: O(1).
	static int maximumLengthSubstring(String s) {
		int n = s.length();
		int res = 0;
		for (int left = 0; left < n; left++) {
			int[] count = new int[26];
			for (int right = left; right < n; right++) {
				int ch = s.charAt(right) - 'a';
				count[ch]++;
				if (count[ch] > 2) {
					break;
				}
				res = Math.max(res, right - left + 1);
			}
		}
		return res;

	}

// sliding window
/// Let n be the length of s.
/// 
/// Time complexity: O(n).
///
/// Each character is added to the sliding window at most once and removed from the sliding window at most once. Therefore, the overall time complexity is O(n).
///
/// Space complexity: O(1).
/// 
/// We use a fixed-size count array of size 26, so the extra space is O(1).
	static int maximumLengthSubstringI(String s) {
		int[] count = new int[26];
		int left = 0;
		int res = 0;
		for (int right = 0; right < s.length(); right++) {
			int ch = s.charAt(right) - 'a';
			count[ch]++;
			while (count[ch] > 2) {
				int ch2 = s.charAt(left) - 'a';
				count[ch2]--;
				left++;
			}
			res = Math.max(res, right - left + 1);
		}
		return res;
	}

}
