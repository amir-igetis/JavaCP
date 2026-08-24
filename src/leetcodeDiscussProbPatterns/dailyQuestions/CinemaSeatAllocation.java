package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.Map;

public class CinemaSeatAllocation {
	public static void main(String[] args) {
		int[][] reservedSeats = { { 2, 1 }, { 1, 8 }, { 2, 6 } };
		int n = 2;
		System.out.println(maxNumberOfFamilies(n, reservedSeats));
	}

// bitwise Operations
/// Let r be the length of the array reservedSeats.
///
/// Time complexity: O(r).
/// 
/// We first traverse reservedSeats to record the reservation information in the hash map, and then traverse the hash map to compute the answer. Both operations take O(r) time in total.
/// 
/// Space complexity: O(r).
/// 
/// The hash map stores the reservation information for each row that has at least one reserved seat among positions 2 to 9. In the worst case, it can contain O(r) entries.
	static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
		int left = 0b11110000;
		int middle = 0b11000011;
		int right = 0b00001111;

		Map<Integer, Integer> occupied = new HashMap<Integer, Integer>();
		for (int[] seat : reservedSeats) {
			if (seat[1] >= 2 && seat[1] <= 9) {
				int origin = occupied.containsKey(seat[0]) ? occupied.get(seat[0]) : 0;
				int value = origin | (1 << (seat[1] - 2));
				occupied.put(seat[0], value);
			}
		}

		int ans = (n - occupied.size()) * 2;
		for (Map.Entry<Integer, Integer> entry : occupied.entrySet()) {
			int row = entry.getKey();
			int bitmask = entry.getValue();
			if ((bitmask | left) == left || (bitmask | middle) == middle || (bitmask | right) == right) {
				++ans;
			}
		}
		return ans;

	}
}
