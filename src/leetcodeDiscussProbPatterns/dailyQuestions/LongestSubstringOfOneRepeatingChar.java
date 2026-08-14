package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.TreeMap;

public class LongestSubstringOfOneRepeatingChar {
	public static void main(String[] args) {
		String s = "babacc", queryCharacters = "bcb";
		int[] queryIndices = { 1, 3, 3 };
		System.out.println(Arrays.toString(longestRepeating(s, queryCharacters, queryIndices)));
		System.out.println(Arrays.toString(longestRepeatingI(s, queryCharacters, queryIndices)));
	}

// segment tree
/// Let n be the length of the string s, and let k be the number of queries.
/// 
/// Time complexity: O((n+k)logn).
/// 
/// Building the segment tree initially takes O(n) time. Each query performs a single-point update, which takes O(logn) time. Therefore, the total time complexity is O(n+klogn).
/// 
/// Space complexity: O(n).
/// 
/// The segment tree uses arrays of size O(n).

	private static char[] sArr;
	private static int[] pre, suf, maxLen;
	private static char[] leftChar, rightChar;

	static int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
		int n = s.length();
		sArr = s.toCharArray();
		pre = new int[4 * n];
		suf = new int[4 * n];
		maxLen = new int[4 * n];
		leftChar = new char[4 * n];
		rightChar = new char[4 * n];

		build(1, 0, n - 1);
		int k = queryIndices.length;
		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
			ans[i] = maxLen[1];
		}
		return ans;
	}

	private static void pushUp(int u, int l, int r) {
		int mid = (l + r) >> 1;
		int leftLen = mid - l + 1, rightLen = r - mid;
		int left = u << 1, right = (u << 1) | 1;
		leftChar[u] = leftChar[left];
		rightChar[u] = rightChar[right];
		pre[u] = pre[left];
		if (pre[left] == leftLen && rightChar[left] == leftChar[right]) {
			pre[u] = pre[left] + pre[right];
		}
		suf[u] = suf[right];
		if (suf[right] == rightLen && rightChar[left] == leftChar[right]) {
			suf[u] = suf[right] + suf[left];
		}
		maxLen[u] = Math.max(maxLen[left], maxLen[right]);
		if (rightChar[left] == leftChar[right]) {
			maxLen[u] = Math.max(maxLen[u], suf[left] + pre[right]);
		}
	}

	private static void build(int u, int l, int r) {
		if (l == r) {
			pre[u] = 1;
			suf[u] = 1;
			maxLen[u] = 1;
			leftChar[u] = sArr[l];
			rightChar[u] = sArr[l];
			return;
		}
		int mid = (l + r) >> 1;
		build(u << 1, l, mid);
		build((u << 1) | 1, mid + 1, r);
		pushUp(u, l, r);
	}

	private static void update(int u, int l, int r, int pos, char ch) {
		if (l == r) {
			leftChar[u] = ch;
			rightChar[u] = ch;
			return;
		}
		int mid = (l + r) >> 1;
		if (pos <= mid) {
			update(u << 1, l, mid, pos, ch);
		} else {
			update((u << 1) | 1, mid + 1, r, pos, ch);
		}
		pushUp(u, l, r);
	}

	// Ordered Set Simulating Interval Merging
	/// Let n be the length of the string s, and let k be the number of queries.
/// 
/// Time complexity: O((n+k)logn).
/// 
/// Constructing the initial intervals requires O(n) time. For each query, we perform a constant number of searches, deletions, and insertions in the ordered sets, each taking O(logn) time. Therefore, the total time complexity is O(n+klogn).
/// 
/// Space complexity: O(n).
/// 
/// There can be at most n intervals, so the ordered sets require O(n) space.
	static int[] longestRepeatingI(String s, String queryCharacters, int[] queryIndices) {
		int n = s.length();
		char[] arr = s.toCharArray();
		TreeMap<Integer, Integer> segs = new TreeMap<>();
		TreeMap<Integer, Integer> lens = new TreeMap<>();

		for (int i = 0; i < n;) {
			int j = i;
			while (j < n && arr[j] == arr[i]) {
				j++;
			}
			segs.put(i, j - 1);
			lens.put(j - i, lens.getOrDefault(j - i, 0) + 1);
			i = j;
		}

		int k = queryIndices.length;
		int[] ans = new int[k];

		for (int q = 0; q < k; q++) {
			int pos = queryIndices[q];
			char ch = queryCharacters.charAt(q);

			if (arr[pos] != ch) {
				int L = segs.floorKey(pos);
				int R = segs.get(L);
				segs.remove(L);
				int oldLen = R - L + 1;
				lens.put(oldLen, lens.get(oldLen) - 1);
				if (lens.get(oldLen) == 0) {
					lens.remove(oldLen);
				}

				if (L <= pos - 1) {
					segs.put(L, pos - 1);
					int len1 = pos - L;
					lens.put(len1, lens.getOrDefault(len1, 0) + 1);
				}
				if (pos + 1 <= R) {
					segs.put(pos + 1, R);
					int len2 = R - pos;
					lens.put(len2, lens.getOrDefault(len2, 0) + 1);
				}

				int newL = pos, newR = pos;

				Integer rightKey = segs.ceilingKey(pos + 1);
				if (rightKey != null && rightKey == pos + 1 && arr[pos + 1] == ch) {
					int rightR = segs.get(rightKey);
					int rightLen = rightR - rightKey + 1;
					lens.put(rightLen, lens.get(rightLen) - 1);
					if (lens.get(rightLen) == 0) {
						lens.remove(rightLen);
					}
					newR = rightR;
					segs.remove(rightKey);
				}

				Integer leftKey = segs.floorKey(pos - 1);
				if (leftKey != null) {
					int leftR = segs.get(leftKey);
					if (leftR == pos - 1 && arr[pos - 1] == ch) {
						int leftLen = leftR - leftKey + 1;
						lens.put(leftLen, lens.get(leftLen) - 1);
						if (lens.get(leftLen) == 0) {
							lens.remove(leftLen);
						}
						newL = leftKey;
						segs.remove(leftKey);
					}
				}

				segs.put(newL, newR);
				int newLen = newR - newL + 1;
				lens.put(newLen, lens.getOrDefault(newLen, 0) + 1);
				arr[pos] = ch;
			}

			ans[q] = lens.lastKey();
		}

		return ans;
	}

}
